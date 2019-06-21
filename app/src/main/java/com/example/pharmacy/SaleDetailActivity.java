package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

    @BindView(R.id.tvSaleCustomerLevel_detail)
    TextView tvSaleCustomerLevel_detail;

    @BindView(R.id.tvSaleCustomerAddress_detail)
    TextView tvSaleCustomerAddress_detail;

    @BindView(R.id.tvSaleCustomerPhNo_detail)
    TextView tvSaleCustomerPhNo_detail;

    @BindView(R.id.tvSaleMedicineName_detail)
    TextView tvSaleMedicineName_detail;

    @BindView(R.id.tvSaleMedicineCode_detail)
    TextView tvSaleMedicineCode_detail;

    @BindView(R.id.tvSaleCategory_detail)
    TextView tvSaleCategory_detail;

    @BindView(R.id.tvSalePrice_detail)
    TextView tvSalePrice_detail;

    @BindView(R.id.tvSaleDiscount_detail)
    TextView tvSaleDiscount_detail;

    @BindView(R.id.tvSaleQuantity_detail)
    TextView tvSaleQuantity_detail;

    @BindView(R.id.tvSaleSubTotalAmt_detail)
    TextView tvSaleSubTotalAmt_detail;

    @BindView(R.id.tvSaleTotalAmt_detail)
    TextView tvSaleTotalAmt_detail;

    @BindView(R.id.tvSaleDueDate_detail)
    TextView tvSaleDueDate_detail;

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
            tvSaleCustomerPhNo_detail.setText(String.valueOf(saleModel.getSaleInvoiceNo()));
            tvSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            tvSaleCustomerName_detail.setText(saleModel.getSaleCustomerName());
            tvSaleCustomerLevel_detail.setText(saleModel.getSaleCustomerLevel());
            tvSaleCustomerAddress_detail.setText(saleModel.getSaleCustomerAddress());
            tvSaleCustomerPhNo_detail.setText(saleModel.getSaleCustomerPhNo());
            tvSaleMedicineName_detail.setText(saleModel.getSaleMedicineName());
            tvSaleMedicineCode_detail.setText(saleModel.getSaleMedicineCode());
            tvSaleCategory_detail.setText(saleModel.getSaleCategory());
            tvSalePrice_detail.setText(saleModel.getSalePrice());
            tvSaleDiscount_detail.setText(saleModel.getSaleDiscount());
            tvSaleQuantity_detail.setText(saleModel.getSaleQty());
            tvSaleSubTotalAmt_detail.setText(saleModel.getSaleSubTotalAmt());
            tvSaleTotalAmt_detail.setText(saleModel.getSaleTotalAmt());
            tvSaleDueDate_detail.setText(saleModel.getSaleDuedate());
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

        return super.onOptionsItemSelected(item);
    }
}
