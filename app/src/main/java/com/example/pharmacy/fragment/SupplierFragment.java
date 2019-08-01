package com.example.pharmacy.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pharmacy.adapter.SupplierAdapter;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

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
        setHasOptionsMenu(true);
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
                    getAllSupplier();
                }else {
                    getSearchList(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    getAllSupplier();
                    return true;
                }
                return false;
            }
        });

    }

    private void getSearchList(String query) {
        supplierAdapter.clear();
        RealmResults<SupplierModel> supplierList = realm.where(SupplierModel.class)
                .contains("supplierName", query, Case.INSENSITIVE)
                .findAll();
        RealmResults<SupplierModel> test = realm.where(SupplierModel.class)
                .findAll();

        Log.d(TAG, "onQueryTextSubmit: query : "+query);
        Log.d(TAG, "onQueryTextSubmit: test : "+test.size());
        Log.d(TAG, "onQueryTextSubmit: "+supplierList.size());
        supplierAdapter.getSupplierModelList().addAll(supplierList);
        supplierAdapter.notifyDataSetChanged();
    }

    private void getAllSupplier() {
        final RealmResults<SupplierModel> supplierModelList = realm.where(SupplierModel.class).findAll();
        Log.d(TAG,"getData : "+supplierModelList.size());

        if(supplierModelList != null && !supplierModelList.isEmpty()){
            supplierAdapter.getSupplierModelList().addAll(supplierModelList);
        }
        supplierAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        supplierAdapter.clear();
        getAllSupplier();

    }
}
