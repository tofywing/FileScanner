package macys.interview.android.filescanner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import macys.interview.android.filescanner.R;

/**
 * Created by Yee on 3/22/18.
 */

class BiggestHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public BiggestHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.list_item_content);
    }
}
