package macys.interview.android.filescanner;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Yee on 3/22/18.
 */

public class ScanPresenter implements ScanContract.Action {

    private ScanContract.View mView;
    private Intent mScanIntent;
    private Context mContext;

    public ScanPresenter(Context context, ScanContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void startScan() {
        mScanIntent = ScanService.newIntent(mContext);
        mContext.startService(mScanIntent);
        mView.scanStart();
    }

    @Override
    public void stopScan() {
        mContext.stopService(mScanIntent);
        mView.scanFinish();
    }
}
