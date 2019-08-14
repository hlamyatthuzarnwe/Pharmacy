package com.example.pharmacy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.helper.SupplierAddList;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SingleSupplierModel;
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

public class MedicineAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MedicineAddActivity";
    private static final char UNIX_SEPRATOR = '/';
    String mName;
    @BindView(R.id.tilMedicineName_medicineAdd)
    TextInputLayout tilMedicineName;
    @BindView(R.id.tilMedicineCost_medicineAdd)
    TextInputLayout tilMedicineCost;
    @BindView(R.id.tilMedicineQty_medicineAdd)
    TextInputLayout tilMedicineQty;
    @BindView(R.id.tilMedicineSalePrice_medicineAdd)
    TextInputLayout tilMedicineSalePrice;
    @BindView(R.id.tilCompanyName_medicineAdd)
    TextInputLayout tilCompanyName;
    @BindView(R.id.tilCompanyPh_medicineAdd)
    TextInputLayout tilCompanyPh;
    @BindView(R.id.linearExistingSupplier_medicineAdd)
    LinearLayout linearExistingSupplier;
    @BindView(R.id.edtMedicineName)
    EditText edtMedicineName;
    @BindView(R.id.edtMedicinePcCostPrice)
    EditText edtMedicinePcCostPrice;
    @BindView(R.id.edtMedicinePcQty)
    EditText edtMedicinePcQty;
    @BindView(R.id.edtMedicineCompanyName)
    EditText edtMedicineCompanyName;
    @BindView(R.id.edtSupplierName)
    EditText edtSupplierName;
    @BindView(R.id.edtContactMedicinePh1)
    EditText edtContactMedicinePh1;
    @BindView(R.id.edtSaleMedicinePcPrice1)
    EditText edtSaleMedicinePcPrice1;
    @BindView(R.id.edtMedicineReceivedDate)
    EditText edtMedicineReceivedDate;
    @BindView(R.id.edtMedicineExpDate)
    EditText edtMedicineExpDate;
    @BindView(R.id.edtAddress_medicineAdd)
    EditText edtAddress;
    @BindView(R.id.relativeSave_medicineAdd)
    RelativeLayout relativeSave;
    @BindView(R.id.spinnerAdd)
    Spinner spinnerAdd;
    private Realm realm;
    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;

    //private SupplierModel supplierModel;
    //private SupplierListModel supplierListModel;
    private SupplierModel singleSupplierModel;
    private MedicineModel singleMedicineModel;

    private String idEdit;
    private String idSingleMedicine;
    private boolean is_edit = false;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        setUpReceivedDateTime();
        setUpExpireDateTime();
        //Log.d(TAG, "onCreate: uuid : "+UUID.randomUUID());

        medicineModel = (MedicineModel) getIntent().getParcelableExtra("Medicine");
        singleSupplierModel = SingleSupplierModel.getSupplier();
        singleMedicineModel = SupplierAddList.getSaleItem();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medicine Data");
        setSupportActionBar(toolbar);

        if (medicineModel != null) {
            idEdit = medicineModel.getMedcineId();
            if (idEdit.isEmpty()) {
                is_edit = false;
            } else {
                is_edit = true;
            }
            edtMedicineName.setText(medicineModel.getMedicineName());
            edtMedicinePcCostPrice.setText(medicineModel.getMedicineCostPerPc());
            edtMedicinePcQty.setText(medicineModel.getMedicineQtyPerPc());
            if (medicineModel.getSupplierModel() != null) {
                edtMedicineCompanyName.setText(medicineModel.getSupplierModel().getCompanyName());
                edtAddress.setText(medicineModel.getSupplierModel().getCompanyAddress());
                edtSupplierName.setText(medicineModel.getSupplierModel().getSupplierName());
                edtContactMedicinePh1.setText(medicineModel.getSupplierModel().getSuplier_phno1());

            }

            edtSaleMedicinePcPrice1.setText(medicineModel.getMedicineSalePcPerPrice1());
            edtMedicineReceivedDate.setText(medicineModel.getMedicineReceivedDate());
            edtMedicineExpDate.setText(medicineModel.getMedicineExpDate());
        } else if (singleMedicineModel.getMedicineName() != null) {
            idSingleMedicine = singleMedicineModel.getMedcineId();
            edtMedicineName.setText(singleMedicineModel.getMedicineName());
            edtMedicinePcCostPrice.setText(singleMedicineModel.getMedicineCostPerPc());
            edtMedicinePcQty.setText(singleMedicineModel.getMedicineQtyPerPc());
            edtSaleMedicinePcPrice1.setText(singleMedicineModel.getMedicineSalePcPerPrice1());
            edtMedicineReceivedDate.setText(singleMedicineModel.getMedicineReceivedDate());
            edtMedicineExpDate.setText(singleMedicineModel.getMedicineExpDate());

            if (singleSupplierModel.getSupplierId() != null) {
                Log.d(TAG, "onCreate: getId : " + singleSupplierModel.getSupplierName());
                edtSupplierName.setText(singleSupplierModel.getSupplierName());
                edtMedicineCompanyName.setText(singleSupplierModel.getCompanyName());
                edtAddress.setText(singleSupplierModel.getCompanyName());
                edtContactMedicinePh1.setText(singleSupplierModel.getSuplier_phno1());
            }
        }


        linearExistingSupplier.setOnClickListener(view -> {
            insertSupplierItems();
            Intent intent = new Intent(MedicineAddActivity.this, SupplierSearchActivity.class);
            startActivity(intent);
        });


        relativeSave.setOnClickListener(view -> {


            if (validateField()) {
                SupplierModel supplierModel = new SupplierModel();
                MedicineModel mModel = new MedicineModel();

                mName = edtMedicineName.getText().toString();
                String mCostPerPc = edtMedicinePcCostPrice.getText().toString();
                String mQtyPerPc = edtMedicinePcQty.getText().toString();
                String mCompanyName = edtMedicineCompanyName.getText().toString();
                String address = edtAddress.getText().toString();
                String mSupplierName = edtSupplierName.getText().toString();
                String mContactPh1 = edtContactMedicinePh1.getText().toString();
                String mSalePricePc1 = edtSaleMedicinePcPrice1.getText().toString();
                String mReceiveDate = edtMedicineReceivedDate.getText().toString();
                String mExpDate = edtMedicineExpDate.getText().toString();


                if (!TextUtils.isEmpty(idEdit)){
                    mModel.setMedcineId(idEdit);
                }else if (!TextUtils.isEmpty(idSingleMedicine)){
                    mModel.setMedcineId(idSingleMedicine);
                }else {
                    mModel.setMedcineId(UUID.randomUUID().toString());
                }

                mModel.setMedicineName(mName);
                mModel.setMedicineCostPerPc(mCostPerPc);
                mModel.setMedicineTotalQty(mQtyPerPc);
                mModel.setMedicineSalePcPerPrice1(mSalePricePc1);
                mModel.setMedicineReceivedDate(mReceiveDate);
                mModel.setMedicineExpDate(mExpDate);

                if (singleSupplierModel.getSupplierId() != null) {
                    Log.d(TAG, "onCreate: Single Model Condition : " + singleSupplierModel.getSupplierName());
                    mModel.setSupplierModel(singleSupplierModel);
                } else {
                    Log.d(TAG, "onCreate: Normal Model Condition");
                    supplierModel.setSupplierId(UUID.randomUUID().toString());
                    supplierModel.setCompanyName(mCompanyName);
                    supplierModel.setCompanyAddress(address);
                    supplierModel.setSupplierName(mSupplierName);
                    supplierModel.setSuplier_phno1(mContactPh1);

                    mModel.setSupplierModel(supplierModel);
                }


                realm.executeTransactionAsync(realm -> {
                    if (singleSupplierModel.getSupplierId() != null) {
                        Log.d(TAG, "onCreate: single Model : " + singleSupplierModel.getSupplierName());
                        realm.copyToRealmOrUpdate(singleSupplierModel);
                    } else {
                        Log.d(TAG, "onCreate: Normal Model");
                        Log.d(TAG, "onCreate: ");
                        realm.copyToRealmOrUpdate(supplierModel);
                    }

                    realm.copyToRealmOrUpdate(mModel);
                });
                SingleSupplierModel.clear();
                SupplierAddList.clear();
                Toast.makeText(MedicineAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(MedicineAddActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
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

    private void insertSupplierItems() {
        if (is_edit) {
            singleMedicineModel.setMedcineId(medicineModel.getMedcineId());
        }
        singleMedicineModel.setMedicineCostPerPc(edtMedicinePcCostPrice.getText().toString());
        singleMedicineModel.setMedicineName(edtMedicineName.getText().toString());
        singleMedicineModel.setMedicineQtyPerPc(edtMedicinePcQty.getText().toString());
        singleMedicineModel.setMedicineSalePcPerPrice1(edtSaleMedicinePcPrice1.getText().toString());
        singleMedicineModel.setMedicineReceivedDate(edtMedicineReceivedDate.getText().toString());
        singleMedicineModel.setMedicineExpDate(edtMedicineExpDate.getText().toString());
    }

    /* private void insertSupplierItems() {
         String supplierName = edtSupplierName.getText().toString();
         String companyName = edtMedicineCompanyName.getText().toString();
         String companyAddress = edtAddress.getText().toString();
         String supplierPh = edtContactMedicinePh1.getText().toString();

         supplierListModel.setSupName(supplierName);
         Log.d(TAG, "insertSupplierItems: supplier name " + supplierName);
         supplierListModel.setCompName(companyName);
         supplierListModel.setCompAddress(companyAddress);
         supplierListModel.setSupPh(supplierPh);

         //  Log.d(TAG, "insertSupplierItems: invoice : "+saleInvoiceDate);
        *//*
        singleSupplier.setSupplierName(supplierName);
        singleSupplier.setCompanyName(companyName);
        singleSupplier.setCompanyAddress(companyAddress);
        singleSupplier.setSuplier_phno1(supplierPh);
        *//*

    }
*/
    private boolean validateField() {
        if (TextUtils.isEmpty(edtMedicineName.getText().toString().trim())) {
            tilMedicineName.setErrorEnabled(true);
            tilMedicineName.setError("Medicine Name required");
            return false;
        } else if (TextUtils.isEmpty(edtMedicinePcCostPrice.getText().toString().trim())) {
            tilMedicineCost.setErrorEnabled(true);
            tilMedicineCost.setError("Medicine Cost required");
            return false;
        } else if (TextUtils.isEmpty(edtMedicinePcQty.getText().toString().trim())) {
            tilMedicineQty.setErrorEnabled(true);
            tilMedicineQty.setError("Medicine Quantity required");
            return false;
        } else if (TextUtils.isEmpty(edtSaleMedicinePcPrice1.getText().toString().trim())) {
            tilMedicineSalePrice.setErrorEnabled(true);
            tilMedicineSalePrice.setError("Medicine Sale Price required");
            return false;
        } else if (TextUtils.isEmpty(edtMedicineCompanyName.getText().toString().trim())) {
            tilCompanyName.setErrorEnabled(true);
            tilCompanyName.setError("Medicine Company Name required");
            return false;
        } else if (TextUtils.isEmpty(edtContactMedicinePh1.getText().toString().trim())) {
            tilCompanyPh.setErrorEnabled(true);
            tilCompanyPh.setError("Medicine Compan Phone Number required");
            return false;
        }
        return true;
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
        if (is_edit) {
            medicineModel.setMedcineId(idEdit);
        } else {
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
