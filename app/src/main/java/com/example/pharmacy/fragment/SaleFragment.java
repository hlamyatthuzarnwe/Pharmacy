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
import android.widget.TextView;

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
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleFragment";
    private SaleAdapter saleAdapter;


    @BindView(R.id.swipeSale)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvSale)
    RecyclerView rvSale;

    @BindView(R.id.tvCount_saleFragment)
    TextView tvCount;


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
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        context = view.getContext();

        init();
        getAllSale();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search by Name");
        searchView.setScrollBarSize(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    getAllSale();
                } else {
                    getSearchList(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    getAllSale();
                    return true;
                }
                return false;
            }
        });

    }

    private void getSearchList(String query) {
        saleAdapter.clear();
        RealmResults<SaleModel> saleList = realm.where(SaleModel.class)
                .contains("medicineName", query, Case.INSENSITIVE)
                .findAll();
        RealmResults<SaleModel> test = realm.where(SaleModel.class)
                .findAll();

        /*
        Log.d(TAG, "onQueryTextSubmit: query : "+query);
        Log.d(TAG, "onQueryTextSubmit: test : "+test.size());
        Log.d(TAG, "onQueryTextSubmit: "+saleList.size());
         */

        getSaleCount(saleList.size());

        saleAdapter.getSaleModelList().addAll(saleList);
        saleAdapter.notifyDataSetChanged();
    }


    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        saleAdapter = new SaleAdapter();

        linearLayoutManager = new LinearLayoutManager(context);
        rvSale.setLayoutManager(linearLayoutManager);
        rvSale.setAdapter(saleAdapter);

    }

    private void getAllSale() {
        saleAdapter.clear();
        final List<SaleModel> saleModelList = realm.where(SaleModel.class).findAll();
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class).findAll();
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
        // Log.d(TAG,"getData : "+saleModelList.size());


        if (saleModelList != null && !saleModelList.isEmpty()) {
            getSaleCount(saleModelList.size());
            saleAdapter.getSaleModelList().addAll(saleModelList);
        }
        saleAdapter.notifyDataSetChanged();

    }


    private void getSaleCount(int count) {
        if (count == 1) {
            tvCount.setText(String.valueOf(count) + " Sale Item");
        } else if (count > 1) {
            tvCount.setText(String.valueOf(count) + " Sale Items");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        saleAdapter.clear();
        getAllSale();

    }
}
