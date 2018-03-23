package macys.interview.android.filescanner;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.model.ScanResult;
import macys.interview.android.filescanner.model.ScanResultWrapper;
import macys.interview.android.filescanner.util.AverageFileSizeUtil;
import macys.interview.android.filescanner.util.BiggestFileUtil;
import macys.interview.android.filescanner.util.FileExtensionUtil;
import macys.interview.android.filescanner.util.FrequentExtensionsUtil;

/**
 * Created by Yee on 3/22/18.
 */

class ScanTask {


    private ScanCallBack mScanCallBack;
    private Disposable mDisposable;
    private BiggestFileUtil mBiggestFileUtil;
    private AverageFileSizeUtil mAverageFileSizeUtil;
    private List<ScanFile> mScanFiles;

    ScanTask(ScanCallBack scanCallBack) {
        mScanCallBack = scanCallBack;
        mBiggestFileUtil = BiggestFileUtil.builder();
        mAverageFileSizeUtil = AverageFileSizeUtil.builder();
        mScanFiles = new ArrayList<>();
    }

    private List<ScanFile> getFile(File dir) {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                String name = file.getName();
                long size = file.getTotalSpace();
                ScanFile scanFile = new ScanFile(name, size, FileExtensionUtil.getExtension(name));
                mBiggestFileUtil.updateBiggestFileList(scanFile);
                mAverageFileSizeUtil.updateAverage(size);
                mScanFiles.add(scanFile);
                if (file.isDirectory()) {
                    getFile(file);
                }
            }
        }
        return mScanFiles;
    }

    void startScan() {
        mDisposable = Observable.fromCallable(new Callable<List<ScanFile>>() {
            @Override
            public List<ScanFile> call() throws Exception {
                File root = new File(Environment.getRootDirectory().getAbsolutePath());
                return getFile(root);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ScanFile>>() {
                    @Override
                    public void accept(List<ScanFile> scanFiles) throws Exception {
                        mScanCallBack.onScanFinished(scanFiles, mBiggestFileUtil, mAverageFileSizeUtil);
                    }
                });
    }

    Disposable getDisposable() {
        return mDisposable;
    }
}
