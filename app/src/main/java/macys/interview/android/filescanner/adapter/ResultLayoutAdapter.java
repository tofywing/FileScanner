package macys.interview.android.filescanner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import macys.interview.android.filescanner.R;
import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.model.ScanResultWrapper;
import macys.interview.android.filescanner.util.AverageFileSizeUtil;
import macys.interview.android.filescanner.util.FileSizeUtil;
import macys.interview.android.filescanner.util.FrequentExtensionsUtil;

/**
 * Created by Yee on 3/21/18.
 */

public class ResultLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_AVERAGE_SIZE = 0;
    private static final int TYPE_FREQUENT_EXTENSION = 1;
    private static final int TYPE_BIGGEST_FILES = 2;
    private static final int FIXED_SIZE = 3;

    private Context mContext;
    private BiggestListAdapter mBiggestFileListAdapter;
    private FrequencyListAdapter mFrequentExtensionAdapter;

    public ResultLayoutAdapter() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_BIGGEST_FILES) {
            view = LayoutInflater.from(mContext = parent.getContext()).inflate(R.layout.item_report_quote_with_list,
                    parent,
                    false);
            return new BiggestFilesHolder(view);
        } else if (viewType == TYPE_FREQUENT_EXTENSION) {
            view = LayoutInflater.from(mContext = parent.getContext()).inflate(R.layout.item_report_quote_with_list,
                    parent,
                    false);
            return new FrequentExtensionHolder(view);
        } else {
            view = LayoutInflater.from(mContext = parent.getContext()).inflate(R.layout.item_report_quote, parent,
                    false);
            return new AverageFileHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        if (holder instanceof AverageFileHolder) {
            ((AverageFileHolder) holder).mTitle.setText(R.string.average_title);
            String size = FileSizeUtil.getReadableFileSize(ScanResultWrapper.getInstance().getAverageFileSize());
            ((AverageFileHolder) holder).mValue.setText(size);
        }
        if (holder instanceof BiggestFilesHolder) {
            ((BiggestFilesHolder) holder).mTitle.setText(R.string.biggest_file_title);
            ((BiggestFilesHolder) holder).mList.setLayoutManager(layoutManager);
            if (mBiggestFileListAdapter == null) {
                mBiggestFileListAdapter = new BiggestListAdapter(ScanResultWrapper
                        .getInstance().getBiggestFileList());
                ((BiggestFilesHolder) holder).mList.setAdapter(mBiggestFileListAdapter);
            }
            mBiggestFileListAdapter.setFileList(ScanResultWrapper
                    .getInstance().getBiggestFileList());
            mBiggestFileListAdapter.notifyDataSetChanged();
        }
        if (holder instanceof FrequentExtensionHolder) {
            ((FrequentExtensionHolder) holder).mTitle.setText(R.string.frequent_extension_title);
            ((FrequentExtensionHolder) holder).mList.setLayoutManager(layoutManager);
            if (mFrequentExtensionAdapter == null) {
                mFrequentExtensionAdapter = new FrequencyListAdapter(ScanResultWrapper.getInstance()
                        .getFrequentFileList());
                ((FrequentExtensionHolder) holder).mList.setAdapter(mFrequentExtensionAdapter);
            }
            mFrequentExtensionAdapter.setFileList(ScanResultWrapper.getInstance().getFrequentFileList());
            mFrequentExtensionAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return FIXED_SIZE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_AVERAGE_SIZE) {
            return TYPE_AVERAGE_SIZE;
        } else if (position == TYPE_FREQUENT_EXTENSION) {
            return TYPE_FREQUENT_EXTENSION;
        } else {
            return TYPE_BIGGEST_FILES;
        }
    }
}
