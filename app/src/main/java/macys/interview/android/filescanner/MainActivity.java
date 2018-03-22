package macys.interview.android.filescanner;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import macys.interview.android.filescanner.fragment.ScanFragment;
import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.model.ScanResult;
import macys.interview.android.filescanner.model.ScanResultWrapper;
import macys.interview.android.filescanner.util.AverageFileSizeUtil;
import macys.interview.android.filescanner.util.BiggestFileUtil;
import macys.interview.android.filescanner.util.FrequentExtensionsUtil;

public class MainActivity extends AppCompatActivity implements ScanContract.View {

    public static final String TAG = "MainActivity";

    ScanPresenter mScanPresenter;
    BroadcastReceiver mReceiver;
    Menu mMenu;
    FragmentManager mFragmentManager;
    ScanFragment mScanFragment;
    ProgressBar mScanProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFragmentManager = getSupportFragmentManager();
        mScanProgress = findViewById(R.id.scan_progress);
        mScanPresenter = new ScanPresenter(this, this);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null && intent.getAction().equals(ScanService.ACTION)) {
                    MenuItem menuItem = mMenu.findItem(R.id.action_scan);
                    updateItemTitle(menuItem, false);
                    long size = intent.getLongExtra(ScanService.AVERAGE_SIZE, 0);
                    List<ScanFile> biggest = intent.getParcelableArrayListExtra(ScanService.TOP_BIGGEST);
                    List<ScanFile> totalFiles = intent.getParcelableArrayListExtra(ScanService.TOTAL_DATA);
                    AverageFileSizeUtil.builder().setAverage(size);
                    ScanResultWrapper.getInstance().setBiggestFileList(new ArrayList<>(biggest));
                    ScanResultWrapper.getInstance().setAverageFileSize(size);
                    ScanResult scanResult = ScanResult.builder();
                    scanResult.setScanFileList(totalFiles);
                    ScanResultWrapper.getInstance().setScanResult(scanResult);
                    FrequentExtensionsUtil.builder().updateExtensionsList();
                    mScanPresenter.stopScan();
                }
            }
        };
        createFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        boolean isScan;
        if (id == R.id.action_scan) {
            String action = String.valueOf(item.getTitle());
            if (action.equals(getString(R.string.action_scan))) {
                mScanPresenter.startScan();
                isScan = true;
            } else {
                mScanPresenter.stopScan();
                isScan = false;
            }
            updateItemTitle(item, isScan);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateItemTitle(MenuItem item, boolean isScan) {
        if (isScan) {
            item.setTitle(getString(R.string.action_stop));
        } else {
            item.setTitle(getString(R.string.action_scan));
        }
    }


    @Override
    public void scanStart() {
        mScanProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void scanFinish() {
        mScanProgress.setVisibility(View.INVISIBLE);
        mScanFragment.setupAdapter();
    }

    private void createFragment() {
        mScanFragment = (ScanFragment) mFragmentManager.findFragmentById(R.id.fragment_result_list);
        if (mScanFragment != null) {
            mFragmentManager.beginTransaction().remove(mScanFragment).commit();
        }
        mScanFragment = ScanFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.fragment_result_list, mScanFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ScanService.ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
