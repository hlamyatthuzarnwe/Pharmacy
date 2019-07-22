package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleMedicineDetailInformationActivity extends AppCompatActivity {

    private static final String TAG = "SaleMedicineDetailInformationActivity";
    private Realm realm;
    private MedicineModel medicineModel;

    @BindView(R.id.edtMedicineName)
    EditText edtMedicineName;

    @BindView(R.id.edtMedicinePcCostPrice)
    EditText edtMedicinePcCostPrice;

    @BindView(R.id.edtMedicinePcQty)
    EditText edtMedicinePcQty;

    @BindView(R.id.edtMedicneSubAmt)
    EditText edtMedicneSubAmt;

    @BindView(R.id.relativeSave_medicineSaleAdd)
    RelativeLayout relativeSave_medicineSaleAdd;

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

        if (medicineModel != null){
            edtMedicineName.setText(medicineModel.getMedicineName());
            edtMedicinePcCostPrice.setText(medicineModel.getMedicineCostPerPc());
            edtMedicinePcQty.setText(medicineModel.getMedicineQtyPerPc());
            edtMedicneSubAmt.setText(medicineModel.getMedicineSubAmt());
        }

        relativeSave_medicineSaleAdd.setOnClickListener(v -> {
            String mName = edtMedicineName.getText().toString();
            String mCostPerPc = edtMedicinePcCostPrice.getText().toString();
            String mQtyPerPc = edtMedicinePcQty.getText().toString();
            String mSubAmt = edtMedicneSubAmt.getText().toString();

//            int cost = Integer.parseInt(mCostPerPc);
//
//            int qty = Integer.parseInt(mQtyPerPc);
//            int amount;
//
//            if (edtMedicinePcQty != null){
//
//                amount = cost * qty;
//                edtMedicneSubAmt.setText(Integer.toString(amount));
//            }

            MedicineModel medicineModel = new MedicineModel();

            medicineModel.setMedicineName(mName);
            medicineModel.setMedicineCostPerPc(mCostPerPc);
            medicineModel.setMedicineQtyPerPc(mQtyPerPc);
            medicineModel.setMedicineSubAmt(mSubAmt);

            realm.executeTransaction(realm1 -> {
                realm1.copyToRealmOrUpdate(medicineModel);
                Toast.makeText(SaleMedicineDetailInformationActivity.this, "Successfully Complete Sale Data", Toast.LENGTH_SHORT).show();
            });

            Intent intent = new Intent(SaleMedicineDetailInformationActivity.this,SaleAddActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
