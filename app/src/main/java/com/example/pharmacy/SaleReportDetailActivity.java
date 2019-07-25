package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleReportDetailActivity extends AppCompatActivity {

    private static final String TAG = "SaleReportDetailActivit";
    private Realm realm;
    private Toolbar toolbar;
    private Context context;

    @BindView(R.id.tvSaleReportSaleInvoiceNo_detail)
    TextView tvSaleReportSaleInvoiceNo_detail;

    @BindView(R.id.tvSaleReportSaleInvoiceDate_detail)
    TextView tvSaleReportSaleInvoiceDate_detail;

    @BindView(R.id.tvSaleReportCustomerName_dateil)
    TextView tvSaleReportCustomerName_dateil;

    @BindView(R.id.tvSaleReportAmount_detail)
    TextView tvSaleReportAmount_detail;

    @BindView(R.id.tvSaleReportProfit_detail)
    TextView tvSaleReportProfit_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report_detail);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SaleReportActivity Detail ");
        setSupportActionBar(toolbar);


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
            Intent intent = new Intent(SaleReportDetailActivity.this,SaleReportAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
