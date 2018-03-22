package macys.interview.android.filescanner;

import java.util.List;

import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.util.AverageFileSizeUtil;
import macys.interview.android.filescanner.util.BiggestFileUtil;

/**
 * Created by Yee on 3/22/18.
 */

public interface ScanCallBack {

    void onScanFinished(List<ScanFile> scanFileList, BiggestFileUtil biggestFileUtil, AverageFileSizeUtil
            averageFileSizeUtil);

    void onScanFailed();
}
