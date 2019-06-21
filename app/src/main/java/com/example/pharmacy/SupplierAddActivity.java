package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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
    private int sId;

    @BindView(R.id.edtSupplierName_add)
    EditText edtSupplierName_add;

    @BindView(R.id.edtCompanyName_add)
    EditText edtCompanyName_add;

    @BindView(R.id.edtCompanyAddress_add)
    EditText edtCompanyAddress_add;

    @BindView(R.id.edtSupplierPhNo1)
    EditText edtSupplierPhNo1;

    @BindView(R.id.edtSupplierPhNo2)
    EditText edtSupplierPhNo2;

    @BindView(R.id.edtSupplierPhNo3)
    EditText edtSupplierPhNo3;

    @BindView(R.id.edtSupplierPhNo4)
    EditText edtSupplierPhNo4;

    @BindView(R.id.edtSupplierPhNo5)
    EditText edtSupplierPhNo5;

    @BindView(R.id.edtSupplierNote_add)
    EditText edtSupplierNote_add;

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

            edtSupplierName_add.setText(supplierModel.getSupplierName());
            edtCompanyName_add.setText(supplierModel.getCompanyName());
            edtCompanyAddress_add.setText(supplierModel.getCompanyAddress());
            edtSupplierPhNo1.setText(supplierModel.getSuplier_phno1());
            edtSupplierPhNo2.setText(supplierModel.getSupplier_phno2());
            edtSupplierPhNo3.setText(supplierModel.getSupplier_phno3());
            edtSupplierPhNo4.setText(supplierModel.getSupplier_phno4());
            edtSupplierPhNo5.setText(supplierModel.getSupplier_phno5());
            edtSupplierNote_add.setText(supplierModel.getSupplierNote());
        }

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
        if(item.getItemId() == R.id.menu_add){
            String sName = edtSupplierName_add.getText().toString();
            String sCompanyName = edtCompanyName_add.getText().toString();
            String sCompanyAddress = edtCompanyAddress_add.getText().toString();
            String sPhNo1 = edtSupplierPhNo1.getText().toString();
            String sPhNo2 = edtSupplierPhNo2.getText().toString();
            String sPhNo3 = edtSupplierPhNo3.getText().toString();
            String sPhNo4 = edtSupplierPhNo4.getText().toString();
            String sPhNo5 = edtSupplierPhNo5.getText().toString();
            String sNote = edtSupplierNote_add.getText().toString();

            SupplierModel supplierModel = new SupplierModel();

            supplierModel.setSuplierId(UUID.randomUUID().toString());
            supplierModel.setSupplierName(sName);
            supplierModel.setCompanyName(sCompanyName);
            supplierModel.setCompanyAddress(sCompanyAddress);
            supplierModel.setSuplier_phno1(sPhNo1);
            supplierModel.setSupplier_phno2(sPhNo2);
            supplierModel.setSupplier_phno3(sPhNo3);
            supplierModel.setSupplier_phno4(sPhNo4);
            supplierModel.setSupplier_phno5(sPhNo5);
            supplierModel.setSupplierNote(sNote);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(supplierModel);
                Toast.makeText(SupplierAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_update){
            String sName = edtSupplierName_add.getText().toString();
            String sCompanyName = edtCompanyName_add.getText().toString();
            String sCompanyAddress = edtCompanyAddress_add.getText().toString();
            String sPhNo1 = edtSupplierPhNo1.getText().toString();
            String sPhNo2 = edtSupplierPhNo2.getText().toString();
            String sPhNo3 = edtSupplierPhNo3.getText().toString();
            String sPhNo4 = edtSupplierPhNo4.getText().toString();
            String sPhNo5 = edtSupplierPhNo5.getText().toString();
            String sNote = edtSupplierNote_add.getText().toString();

            SupplierModel supplierModel = new SupplierModel();

            supplierModel.setSuplierId(supplierModel.getSuplierId());
            supplierModel.setSupplierName(sName);
            supplierModel.setCompanyName(sCompanyName);
            supplierModel.setCompanyAddress(sCompanyAddress);
            supplierModel.setSuplier_phno1(sPhNo1);
            supplierModel.setSupplier_phno2(sPhNo2);
            supplierModel.setSupplier_phno3(sPhNo3);
            supplierModel.setSupplier_phno4(sPhNo4);
            supplierModel.setSupplier_phno5(sPhNo5);
            supplierModel.setSupplierNote(sNote);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(supplierModel);
                Toast.makeText(SupplierAddActivity.this, "Successfully Update Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_delete){

           String sId = supplierModel.getSuplierId();
            final  SupplierModel deleteResults = realm.where(SupplierModel.class).equalTo("supplierId",sId).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if(deleteResults != null){
                        deleteResults.deleteFromRealm();;
                    }
                    Toast.makeText(SupplierAddActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        if(item.getItemId() == R.id.menu_cancel){

            edtSupplierName_add.setText("");
            edtCompanyName_add.setText("");
            edtCompanyAddress_add.setText("");
            edtSupplierPhNo1.setText("");
            edtSupplierPhNo2.setText("");
            edtSupplierPhNo3.setText("");
            edtSupplierPhNo4.setText("");
            edtSupplierPhNo5.setText("");
            edtSupplierNote_add.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}
