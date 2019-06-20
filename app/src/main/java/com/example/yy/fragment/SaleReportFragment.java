package com.example.yy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleReportFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleReportFragment";


    public SaleReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale_report, container, false);
    }

    @Override
    public void onRefresh() {

    }
}
