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

import com.example.pharmacy.model.CustomerModel;
import com.example.yy.R;
import com.example.pharmacy.adapter.CustomerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="MainActivity";
    private CustomerAdapter customerAdapter;

    @BindView(R.id.swipeCustomer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvCustomer)
    RecyclerView rvCustomer;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;


    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        ButterKnife.bind(this,view);
        init();
        getAllCustomer();
        return view;
    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        customerAdapter = new CustomerAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvCustomer.setLayoutManager(linearLayoutManager);
        rvCustomer.setAdapter(customerAdapter);
    }

    private void getAllCustomer() {

        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class).sort("customerName", Sort.ASCENDING).findAll();
        Log.d(TAG,"getData : "+customerModelList.size());

        if(customerModelList != null && !customerModelList.isEmpty()){
            customerAdapter.getCustomerModelList().addAll(customerModelList);
            customerAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        customerAdapter.clear();
        getAllCustomer();
    }
}
