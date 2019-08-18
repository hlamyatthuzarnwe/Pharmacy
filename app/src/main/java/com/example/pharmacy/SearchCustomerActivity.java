package com.example.pharmacy;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.pharmacy.adapter.SearchCustomerAdapter;
import com.example.pharmacy.model.CustomerModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;

public class SearchCustomerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleMedicineInformationActivity";

    @BindView(R.id.swipeSearchCustomer)
    SwipeRefreshLayout swipe;

    /*
     @BindView(R.id.edtSearchMedicineInfo)
    EditText edtSearchMedicinInfo;
     */
    @BindView(R.id.rvCustomerList_searchCustomer)
    RecyclerView rvCustomer;

    @BindView(R.id.edtSearch_searchCustomer)
    EditText edtSearch;

    @BindView(R.id.btnSearch_searchCustomer)
    Button btnSearch;

    private Toolbar toolbar;
    private Realm realm;
    private LinearLayoutManager linearLayoutManager;

    private SearchCustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        init();
        getAllCustomer();

        btnSearch.setOnClickListener(view -> {
            String text = edtSearch.getText().toString();
            if (!text.isEmpty()) {
                getSearchCustomer(text);
            }
        });

    }
    private void init() {
        swipe.setRefreshing(false);
        swipe.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        adapter = new SearchCustomerAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        rvCustomer.setLayoutManager(linearLayoutManager);
        rvCustomer.setAdapter(adapter);
    }
    private void getAllCustomer() {
        adapter.clear();
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class).findAll();
        // Log.d(TAG,"getData : "+medicineModelList.size());

        if (!customerModelList.isEmpty()) {
            adapter.getCustomerModelList().addAll(customerModelList);
        }
        adapter.notifyDataSetChanged();
    }


    private void getSearchCustomer(String name) {
      //  adapter.clear();
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class)
                .contains("customerName", name, Case.INSENSITIVE).findAll();
        // Log.d(TAG,"getSearchData : "+medicineModelList.size());

        if (!customerModelList.isEmpty()) {
            adapter.getCustomerModelList().addAll(customerModelList);
            adapter.notifyDataSetChanged();
        }

    }






    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
        adapter.clear();
        getAllCustomer();
    }
}
