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


    @BindView(R.id.tvSaleInvoiceDate_detail)
    TextView tvSaleInvoiceDate;

    @BindView(R.id.tvSaleCustomerName_detail)
    TextView tvSaleCustomerName_detail;

    @BindView(R.id.tvSaleCustomerAddress_detail)
    TextView tvSaleCustomerAddress_detail;

    @BindView(R.id.tvSaleCustomerPhNo1_detail)
    TextView tvSaleCustomerPhNo1_detail;

    @BindView(R.id.tvSaleMedicineName_detail)
    TextView tvSaleMedicineName_detail;

    @BindView(R.id.tvSalePricePerPc_detail)
    TextView tvSalePricePerPc_detail;

    @BindView(R.id.tvSaleQuantityPerPc_detail)
    TextView tvMedicineQtyPerPc;

   /* @BindView(R.id.tvMedicineQtyPerDz_detail)
    TextView tvMedicineQtyPerDz;


  @BindView(R.id.spinnerAdd)
   TextView customerLevel;*/

    @BindView(R.id.tvSaleTotalAmt_detail)
    TextView tvSaleTotalAmt_detail;

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
           // tvSaleInvoiceNo.setText(String.valueOf(saleModel.getSaleInvoiceNo()));
            tvSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            tvSaleCustomerName_detail.setText(saleModel.getSaleCustomerName());
         //   customerLevel.setText(saleModel.getSaleCustomerLevel());
            tvSaleCustomerAddress_detail.setText(saleModel.getSaleCustomerAddress());
            tvSaleCustomerPhNo1_detail.setText(saleModel.getSaleCustomerPhNo1());

            tvSaleMedicineName_detail.setText(saleModel.getSaleMedicineName());
            tvSalePricePerPc_detail.setText(saleModel.getSaleCostPerPc());
            tvMedicineQtyPerPc.setText(saleModel.getSaleQtyPerPc());
           // tvMedicineQtyPerDz.setText(saleModel.getSaleQtyPerDz());
            tvSaleTotalAmt_detail.setText(saleModel.getSaleTotalAmt());
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
