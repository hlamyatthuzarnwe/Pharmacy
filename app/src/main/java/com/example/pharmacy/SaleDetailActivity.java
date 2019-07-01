package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleDetailActivity extends AppCompatActivity {

    private static final String TAG = "SaleDetailActivity";
    private Realm realm;
    private SaleModel saleModel;
    private Toolbar toolbar;
    private Context context;

    @BindView(R.id.tvSaleInvoiceNo)
    TextView tvSaleInvoiceNo;

    @BindView(R.id.tvSaleInvoiceDate)
    TextView tvSaleInvoiceDate;

    @BindView(R.id.tvSaleCustomerName_detail)
    TextView tvSaleCustomerName_detail;

    @BindView(R.id.tvSaleCustomerLevel1_detail)
    TextView tvSaleCustomerLevel1_detail;

    @BindView(R.id.tvSaleCustomerLevel2_detail)
    TextView tvSaleCustomerLevel2_detail;

    @BindView(R.id.tvSaleCustomerLevel3_detail)
    TextView tvSaleCustomerLevel3_detail;

    @BindView(R.id.tvSaleCustomerLevel4_detail)
    TextView tvSaleCustomerLevel4_detail;

    @BindView(R.id.tvSaleCustomerAddress_detail)
    TextView tvSaleCustomerAddress_detail;

    @BindView(R.id.tvSaleCustomerPhNo1_detail)
    TextView tvSaleCustomerPhNo1_detail;

    @BindView(R.id.tvSaleCustomerPhNo2_detail)
    TextView tvSaleCustomerPhNo2_detail;

    @BindView(R.id.tvSaleCustomerPhNo3_detail)
    TextView tvSaleCustomerPhNo3_detail;

    @BindView(R.id.tvSaleMedicineName_detail)
    TextView tvSaleMedicineName_detail;

    @BindView(R.id.tvSaleMedicineCode_detail)
    TextView tvSaleMedicineCode_detail;

    @BindView(R.id.tvSaleCategory_detail)
    TextView tvSaleCategory_detail;

    @BindView(R.id.tvSalePricePerPc_detail)
    TextView tvSalePricePerPc_detail;

    @BindView(R.id.tvSalePricePerDz_detail)
    TextView tvSalePricePerDz_detail;

    @BindView(R.id.tvMedicineQtyPerPc)
    TextView tvMedicineQtyPerPc;

    @BindView(R.id.tvMedicineQtyPerDz)
    TextView tvMedicineQtyPerDz;

    @BindView(R.id.tvSaleSubTotalAmt_detail)
    TextView tvSaleSubTotalAmt_detail;

    @BindView(R.id.tvSaleTotalAmt_detail)
    TextView tvSaleTotalAmt_detail;

    @BindView(R.id.tvSaleUpFront_detail)
    TextView tvSaleUpFront_detail;

    @BindView(R.id.tvSaleBalance_detail)
    TextView tvSaleBalance_detail;

    @BindView(R.id.tvSaleDueDate_detail)
    TextView tvSaleDueDate_detail;

    @BindView(R.id.tvSaleNote_detail)
    TextView tvSaleNote_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_detail);


        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        saleModel = (SaleModel) getIntent().getParcelableExtra("SaleModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Detail ");
        setSupportActionBar(toolbar);

        if(saleModel != null){
            tvSaleInvoiceNo.setText(String.valueOf(saleModel.getSaleInvoiceNo()));
            tvSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            tvSaleCustomerName_detail.setText(saleModel.getSaleCustomerName());
            tvSaleCustomerLevel1_detail.setText(saleModel.getSaleCustomerLevel1());
            tvSaleCustomerLevel2_detail.setText(saleModel.getSaleCustomerLevel2());
            tvSaleCustomerLevel3_detail.setText(saleModel.getSaleCustomerLevel3());
            tvSaleCustomerLevel4_detail.setText(saleModel.getSaleCustomerLevel4());
            tvSaleCustomerAddress_detail.setText(saleModel.getSaleCustomerAddress());
            tvSaleCustomerPhNo1_detail.setText(saleModel.getSaleCustomerPhNo1());
            tvSaleCustomerPhNo2_detail.setText(saleModel.getSaleCustomerPhNo2());
            tvSaleCustomerPhNo3_detail.setText(saleModel.getSaleCustomerPhNo3());
            tvSaleMedicineName_detail.setText(saleModel.getSaleMedicineName());
            tvSaleMedicineCode_detail.setText(saleModel.getSaleMedicineCode());
            tvSaleCategory_detail.setText(saleModel.getSaleCategory());
            tvSalePricePerPc_detail.setText(saleModel.getSaleCostPerPc());
            tvSalePricePerDz_detail.setText(saleModel.getSaleCostPerDz());
            tvMedicineQtyPerPc.setText(saleModel.getSaleQtyPerPc());
            tvMedicineQtyPerDz.setText(saleModel.getSaleQtyPerDz());
            tvSaleSubTotalAmt_detail.setText(saleModel.getSaleSubTotalAmt());
            tvSaleTotalAmt_detail.setText(saleModel.getSaleTotalAmt());
            tvSaleUpFront_detail.setText(saleModel.getSaleUpFront());
            tvSaleBalance_detail.setText(saleModel.getSaleBalance());
            tvSaleDueDate_detail.setText(saleModel.getSaleDuedate());
            tvSaleNote_detail.setText(saleModel.getSaleNote());
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
            Intent intent = new Intent(SaleDetailActivity.this,SaleAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Sale",saleModel);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.delete_data){
            String sId = saleModel.getSaleInvoiceNo();

            final SaleModel deleteResults = realm.where(SaleModel.class).equalTo("saleInvoiceNo", sId).findFirst();
            realm.executeTransaction(realm -> {
                if (deleteResults != null) {
                    deleteResults.deleteFromRealm();
                }
                Toast.makeText(SaleDetailActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
