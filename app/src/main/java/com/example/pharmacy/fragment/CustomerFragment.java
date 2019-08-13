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

import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;
import com.example.pharmacy.adapter.CustomerAdapter;

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
public class CustomerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    private CustomerAdapter customerAdapter;

    @BindView(R.id.swipeCustomer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvCustomer)
    RecyclerView rvCustomer;

    @BindView(R.id.tvCount_customerFragment)
    TextView tvCount;

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
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        context = view.getContext();

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
                    getAllCustomer();
                } else {
                    getSearchList(query);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    getAllCustomer();
                    return true;
                }
                return false;
            }
        });

    }

    private void getSearchList(String query) {
        customerAdapter.clear();
        RealmResults<CustomerModel> customerList = realm.where(CustomerModel.class)
                .contains("customerName", query, Case.INSENSITIVE)
                .findAll();

       /*
        RealmResults<CustomerModel> test = realm.where(CustomerModel.class)
                .findAll();

        Log.d(TAG, "onQueryTextSubmit: query : "+query);
        Log.d(TAG, "onQueryTextSubmit: test : "+test.size());
        Log.d(TAG, "onQueryTextSubmit: "+customerList.size());
        */

        getCustomerCount(customerList.size());

        customerAdapter.getCustomerModelList().addAll(customerList);
        customerAdapter.notifyDataSetChanged();
    }



    private void getAllCustomer() {
        customerAdapter.clear();
        //final RealmResults<SupplierModel> supplierModelList = realm.where(SupplierModel.class).findAll();
        final RealmResults<CustomerModel> customerModelList = realm.where(CustomerModel.class).findAll();
       /*
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class)
                .sort("customerName", Sort.ASCENDING).findAll();
        */
        //Log.d(TAG,"getData : "+customerModelList.size());


        if (customerModelList != null && !customerModelList.isEmpty()) {
            getCustomerCount(customerModelList.size());
            customerAdapter.getCustomerModelList().addAll(customerModelList);
        }
        customerAdapter.notifyDataSetChanged();
    }

    private void getCustomerCount(int count) {
        if (count == 1) {
            tvCount.setText(String.valueOf(count) + " Customer");
        } else if (count > 1) {
            tvCount.setText(String.valueOf(count) + " Customers");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        customerAdapter.clear();
        getAllCustomer();
    }
}
