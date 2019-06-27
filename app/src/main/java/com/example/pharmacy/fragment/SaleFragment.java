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

import com.example.pharmacy.adapter.CustomerAdapter;
import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;
import com.example.pharmacy.adapter.SaleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    private SaleAdapter saleAdapter;
    private CustomerAdapter customerAdapter;
    private MedicineAdapter medicineAdapter;

    @BindView(R.id.swipeSale)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvSale)
    RecyclerView rvSale;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    public SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        init();
        getAllSale();
        return view;
    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        saleAdapter = new SaleAdapter();
        customerAdapter = new CustomerAdapter();
        medicineAdapter = new MedicineAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvSale.setLayoutManager(linearLayoutManager);
        rvSale.setAdapter(saleAdapter);
        rvSale.setAdapter(customerAdapter);
        rvSale.setAdapter(medicineAdapter);
    }

    private void getAllSale() {
        final List<SaleModel> saleModelList = realm.where(SaleModel.class).findAll();
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class).findAll();
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
        Log.d(TAG,"getData : "+saleModelList.size());
        Log.d(TAG,"getData : "+customerModelList.size());
        Log.d(TAG,"getData : "+medicineModelList.size());

        if(saleModelList != null && !saleModelList.isEmpty()){
            saleAdapter.getSaleModelList().addAll(saleModelList);
            saleAdapter.notifyDataSetChanged();
        }
        if(medicineModelList != null && !medicineModelList.isEmpty()){
            medicineAdapter.getMedicineModelList().addAll(medicineModelList);
            medicineAdapter.notifyDataSetChanged();
        }
        if(customerModelList != null && !customerModelList.isEmpty()){
            customerAdapter.getCustomerModelList().addAll(customerModelList);
            customerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        saleAdapter.clear();
        medicineAdapter.clear();
        customerAdapter.clear();
        getAllSale();

    }
}