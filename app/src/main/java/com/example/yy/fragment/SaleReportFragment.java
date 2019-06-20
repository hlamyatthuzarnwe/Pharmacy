package com.example.yy.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yy.R;
import com.example.yy.adapter.MedicineAdapter;
import com.example.yy.adapter.SaleReportAdapter;
import com.example.yy.model.MedicineModel;
import com.example.yy.model.SaleReportModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleReportFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleReportFragment";
    private SaleReportAdapter saleReportAdapter;

    @BindView(R.id.swipeSaleReort)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvSaleReport)
    RecyclerView rvSaleReport;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    public SaleReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_report, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        init();
        getAllSaleReport();
        return view;
    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        saleReportAdapter = new SaleReportAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvSaleReport.setLayoutManager(linearLayoutManager);
        rvSaleReport.setAdapter(saleReportAdapter);
    }

    private void getAllSaleReport() {
        final List<SaleReportModel> saleReportModelList = realm.where(SaleReportModel.class).findAll();
        Log.d(TAG,"getData : "+saleReportModelList.size());

        if( saleReportModelList!= null && !saleReportModelList.isEmpty()){
            saleReportAdapter.getSaleReportModelList().addAll(saleReportModelList);
            saleReportAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        saleReportAdapter.clear();
        getAllSaleReport();

    }
}
