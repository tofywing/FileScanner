package macys.interview.android.filescanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yee on 3/21/18.
 */

public class ScanResult {

    private List<ScanFile> mScanFileList = new ArrayList<>();


    private ScanResult() {

    }

    public static ScanResult builder(){
        return new ScanResult();
    }

    public void updateScanFileList(ScanFile scanFileList) {
        mScanFileList.add(scanFileList);
    }

    public List<ScanFile> getScanFileList() {
        return mScanFileList;
    }

    public void setScanFileList(List<ScanFile> scanFileList) {
        mScanFileList = scanFileList;
    }
}
