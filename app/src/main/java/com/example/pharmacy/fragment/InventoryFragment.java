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
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="MainActivity";
    private MedicineAdapter medicineAdapter;

    @BindView(R.id.swipeInventory)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvInventory)
    RecyclerView rvInventory;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_inventory, container, false);
            ButterKnife.bind(this,view);
            init();
            getAllMedicine();
            return view;

    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        medicineAdapter = new MedicineAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvInventory.setLayoutManager(linearLayoutManager);
        rvInventory.setAdapter(medicineAdapter);
    }

    private void getAllMedicine() {

        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).sort("medicineName", Sort.ASCENDING).findAll();
        Log.d(TAG,"getData : "+medicineModelList.size());

        if(medicineModelList != null && !medicineModelList.isEmpty()){
            medicineAdapter.getMedicineModelList().addAll(medicineModelList);
        }
        medicineAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        medicineAdapter.clear();
        getAllMedicine();
    }
}
