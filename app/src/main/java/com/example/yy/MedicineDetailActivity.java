package com.example.yy;

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

import com.example.yy.model.MedicineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MedicineDetailActivity extends AppCompatActivity {

    private static final String TAG = "MedicineDetailActivity";

    private Realm realm;
    private MedicineModel medicineModel;

    @BindView(R.id.tvMedicineName_detail)
    TextView tvMedicineName_detail;

    @BindView(R.id.tvMedicineCode_detail)
    TextView tvMedicineCode_detail;

    @BindView(R.id.tvMedicineCategory_detail)
    TextView tvMedicineCategory_detail;

    @BindView(R.id.tvMedicineDescription_detail)
    TextView tvMedicineDescription_detail;

    @BindView(R.id.tvSellingPrice1)
    TextView tvSellingPrice1;

    @BindView(R.id.tvSellingPrice2)
    TextView tvSellingPrice2;

    @BindView(R.id.tvSellingPrice3)
    TextView tvSellingPrice3;

    @BindView(R.id.tvSellingPrice4)
    TextView tvSellingPrice4;

    @BindView(R.id.tvSellingPrice5)
    TextView tvSellingPrice5;

    @BindView(R.id.tvSupplierName_detail)
    TextView tvSupplierName_detail;

    @BindView(R.id.tvReceivedDate_detail)
    TextView tvReceivedDate_detail;

    @BindView(R.id.tvExpDate_detail)
    TextView tvExpDate_detail;

    @BindView(R.id.tvCostPrice_detail)
    TextView tvCostPrice_detail;

    @BindView(R.id.tvMedicineQty_detail)
    TextView tvMedicineQty_detail;

    @BindView(R.id.tvMedicineQtyLeft_detail)
    TextView tvMedicineQtyLeft_detail;

    @BindView(R.id.tvMedicineTotalAmt_detail)
    TextView tvMedicineTotalAmt_detail;

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
            tvMedicineCode_detail.setText(medicineModel.getMedicineCode());
            tvMedicineCategory_detail.setText(medicineModel.getMedicineCategory());
            tvMedicineDescription_detail.setText(medicineModel.getMedicineDescription());
            tvSellingPrice1.setText(medicineModel.getMedicinePrice1());
            tvSellingPrice2.setText(medicineModel.getMedicinePrice2());
            tvSellingPrice3.setText(medicineModel.getMedicinePrice3());
            tvSellingPrice4.setText(medicineModel.getMedicinePrice4());
            tvSellingPrice5.setText(medicineModel.getMedicinePrice5());
            tvSupplierName_detail.setText(medicineModel.getSupplierName());
            tvReceivedDate_detail.setText(medicineModel.getReceivedDate());
            tvExpDate_detail.setText(medicineModel.getExpDate());
            tvCostPrice_detail.setText(medicineModel.getMedicineCost());
            tvMedicineQty_detail.setText(medicineModel.getMedicineQty());
            tvMedicineQtyLeft_detail.setText(medicineModel.getMedicineQtyLeft());
            tvMedicineTotalAmt_detail.setText(medicineModel.getMedicineTotalAmt());
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
        }

        return super.onOptionsItemSelected(item);
    }
}
