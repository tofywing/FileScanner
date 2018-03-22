package macys.interview.android.filescanner.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import macys.interview.android.filescanner.R;
import macys.interview.android.filescanner.adapter.ResultLayoutAdapter;

/**
 * Created by Yee on 3/22/18.
 */

public class ScanFragment extends Fragment {

    RecyclerView mResultLayoutList;
    ResultLayoutAdapter mLayoutAdapter;

    public static ScanFragment newInstance() {

        Bundle args = new Bundle();

        ScanFragment fragment = new ScanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_list, container, false);
        mResultLayoutList = view.findViewById(R.id.result_layout_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mResultLayoutList.setLayoutManager(layoutManager);
        setupAdapter();
        return view;
    }

    public void setupAdapter() {
        mLayoutAdapter = new ResultLayoutAdapter();
        mResultLayoutList.setAdapter(mLayoutAdapter);
    }
}
