package macys.interview.android.filescanner;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.util.AverageFileSizeUtil;
import macys.interview.android.filescanner.util.BiggestFileUtil;

/**
 * Created by Yee on 3/22/18.
 */

public class ScanService extends Service implements ScanCallBack {

    public static final String TAG = "ScanService";
    public static final String ACTION = "scanActionFinished";
    public static final String TOTAL_DATA = "scanData";
    public static final String AVERAGE_SIZE = "averageSize";
    public static final String TOP_BIGGEST = "topBiggest";
    public static final String MOST_FREQUENCY = "mostFrequency";

    ScanTask mScanTask;

    public static Intent newIntent(Context context) {
        return new Intent(context, ScanService.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mScanTask = new ScanTask(this);
        mScanTask.startScan();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        Disposable disposable = mScanTask.getDisposable();
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }


    @Override
    public void onScanFinished(List<ScanFile> scanFiles, BiggestFileUtil biggestFileUtil, AverageFileSizeUtil
            averageFileSizeUtil) {
        Intent intent = new Intent(ScanService.ACTION);
        intent.putParcelableArrayListExtra(ScanService.TOTAL_DATA, new ArrayList<Parcelable>
                (scanFiles));
        intent.putExtra(ScanService.AVERAGE_SIZE, averageFileSizeUtil.getAverage());
        intent.putParcelableArrayListExtra(ScanService.TOP_BIGGEST, new ArrayList<Parcelable>
                (biggestFileUtil.getBiggestFileList()));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onScanFailed() {

    }
}
