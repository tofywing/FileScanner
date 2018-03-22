package macys.interview.android.filescanner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import macys.interview.android.filescanner.R;
import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.util.FileSizeUtil;

/**
 * Created by Yee on 3/22/18.
 */

public class BiggestListAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<ScanFile> mFileList;

    public BiggestListAdapter(List<ScanFile> fileList) {
        mFileList = fileList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        ScanFile file = mFileList.get(position);
        holder.mTextView.setText(String.format(Locale.US, "%d. name: %s size: %s"
                , position + 1
                , file.getName()
                , FileSizeUtil.getReadableFileSize(file.getSize())));
    }

    @Override
    public int getItemCount() {
        return mFileList == null ? 0 : mFileList.size();
    }

    public void setFileList(List<ScanFile> fileList) {
        mFileList = fileList;
    }
}
