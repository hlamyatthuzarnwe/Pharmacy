package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

    @BindView(R.id.tvMedicineSupplierName)
    TextView tvSupplierName_detail;

    @BindView(R.id.tvCompanyName_detail)
    TextView tvCompanyName_detail;

    @BindView(R.id.tvCompanyAddress_detail)
    TextView tvCompanyAddress_detail;

    @BindView(R.id.tvCustomerPhNo1_detail)
    TextView tvCustomerPhNo1_detail;

    @BindView(R.id.tvCustomerPhNo2_detail)
    TextView tvCustomerPhNo2_detail;

    @BindView(R.id.tvCustomerPhNo3_detail)
    TextView tvCustomerPhNo3_detail;

    @BindView(R.id.tvCustomerPhNo4_detail)
    TextView tvCustomerPhNo4_detail;

    @BindView(R.id.tvCustomerPhNo5_detail)
    TextView tvCustomerPhNo5_detail;

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

            tvSupplierName_detail.setText(supplierModel.getSupplierName());
            tvCompanyName_detail.setText(supplierModel.getCompanyName());
            tvCompanyAddress_detail.setText(supplierModel.getCompanyAddress());
            tvCustomerPhNo1_detail.setText(supplierModel.getSuplier_phno1());
            tvCustomerPhNo2_detail.setText(supplierModel.getSupplier_phno2());
            tvCustomerPhNo3_detail.setText(supplierModel.getSupplier_phno3());
            tvCustomerPhNo4_detail.setText(supplierModel.getSupplier_phno4());
            tvCustomerPhNo5_detail.setText(supplierModel.getSupplier_phno5());
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

        if(item.getItemId() == R.id.edit_data)
        {
            Intent intent = new Intent(SupplierDetailActivity.this,SupplierAddActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Supplier",supplierModel);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
