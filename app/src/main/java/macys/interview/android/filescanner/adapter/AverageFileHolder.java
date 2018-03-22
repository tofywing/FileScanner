package macys.interview.android.filescanner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import macys.interview.android.filescanner.R;

/**
 * Created by Yee on 3/21/18.
 */

class AverageFileHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    TextView mValue;

    public AverageFileHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.report_quote_title);
        mValue = itemView.findViewById(R.id.report_quote_value);
    }
}
