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

import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SupplierAddActivity extends AppCompatActivity {

    private static final String TAG="SupplierAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private SupplierModel supplierModel;
    private Realm realm;
    private Toolbar toolbar;
    private Context context;
    private  String idEdit;

    @BindView(R.id.edtSupplierName_add)
    EditText edtSupplierName_add;

    @BindView(R.id.edtCompanyName_add)
    EditText edtCompanyName_add;

    @BindView(R.id.edtCompanyAddress_add)
    EditText edtCompanyAddress_add;

    @BindView(R.id.edtSupplierPhNo1)
    EditText edtSupplierPhNo1;

    @BindView(R.id.relativeSave_supplierAdd)
    RelativeLayout relativeSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        supplierModel = (SupplierModel)getIntent().getParcelableExtra("Supplier");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Supplier Data");
        setSupportActionBar(toolbar);

        if(supplierModel != null){
            idEdit = supplierModel.getSuplierId();
            edtSupplierName_add.setText(supplierModel.getSupplierName());
            edtCompanyName_add.setText(supplierModel.getCompanyName());
            edtCompanyAddress_add.setText(supplierModel.getCompanyAddress());
            edtSupplierPhNo1.setText(supplierModel.getSuplier_phno1());

        }
        relativeSave.setOnClickListener(v -> {
            String sName = edtSupplierName_add.getText().toString();
            String sCompanyName = edtCompanyName_add.getText().toString();
            String sCompanyAddress = edtCompanyAddress_add.getText().toString();
            String sPhNo1 = edtSupplierPhNo1.getText().toString();

            SupplierModel supplierModel = new SupplierModel();

            supplierModel.setSuplierId(UUID.randomUUID().toString());
            supplierModel.setSupplierName(sName);
            supplierModel.setCompanyName(sCompanyName);
            supplierModel.setCompanyAddress(sCompanyAddress);
            supplierModel.setSuplier_phno1(sPhNo1);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(supplierModel);
                Toast.makeText(SupplierAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

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
