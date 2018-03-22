package macys.interview.android.filescanner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import macys.interview.android.filescanner.R;

/**
 * Created by Yee on 3/21/18.
 */

class BiggestFilesHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    RecyclerView mList;

    BiggestFilesHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.report_list_title);
        mList = itemView.findViewById(R.id.report_list);
    }
}
