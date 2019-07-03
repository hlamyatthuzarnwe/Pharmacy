package com.example.pharmacy;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.pharmacy.adapter.SearchMedicineAdapter;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;

public class SaleMedicineInformationActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleMedicineActivity";

    private Toolbar toolbar;

    //    @BindView(R.id.edtSearchMedicineInfo)
//    EditText edtSearchMedicinInfo;
    @BindView(R.id.swipeSearchMedicine)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rvMedicineList_searchMedicine)
    RecyclerView rvMedicine;

    @BindView(R.id.edtSearch_searchMedicine)
    EditText edtSearch;

    @BindView(R.id.btnSearch_searchMedicine)
    Button btnSearch;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;


    private SearchMedicineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_medicine_information);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        init();
        getAllMedicine();

        btnSearch.setOnClickListener(view -> {
            String text = edtSearch.getText().toString();
            if (!text.isEmpty()){
                getSearchMedicine(text);
            }

        });
    }

    private void init() {
        swipe.setRefreshing(false);
        swipe.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        adapter = new SearchMedicineAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        rvMedicine.setLayoutManager(linearLayoutManager);
        rvMedicine.setAdapter(adapter);
    }

    private void getAllMedicine() {
        adapter.clear();
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
        Log.d(TAG,"getData : "+medicineModelList.size());

        if (!medicineModelList.isEmpty()) {
            adapter.getMedicineModelList().addAll(medicineModelList);
        }
        adapter.notifyDataSetChanged();
    }
    private void getSearchMedicine(String name){
        adapter.clear();
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class)
                .contains("medicineName",name, Case.INSENSITIVE).findAll();
        Log.d(TAG,"getSearchData : "+medicineModelList.size());

        if (!medicineModelList.isEmpty()) {
            adapter.getMedicineModelList().addAll(medicineModelList);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
        adapter.clear();
        getAllMedicine();
    }
}
