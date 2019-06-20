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
import com.example.yy.model.MedicineModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="MainActivity";
    private MedicineAdapter medicineAdapter;

    @BindView(R.id.swipeMedicine)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvMedicine)
    RecyclerView rvMedicine;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();

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
        rvMedicine.setLayoutManager(linearLayoutManager);
        rvMedicine.setAdapter(medicineAdapter);
    }

    private void getAllMedicine() {
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
        Log.d(TAG,"getData : "+medicineModelList.size());

        if(medicineModelList != null && !medicineModelList.isEmpty()){
            medicineAdapter.getMedicineModelList().addAll(medicineModelList);
            medicineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        medicineAdapter.clear();
        getAllMedicine();

    }
}
