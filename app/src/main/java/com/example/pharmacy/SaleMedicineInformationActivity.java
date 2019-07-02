package com.example.pharmacy;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleMedicineInformationActivity extends AppCompatActivity {

    private static final String TAG = "SaleMedicineInformationActivity";

    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;

//    @BindView(R.id.edtSearchMedicineInfo)
//    EditText edtSearchMedicinInfo;

    @BindView(R.id.rvMedicine)
    RecyclerView rvMedicine;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;

    private String idEdit;
    private boolean is_edit=false;
    private MedicineAdapter medicineAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_medicine_information);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        medicineModel = (MedicineModel) getIntent().getParcelableExtra("Medicine");

        init();
        getAllMedicine();
    }

    private void init() {
      // swipeRefreshLayout.setRefreshing(false);
       //swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        medicineAdapter = new MedicineAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvMedicine.setLayoutManager(linearLayoutManager);
        rvMedicine.setAdapter(medicineAdapter);
    }

    private void getAllMedicine() {
        final List<MedicineModel> medicineModelList = realm.where(MedicineModel.class).findAll();
      //  Log.d(TAG,"getData : "+medicineModelList.size());

        if(medicineModelList != null && !medicineModelList.isEmpty()){
            medicineAdapter.getMedicineModelList().addAll(medicineModelList);
        }
        medicineAdapter.notifyDataSetChanged();
    }

}
