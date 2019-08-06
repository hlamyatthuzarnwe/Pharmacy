package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class CustomerDetailActivity extends AppCompatActivity {

    private static final String TAG = "CustomerDetailActivity";
    private Realm realm;
    private CustomerModel customerModel;
    private Toolbar toolbar;
    private Context context;

    @BindView(R.id.tvCustomerInvoiceDate_detail)
    TextView tvCustomerInvoiceDate_detail;

    @BindView(R.id.tvCustomerName_detail)
    TextView tvCustomerName_detail;

    @BindView(R.id.tvCustomerAddress_detail)
    TextView tvCustomerAddress_detail;

    @BindView(R.id.tvCustomerPhNo1_detail)
    TextView tvCustomerPhNo1_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        customerModel = (CustomerModel) getIntent().getParcelableExtra("CustomerModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Detail ");
        setSupportActionBar(toolbar);

        if (customerModel != null) {
            SaleModel saleModel = realm.where(SaleModel.class)
                    .equalTo("customerModel.customerId", customerModel.getCustomerId())
                    .findFirst();

            if (saleModel != null){
                tvCustomerInvoiceDate_detail.setText(saleModel.getSaleInvoiceDate());
                Log.d(TAG, "onCreate: InvoiceDate : " + saleModel.getSaleInvoiceDate());
            }

            tvCustomerName_detail.setText(customerModel.getCustomerName());

            //  spinnerItemCustomerLevel.setText(customerModel.getCustomerLevel());
//            tvCustomerLevel_detail.setText(customerModel.getCustomerLevel());
            tvCustomerAddress_detail.setText(customerModel.getCustomerAddress());
            tvCustomerPhNo1_detail.setText(customerModel.getCustomerPhNo1());
            // tvCustomerNote_detail.setText(customerModel.getCustomerNote());
            // Log.d(TAG, "onCreate: note : "+customerModel.getCustomerNote());


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_data) {
            Intent intent = new Intent(CustomerDetailActivity.this, CustomerAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Customer", customerModel);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.delete_data) {
            String cId = customerModel.getCustomerId();

            final CustomerModel deleteResults = realm.where(CustomerModel.class).equalTo("customerId", cId).findFirst();
            realm.executeTransaction(realm -> {
                if (deleteResults != null) {
                    deleteResults.deleteFromRealm();
                }
                Toast.makeText(CustomerDetailActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
