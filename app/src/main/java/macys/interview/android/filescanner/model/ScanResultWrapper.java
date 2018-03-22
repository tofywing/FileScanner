package macys.interview.android.filescanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import macys.interview.android.filescanner.ScanCallBack;

/**
 * Created by Yee on 3/21/18.
 */

public class ScanResultWrapper {

    private ScanResult mScanResult = ScanResult.builder();
    private List<ScanFile> mBiggestFileList = new ArrayList<>();
    private List<String> mFrequentFileList = new ArrayList<>();
    private long averageFileSize;

    private static final ScanResultWrapper ourInstance = new ScanResultWrapper();

    public static ScanResultWrapper getInstance() {
        return ourInstance;
    }

    private ScanResultWrapper() {
    }

    public void setScanResult(ScanResult scanResult) {
        mScanResult = scanResult;
    }

    public ScanResult getScanResult() {
        return mScanResult;
    }

    public List<ScanFile> getBiggestFileList() {
        return mBiggestFileList;
    }

    public void setFrequentFileList(List<String> frequentFileList) {
        mFrequentFileList = frequentFileList;
    }

    public void setBiggestFileList(List<ScanFile> biggestFileList) {
        mBiggestFileList = biggestFileList;
    }

    public List<String> getFrequentFileList() {
        return mFrequentFileList;
    }

    public void setAverageFileSize(long averageFileSize) {
        this.averageFileSize = averageFileSize;
    }

    public long getAverageFileSize() {
        return averageFileSize;
    }
}
