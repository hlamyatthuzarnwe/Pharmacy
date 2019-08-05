package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MedicineDetailActivity extends AppCompatActivity {

    private static final String TAG = "MedicineDetailActivity";

    private Realm realm;
    private MedicineModel medicineModel;

    @BindView(R.id.tvMedicineName_detail)
    TextView tvMedicineName_detail;

    @BindView(R.id.tvMedicineCategory_detail)
    TextView tvMedicineCategory_detail;

   @BindView(R.id.tvMedicineCostPerPc)
   TextView tvMedicineCostPerPc;


   @BindView(R.id.tvMedicineQtyPerPc)
   TextView tvMedicineQtyPerPc;

   @BindView(R.id.tvMedicineCompanyName)
   TextView tvMedicineCompanyName;

   @BindView(R.id.tvMedicineCompanyAddress)
   TextView tvMedicineCompanyAddress;

   @BindView(R.id.tvMedicineSupplierName)
   TextView tvMedicineSupplierName;

   @BindView(R.id.tvMedicinePh1)
   TextView tvMedicinePh1;


    @BindView(R.id.tvMedicineSalePerPc1)
    TextView tvMedicineSalePerPc1;

    @BindView(R.id.tvMedicineReceivedDate)
    TextView tvMedicineReceivedDate;

    @BindView(R.id.tvMedicineExpDate)
    TextView tvMedicineExpDate;

    @BindView(R.id.tvMedicineNote)
    TextView tvMedicineNote;

    private Toolbar toolbar;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        medicineModel = (MedicineModel)getIntent().getParcelableExtra("MedicineModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine Detail ");
        setSupportActionBar(toolbar);

        if(medicineModel != null){

            tvMedicineName_detail.setText(medicineModel.getMedicineName());
            tvMedicineCategory_detail.setText(medicineModel.getMedicineCategory());
            tvMedicineCostPerPc.setText(medicineModel.getMedicineCostPerPc());
            tvMedicineQtyPerPc.setText(medicineModel.getMedicineQtyPerPc());

            if (medicineModel.getSupplierModel() != null){
                tvMedicineCompanyName.setText(medicineModel.getSupplierModel().getCompanyName());
                tvMedicineCompanyAddress.setText(medicineModel.getSupplierModel().getCompanyAddress());
                tvMedicineSupplierName.setText(medicineModel.getSupplierModel().getSupplierName());
                tvMedicinePh1.setText(medicineModel.getSupplierModel().getSuplier_phno1());

            }
          //  tvMedicinePayment.setText(medicineModel.getMedicinePayment());
            tvMedicineSalePerPc1.setText(medicineModel.getMedicineSalePcPerPrice1());

            tvMedicineReceivedDate.setText(medicineModel.getMedicineReceivedDate());
            tvMedicineExpDate.setText(medicineModel.getMedicineExpDate());
            tvMedicineNote.setText(medicineModel.getMedicineNote());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.edit_data)
        {
            Intent intent = new Intent(MedicineDetailActivity.this,MedicineAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Medicine",medicineModel);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.delete_data){
            String mId = medicineModel.getMedcineId();

            final MedicineModel deleteResults = realm.where(MedicineModel.class).equalTo("medcineId", mId).findFirst();
            realm.executeTransaction(realm -> {
                if (deleteResults != null) {
                    deleteResults.deleteFromRealm();
                }
                Toast.makeText(MedicineDetailActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
