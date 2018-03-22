package macys.interview.android.filescanner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import macys.interview.android.filescanner.R;
import macys.interview.android.filescanner.model.ScanFile;

/**
 * Created by Yee on 3/22/18.
 */

public class FrequencyListAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<String> mFileList;

    public FrequencyListAdapter(List<String> fileList) {
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
        String[] extensionContent = mFileList.get(position).split(" ");
        holder.mTextView.setText(String.format(Locale.US, "%d. extension: %s frequency: %s"
                , position + 1
                , mFileList.get(position)
                , extensionContent[1]));
    }

    @Override
    public int getItemCount() {
        return mFileList == null ? 0 : mFileList.size();
    }

    public void setFileList(List<String> fileList) {
        mFileList = fileList;
    }
}
