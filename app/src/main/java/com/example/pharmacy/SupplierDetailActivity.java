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
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SupplierDetailActivity extends AppCompatActivity {

    private static final String TAG="SupplierDetailActivity";
    private Realm realm;
    private SupplierModel supplierModel;
    private Toolbar toolbar;
    private Context context;

    @BindView(R.id.tvSupplier_supplierName_detail)
    TextView tvSupplier_supplierName_detail;

    @BindView(R.id.tvCompanyName_detail)
    TextView tvCompanyName_detail;

    @BindView(R.id.tvCompanyAddress_detail)
    TextView tvCompanyAddress_detail;

    @BindView(R.id.tvSupplierPhoneNo1_detail)
    TextView tvSupplierPhoneNo1_detail;

    @BindView(R.id.tvSupplierPhoneNo2_detail)
    TextView tvSupplierPhoneNo2_detail;

    @BindView(R.id.tvSupplierPhoneNo3_detail)
    TextView tvSupplierPhoneNo3_detail;

    @BindView(R.id.tvSupplierNote_detail)
    TextView tvSupplierNote_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        supplierModel = (SupplierModel)getIntent().getParcelableExtra("SupplierModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Supplier Detail ");
        setSupportActionBar(toolbar);

        if(supplierModel != null){

            tvSupplier_supplierName_detail.setText(supplierModel.getSupplierName());
            tvCompanyName_detail.setText(supplierModel.getCompanyName());
            tvCompanyAddress_detail.setText(supplierModel.getCompanyAddress());
            tvSupplierPhoneNo1_detail.setText(supplierModel.getSuplier_phno1());
            tvSupplierPhoneNo2_detail.setText(supplierModel.getSupplier_phno2());
            tvSupplierPhoneNo3_detail.setText(supplierModel.getSupplier_phno3());
            tvSupplierNote_detail.setText(supplierModel.getSupplierNote());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {{
        getMenuInflater().inflate(R.menu.edit_detail,menu);
        return true;
    }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_data) {
            Intent intent = new Intent(SupplierDetailActivity.this, SupplierAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Supplier", supplierModel);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.delete_data) {
            String sId = supplierModel.getSuplierId();

            final SupplierModel deleteResults = realm.where(SupplierModel.class).equalTo("supplierId", sId).findFirst();
            realm.executeTransaction(realm -> {
                if (deleteResults != null) {
                    deleteResults.deleteFromRealm();
                }
                Toast.makeText(SupplierDetailActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
