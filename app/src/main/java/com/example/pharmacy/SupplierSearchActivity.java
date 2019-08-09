package com.example.pharmacy;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.pharmacy.adapter.SearchCustomerAdapter;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SupplierSearchActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SearchSupplierActivity";


    @BindView(R.id.swipeSearchSupplier)
    SwipeRefreshLayout swipe;

    /*
     @BindView(R.id.edtSearchMedicineInfo)
    EditText edtSearchMedicinInfo;
     */
    @BindView(R.id.rvSupplierList_searchSupplier)
    RecyclerView rvSupplier;

    @BindView(R.id.edtSearch_searchSupplier)
    EditText edtSearch;

    @BindView(R.id.btnSearch_searchSupplier)
    Button btnSearch;

    private Toolbar toolbar;
    private Realm realm;
    private LinearLayoutManager linearLayoutManager;

    private SearchCustomerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_search);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        init();
        getAllSupplier();

        btnSearch.setOnClickListener(view -> {
            String text = edtSearch.getText().toString();
           /*
            if (!text.isEmpty()) {
                getSearchMedicine(text);
            }
            */
        });

    }

    private void getAllSupplier() {
        adapter.clear();
        final List<SupplierModel> supplierModelList = realm.where(SupplierModel.class).findAll();
        // Log.d(TAG,"getData : "+medicineModelList.size());

        /*
        if (!supplierModelList.isEmpty()) {
            adapter.getCustomerModelList().addAll(supplierModelList);
        }
         */
        adapter.notifyDataSetChanged();
    }

    private void init() {
        swipe.setRefreshing(false);
        swipe.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        adapter = new SearchCustomerAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        rvSupplier.setLayoutManager(linearLayoutManager);
        rvSupplier.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
        adapter.clear();
        getAllSupplier();
    }
}
