package macys.interview.android.filescanner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import macys.interview.android.filescanner.R;

/**
 * Created by Yee on 3/22/18.
 */

class ItemHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    TextView mValue;

    ItemHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.list_item_title);
        mValue = itemView.findViewById(R.id.list_item_value);
    }
}
