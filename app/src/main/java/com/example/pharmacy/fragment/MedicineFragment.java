package com.example.pharmacy.fragment;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yy.R;
import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.pharmacy.model.MedicineModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="MedicineFragment";
    private MedicineAdapter medicineAdapter;

    @BindView(R.id.swipeMedicine)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvMedicine)
    RecyclerView rvMedicine;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;


    public MedicineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        context = view.getContext();

        init();

        getAllMedicine();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_view,menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint( "Search by Name");
        searchView.setScrollBarSize(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               if (TextUtils.isEmpty(query)){
                   getAllMedicine();
               }else {
                   getSearchList(query);
               }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    getAllMedicine();
                    return true;
                }
                return false;
            }
        });

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
    private void getSearchList(String query){
        medicineAdapter.clear();
        RealmResults<MedicineModel> medicineList = realm.where(MedicineModel.class)
                .contains("medicineName", query, Case.INSENSITIVE)
                .findAll();
        RealmResults<MedicineModel> test = realm.where(MedicineModel.class)
                .findAll();

        Log.d(TAG, "onQueryTextSubmit: query : "+query);
        Log.d(TAG, "onQueryTextSubmit: test : "+test.size());
        Log.d(TAG, "onQueryTextSubmit: "+medicineList.size());
        medicineAdapter.getMedicineModelList().addAll(medicineList);
        medicineAdapter.notifyDataSetChanged();
    }

    private void getAllMedicine() {
        medicineAdapter.clear();
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
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
