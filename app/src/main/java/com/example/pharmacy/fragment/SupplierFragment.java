package com.example.pharmacy.fragment;


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

import com.example.pharmacy.adapter.SupplierAdapter;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="MainActivity";
    private SupplierAdapter supplierAdapter;

    @BindView(R.id.swipeSupplier)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvSupplier)
    RecyclerView rvSupplier;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;



    public SupplierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        init();
        getAllSupplier();
        return view;
    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        supplierAdapter = new SupplierAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvSupplier.setLayoutManager(linearLayoutManager);
        rvSupplier.setAdapter(supplierAdapter);
    }

    private void getAllSupplier() {
        final List<SupplierModel> supplierModelList = realm.where(SupplierModel.class).findAll();
        Log.d(TAG,"getData : "+supplierModelList.size());

        if(supplierModelList != null && !supplierModelList.isEmpty()){
            supplierAdapter.getSupplierModelList().addAll(supplierModelList);
            supplierAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        supplierAdapter.clear();
        getAllSupplier();

    }
}
