package com.example.pharmacy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleMedicineDetailInformationActivity extends AppCompatActivity {

    private static final String TAG = "SaleMedicineDetailInformationActivity";
    private Realm realm;
    private MedicineModel medicineModel;

    @BindView(R.id.edtMedicineName_saleDetail)
    EditText edtMedicineName_saleDetail;

    @BindView(R.id.edtMedicineCategory_saleDetail)
    EditText edtMedicineCategory_saleDetail;

    @BindView(R.id.edtMedicineQty_saleDetail)
    EditText edtMedicineQty_saleDetail;

    @BindView(R.id.edtMedicineCostPrice_saleDetail)
    EditText edtMedicineCostPrice_saleDetail;

    @BindView(R.id.edtMedicineSubAmt_saleDetail)
    EditText edtMedicineSubAmt_saleDetail;

    @BindView(R.id.edtMedicineTotalAmt_saleDetail)
    EditText edtMedicineTotalAmt_saleDetail;

    @BindView(R.id.edtMedicineUpFront_saleDetail)
    EditText edtMedicineUpFront_saleDetail;

    @BindView(R.id.edtMedicineBalance_saleDetail)
    EditText edtMedicineBalance_saleDetail;

    @BindView(R.id.edtSaleNote_saleDetail)
    EditText edtSaleNote_saleDetail;

    private Toolbar toolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_medicine_detail_information);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        medicineModel = (MedicineModel)getIntent().getParcelableExtra("MedicineModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine Detail ");
        setSupportActionBar(toolbar);
    }
}
