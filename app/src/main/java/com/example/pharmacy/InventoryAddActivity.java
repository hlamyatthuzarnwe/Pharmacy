package com.example.pharmacy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import butterknife.BindView;
import io.realm.Realm;

public class InventoryAddActivity extends AppCompatActivity {

    private static final String TAG = "InventoryAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;
    int mId;

    @BindView(R.id.edtInventoryCustomerName)
    EditText edtInventoryCustomerName;

    @BindView(R.id.edtInventoryMedicineCode)
    EditText edtInventoryMedicineCode;

    @BindView(R.id.edtInventoryMedicineQty)
    EditText edtInventoryMedicineQty;

    @BindView(R.id.edtInventoryExpDate)
    EditText edtInventoryExpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);
    }
}
