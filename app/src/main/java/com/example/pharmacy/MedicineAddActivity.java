package com.example.pharmacy;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MedicineAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MedicineAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;
    String mName;

    @BindView(R.id.edtMedicineName)
    EditText edtMedicineName;

    @BindView(R.id.edtMedicineCode)
    EditText edtMedicineCode;

    @BindView(R.id.edtMedicineCategory)
    EditText edtMedicineCategory;

    @BindView(R.id.edtMedicinePcCostPrice)
    EditText edtMedicinePcCostPrice;

    @BindView(R.id.edtMedicneDzCostPrice)
    EditText edtMedicneDzCostPrice;

    @BindView(R.id.edtMedicinePcQty)
    EditText edtMedicinePcQty;


    @BindView(R.id.edtMedicineCompanyName)
    EditText edtMedicineCompanyName;

    @BindView(R.id.edtSupplierName)
    EditText edtSupplierName;

    @BindView(R.id.edtContactMedicinePh1)
    EditText edtContactMedicinePh1;

    @BindView(R.id.edtContactMedicinePh2)
    EditText edtContactMedicinePh2;

    @BindView(R.id.edtMedicineContactPh3)
    EditText edtMedicineContactPh3;

    @BindView(R.id.edtMedicineViberPh)
    EditText edtMedicineViberPh;

    @BindView(R.id.edtMedicinePayment)
    EditText edtMedicinePayment;

    @BindView(R.id.edtSaleMedicinePcPrice1)
    EditText edtSaleMedicinePcPrice1;

    @BindView(R.id.edtSaleMedicineDzPrice1)
    EditText edtSaleMedicineDzPrice1;

    @BindView(R.id.edtSaleMedicinePcPrice2)
    EditText edtSaleMedicinePcPrice2;

    @BindView(R.id.edtSaleMedicineDzPrice2)
    EditText edtSaleMedicineDzPrice2;

    @BindView(R.id.edtSaleMedicinePcPrice3)
    EditText edtSaleMedicinePcPrice3;

    @BindView(R.id.edtSaleMedicineDzPrice3)
    EditText edtSaleMedicineDzPrice3;

    @BindView(R.id.edtSaleMedicinePcPrice4)
    EditText edtSaleMedicinePcPrice4;

    @BindView(R.id.edtSaleMedicineDzCostPrice4)
    EditText edtSaleMedicineDzCostPrice4;

    @BindView(R.id.edtMedicineReceivedDate)
    EditText edtMedicineReceivedDate;

    @BindView(R.id.edtMedicineExpDate)
    EditText edtMedicineExpDate;

    @BindView(R.id.edtMedicineNote)
    EditText edtMedicineNote;

    @BindView(R.id.edtAddress_medicineAdd)
    EditText edtAddress;

    @BindView(R.id.relativeSave_medicineAdd)
    RelativeLayout relativeSave;

    @BindView(R.id.spinnerAdd)
    Spinner spinnerAdd;

   private SupplierModel supplierModel;
   private String idEdit;
   private boolean is_edit=false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setUpReceivedDateTime();
        setUpExpireDateTime();

        medicineModel = (MedicineModel)getIntent().getParcelableExtra("Medicine");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine Data");
        setSupportActionBar(toolbar);

        if(medicineModel != null){
            idEdit = medicineModel.getMedcineId();
            if (idEdit.isEmpty()){
                is_edit = false;
            }else {
                is_edit = true;
            }
            edtMedicineName.setText(medicineModel.getMedicineName());
            edtMedicineCode.setText(medicineModel.getMedicineCode());
            edtMedicineCategory.setText(medicineModel.getMedicineCategory());
            edtMedicinePcCostPrice.setText(medicineModel.getMedicineCostPerPc());
            edtMedicneDzCostPrice.setText(medicineModel.getMedicineCostPerDz());
            edtMedicinePcQty.setText(medicineModel.getMedicineQtyPerPc());
            if (medicineModel.getSupplierModel() != null){
                edtMedicineCompanyName.setText(medicineModel.getSupplierModel().getCompanyName());
                edtAddress.setText(medicineModel.getSupplierModel().getCompanyAddress());
                edtSupplierName.setText(medicineModel.getSupplierModel().getSupplierName());
                edtContactMedicinePh1.setText(medicineModel.getSupplierModel().getSuplier_phno1());
                edtContactMedicinePh2.setText(medicineModel.getSupplierModel().getSupplier_phno2());
                edtMedicineContactPh3.setText(medicineModel.getSupplierModel().getSupplier_phno3());
            }

            edtMedicineViberPh.setText(medicineModel.getMedicineViberPh());
            edtMedicinePayment.setText(medicineModel.getMedicinePayment());
            edtSaleMedicinePcPrice1.setText(medicineModel.getMedicineSalePcPerPrice1());
            edtSaleMedicineDzPrice1.setText(medicineModel.getMedicineSaleDzPerPrice1());
            edtSaleMedicinePcPrice2.setText(medicineModel.getMedicineSalePcPerPrice2());
            edtSaleMedicineDzPrice2.setText(medicineModel.getMedicineSaleDzPerPrice2());
            edtSaleMedicinePcPrice3.setText(medicineModel.getMedicineSalePcPerPrice3());
            edtSaleMedicineDzPrice3.setText(medicineModel.getMedicineSaleDzPerPrice3());
            edtSaleMedicinePcPrice4.setText(medicineModel.getMedicineSalePcPerPrice4());
            edtSaleMedicineDzCostPrice4.setText(medicineModel.getMedicineSaleDzPerPrice4());
            edtMedicineReceivedDate.setText(medicineModel.getMedicineReceivedDate());
            edtMedicineExpDate.setText(medicineModel.getMedicineExpDate());
            edtMedicineNote.setText(medicineModel.getMedicineNote());
        }

        relativeSave.setOnClickListener(view -> {

             mName = edtMedicineName.getText().toString();
            String mCode = edtMedicineCode.getText().toString();
            String mCategory = edtMedicineCategory.getText().toString();
            String mCostPerPc = edtMedicinePcCostPrice.getText().toString();
            String mCostPerDz = edtMedicneDzCostPrice.getText().toString();
            String mQtyPerPc = edtMedicinePcQty.getText().toString();
            String mCompanyName = edtMedicineCompanyName.getText().toString();
            String address = edtAddress.getText().toString();
            String mSupplierName = edtSupplierName.getText().toString();
            String mContactPh1 = edtContactMedicinePh1.getText().toString();
            String mContactPh2 = edtContactMedicinePh2.getText().toString();
            String mContactPh3 = edtMedicineContactPh3.getText().toString();
            String mViberPh = edtMedicineViberPh.getText().toString();
            String mPayment = edtMedicinePayment.getText().toString();
            String mSalePricePc1 = edtSaleMedicinePcPrice1.getText().toString();
            String mSalePriceDz1 = edtSaleMedicineDzPrice1.getText().toString();
            String mSalePricePc2 = edtSaleMedicinePcPrice2.getText().toString();
            String mSalePriceDz2 = edtSaleMedicineDzPrice2.getText().toString();
            String mSalePricePc3 = edtSaleMedicinePcPrice3.getText().toString();
            String mSalePriceDz3 = edtSaleMedicineDzPrice3.getText().toString();
            String mSalePricePc4 = edtSaleMedicinePcPrice4.getText().toString();
            String mSalePriceDz4 = edtSaleMedicineDzCostPrice4.getText().toString();
            String mReceiveDate = edtMedicineReceivedDate.getText().toString();
            String mExpDate = edtMedicineExpDate.getText().toString();
            String mNote = edtMedicineNote.getText().toString();

            MedicineModel medicineModel = new MedicineModel();

            if (is_edit){
                medicineModel.setMedcineId(idEdit);
            }else {
                medicineModel.setMedcineId(UUID.randomUUID().toString());
            }


            medicineModel.setMedicineName(mName);
            medicineModel.setMedicineCode(mCode);
            medicineModel.setMedicineCategory(mCategory);
            medicineModel.setMedicineCostPerPc(mCostPerPc);
            medicineModel.setMedicineCostPerDz(mCostPerDz);
            medicineModel.setMedicineQtyPerPc(mQtyPerPc);

            medicineModel.setMedicineViberPh(mViberPh);
            medicineModel.setMedicinePayment(mPayment);
            medicineModel.setMedicineSalePcPerPrice1(mSalePricePc1);
            medicineModel.setMedicineSaleDzPerPrice1(mSalePriceDz1);
            medicineModel.setMedicineSalePcPerPrice2(mSalePricePc2);
            medicineModel.setMedicineSaleDzPerPrice2(mSalePriceDz2);
            medicineModel.setMedicineSalePcPerPrice3(mSalePricePc3);
            medicineModel.setMedicineSaleDzPerPrice3(mSalePriceDz3);
            medicineModel.setMedicineSalePcPerPrice4(mSalePricePc4);
            medicineModel.setMedicineSaleDzPerPrice4(mSalePriceDz4);
            medicineModel.setMedicineReceivedDate(mReceiveDate);
            medicineModel.setMedicineExpDate(mExpDate);
            medicineModel.setMedicineNote(mNote);

//            medicineModel.setSupplierModel(mCompanyName);
//            medicineModel.setMedicineSupplierName(mSupplierName);
//            medicineModel.setMedicineContactPh1(mContactPh1);
//            medicineModel.setMedicineContactPh2(mContactPh2);
//            medicineModel.setMedicineContactPh3(mContactPh3);

            SupplierModel supplierModel = new SupplierModel();
            supplierModel.setSuplierId(UUID.randomUUID().toString());
            supplierModel.setCompanyName(mCompanyName);
            supplierModel.setCompanyAddress(address);
            supplierModel.setSupplierName(mSupplierName);
            supplierModel.setSuplier_phno1(mContactPh1);
            supplierModel.setSupplier_phno2(mContactPh2);
            supplierModel.setSupplier_phno3(mContactPh3);

            medicineModel.setSupplierModel(supplierModel);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(supplierModel);
                realm.copyToRealmOrUpdate(medicineModel);
                Toast.makeText(MedicineAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });
           finish();
        });

        spinnerAdd.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Card");
        list.add("Dozens");
        list.add("Bottle");
        list.add("Capsule");
        list.add("Package");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdd.setAdapter(adapter);

    }

    private void setUpExpireDateTime() {
        // Registration Date
        edtMedicineExpDate.setInputType(InputType.TYPE_NULL);
        edtMedicineExpDate.setFocusableInTouchMode(false);
        edtMedicineExpDate.setOnClickListener(v -> {
            String localDate = edtMedicineExpDate.getText().toString();
            String enteredRegDate = DateTimeHelper.convertDateFormat(localDate,
                    DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT,
                    DateTimeHelper.LOCAL_DATE_FORMAT);
            Date initDate = new Date();
            if (!TextUtils.isEmpty(enteredRegDate)) {
                Date parsedDated = DateTimeHelper.getDateFromString(enteredRegDate, DateTimeHelper.LOCAL_DATE_FORMAT);
                if (parsedDated != null) {
                    initDate = parsedDated;
                }
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initDate);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT,
                    (view, year, monthOfYear, dayOfMonth) -> {

                        String strDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        edtMedicineExpDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    private void setUpReceivedDateTime() {
        // Registration Date
        edtMedicineReceivedDate.setInputType(InputType.TYPE_NULL);
        edtMedicineReceivedDate.setFocusableInTouchMode(false);
        edtMedicineReceivedDate.setOnClickListener(v -> {
            String localDate = edtMedicineReceivedDate.getText().toString();
            String enteredRegDate = DateTimeHelper.convertDateFormat(localDate,
                    DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT,
                    DateTimeHelper.LOCAL_DATE_FORMAT);
            Date initDate = new Date();
            if (!TextUtils.isEmpty(enteredRegDate)) {
                Date parsedDated = DateTimeHelper.getDateFromString(enteredRegDate, DateTimeHelper.LOCAL_DATE_FORMAT);
                if (parsedDated != null) {
                    initDate = parsedDated;
                }
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initDate);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT,
                    (view, year, monthOfYear, dayOfMonth) -> {

                        String strDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        edtMedicineReceivedDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (is_edit){
            medicineModel.setMedcineId(idEdit);
        }else {
            medicineModel.setMedcineId(UUID.randomUUID().toString());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
