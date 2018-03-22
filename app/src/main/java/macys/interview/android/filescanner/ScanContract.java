package macys.interview.android.filescanner;

/**
 * Created by Yee on 3/22/18.
 */

public interface ScanContract {

    interface View {
        void scanStart();
        void scanFinish();
    }

    interface Action {
        void startScan();
        void stopScan();
    }
}
