package com.example.pharmacy;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.pharmacy.adapter.SearchSupplierAdapter;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

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

    private SearchSupplierAdapter adapter;


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

            if (!text.isEmpty()) {
                getSearchSupplier(text);
            }

        });

    }

    private void getSearchSupplier(String name) {
        adapter.clear();
        final List<SupplierModel> supplierModelList = realm.where(SupplierModel.class)
                .contains("supplierName",name, Case.INSENSITIVE).findAll();

        if (!supplierModelList.isEmpty()){
            adapter.getSupplierModelList().addAll(supplierModelList);
            adapter.notifyDataSetChanged();
        }
    }

    private void getAllSupplier() {
        adapter.clear();
         RealmResults<SupplierModel> supplierModelList = realm.where(SupplierModel.class).findAll();
        // Log.d(TAG,"getData : "+medicineModelList.size());

        Log.d(TAG, "getAllSupplier: "+supplierModelList.size());
        if (!supplierModelList.isEmpty()) {
            adapter.getSupplierModelList().addAll(supplierModelList);
        }

        adapter.notifyDataSetChanged();
    }

    private void init() {
        swipe.setRefreshing(false);
        swipe.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        adapter = new SearchSupplierAdapter();
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
