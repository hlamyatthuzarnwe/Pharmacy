package com.example.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pharmacy.helper.MedicineModelList;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class SaleMedicineDetailInformationActivity extends AppCompatActivity {

    private static final String TAG = "SaleMedicineDetailInformationActivity";
    private Realm realm;
    private MedicineModel medicineModel;

    @BindView(R.id.edtSaleMedicineName)
    EditText edtSaleMedicineName;

    @BindView(R.id.edtSaleMedicinePcPrice1)
    EditText edtSaleMedicinePcPrice1;

    @BindView(R.id.edtMedicinePcQty)
    EditText edtMedicinePcQty;

    @BindView(R.id.edtMedicneSubAmt)
    EditText edtMedicneSubAmt;

    @BindView(R.id.relativeSave_medicineSaleAdd)
    RelativeLayout relativeSave_medicineSaleAdd;

    private Toolbar toolbar;
    private Context context;
    private RealmList<MedicineModel> medicineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_medicine_detail_information);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        medicineList = MedicineModelList.getInstance();
        medicineModel = (MedicineModel) getIntent().getParcelableExtra("MedicineModel");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine Detail ");
        setSupportActionBar(toolbar);

        if (medicineModel != null) {
            edtSaleMedicineName.setText(medicineModel.getMedicineName());
            edtSaleMedicinePcPrice1.setText(medicineModel.getMedicineSalePcPerPrice1());
            //  edtMedicinePcQty.setText(medicineModel.getMedicineQtyPerPc());
            edtMedicneSubAmt.setText(medicineModel.getMedicineSubAmt());
        }
        edtMedicinePcQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int price = 0;
                if (edtSaleMedicinePcPrice1.getText() != null &&
                        !edtSaleMedicinePcPrice1.getText().toString().equals("")) {
                    price = Integer.parseInt(edtSaleMedicinePcPrice1.getText().toString());
                }
                int qty = 0;
                if (edtMedicinePcQty.getText() != null &&
                        !edtMedicinePcQty.getText().toString().equals("")) {
                    qty = Integer.parseInt(edtMedicinePcQty.getText().toString());
                }
                int total = price * qty;
                edtMedicneSubAmt.setText(String.valueOf(total));
            }
        });

        relativeSave_medicineSaleAdd.setOnClickListener(v -> {
            String mName = edtSaleMedicineName.getText().toString();
            String mCostPerPc = edtSaleMedicinePcPrice1.getText().toString();
            String mQtyPerPc = edtMedicinePcQty.getText().toString();
            String mSubAmt = edtMedicneSubAmt.getText().toString();

//            int cost = Integer.parseInt(mCostPerPc);
//
//            int qty = Integer.parseInt(mQtyPerPc);
//            int amount;
//
//            if (edtMedicinePcQty != null){
//
//                amount = cost * qty;
//                edtMedicneSubAmt.setText(Integer.toString(amount));
//            }

            MedicineModel medicineModel = new MedicineModel();

            medicineModel.setMedicineName(mName);
            medicineModel.setMedicineCostPerPc(mCostPerPc);
            medicineModel.setMedicineQtyPerPc(mQtyPerPc);
            medicineModel.setMedicineSubAmt(mSubAmt);
            Log.d("SaleAdd", "onCreate: SubAmt : " + medicineModel.getMedicineSubAmt());
            medicineList.add(medicineModel);
            Toast.makeText(SaleMedicineDetailInformationActivity.this, "Successfully Complete Sale Data", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(SaleMedicineDetailInformationActivity.this, SaleAddActivity.class);
            startActivity(intent);
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
