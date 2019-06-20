package com.example.yy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.yy.helper.DateTimeHelper;
import com.example.yy.model.MedicineModel;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.realm.Realm;

public class MedicineAddActivity extends AppCompatActivity {

    private static final String TAG = "MedicineAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;
    int mId;

    @BindView(R.id.edtMedicineId)
    EditText edtMedicineId;

    @BindView(R.id.edtMedicineName)
    EditText edtMedicineName;

    @BindView(R.id.edtMedicineCode)
    EditText edtMedicineCode;

    @BindView(R.id.edtMedicineCategory)
    EditText edtMedicineCategory;

    @BindView(R.id.edtMedicineDescription)
    EditText edtMedicineDescription;

    @BindView(R.id.edtMedicinePrice1)
    EditText edtMedicinePrice1;

    @BindView(R.id.edtMedicinePrice2)
    EditText edtMedicinePrice2;

    @BindView(R.id.edtMedicinePrice3)
    EditText edtMedicinePrice3;

    @BindView(R.id.edtMedicinePrice4)
    EditText edtMedicinePrice4;

    @BindView(R.id.edtMedicinePrice5)
    EditText edtMedicinePrice5;

    @BindView(R.id.edtSupplierName)
    EditText edtSupplierName;

    @BindView(R.id.edtReceivedDate)
    EditText edtReceivedDate;

    @BindView(R.id.edtExpDate)
    EditText edtExpDate;

    @BindView(R.id.edtMedicineCostPrice)
    EditText edtMedicineCostPrice;

    @BindView(R.id.edtMedicineQty)
    EditText edtMedicineQty;

    @BindView(R.id.edtMedicineQtyLeft)
    EditText edtMedicineQtyLeft;

    @BindView(R.id.edtMedicineTotalAmt)
    EditText edtMedicineTotalAmt;

   // private String selectedPrice;

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
            edtMedicineId.setText(String.valueOf(medicineModel.getMedcineId()));
            edtMedicineName.setText(medicineModel.getMedicineName());
            edtMedicineCode.setText(medicineModel.getMedicineCode());
            edtMedicineCategory.setText(medicineModel.getMedicineCategory());
            edtMedicineDescription.setText(medicineModel.getMedicineDescription());
            edtMedicinePrice1.setText(medicineModel.getMedicinePrice1());
            edtMedicinePrice2.setText(medicineModel.getMedicinePrice2());
            edtMedicinePrice3.setText(medicineModel.getMedicinePrice3());
            edtMedicinePrice4.setText(medicineModel.getMedicinePrice4());
            edtMedicinePrice5.setText(medicineModel.getMedicinePrice5());
            edtSupplierName.setText(medicineModel.getSupplierName());
            edtReceivedDate.setText(medicineModel.getReceivedDate());
            edtExpDate.setText(medicineModel.getExpDate());
            edtMedicineCostPrice.setText(medicineModel.getMedicineCost());
            edtMedicineQty.setText(medicineModel.getMedicineQty());
            edtMedicineQtyLeft.setText(medicineModel.getMedicineQtyLeft());
            edtMedicineTotalAmt.setText(medicineModel.getMedicineTotalAmt());
        }
    }

    private void setUpExpireDateTime() {
        // Registration Date
        edtExpDate.setInputType(InputType.TYPE_NULL);
        edtExpDate.setFocusableInTouchMode(false);
        edtExpDate.setOnClickListener(v -> {
            String localDate = edtExpDate.getText().toString();
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

                        edtExpDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    private void setUpReceivedDateTime() {
        // Registration Date
        edtReceivedDate.setInputType(InputType.TYPE_NULL);
        edtReceivedDate.setFocusableInTouchMode(false);
        edtReceivedDate.setOnClickListener(v -> {
            String localDate = edtReceivedDate.getText().toString();
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

                        edtReceivedDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
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
        if (TextUtils.isEmpty(edtMedicineId.getText())) {
            mId = Integer.parseInt(edtMedicineId.getText().toString());
        }
        if(item.getItemId() == R.id.menu_add){
            String mName = edtMedicineName.getText().toString();
            String mCode = edtMedicineCode.getText().toString();
            String mCategory = edtMedicineCategory.getText().toString();
            String mDescription = edtMedicineDescription.getText().toString();
            String mPrice1 = edtMedicinePrice1.getText().toString();
            String mPrice2 = edtMedicinePrice2.getText().toString();
            String mPrice3 = edtMedicinePrice3.getText().toString();;
            String mPrice4 = edtMedicinePrice4.getText().toString();
            String mPrice5 = edtMedicinePrice5.getText().toString();
            String supplierName = edtSupplierName.getText().toString();
            String receivedDate = edtReceivedDate.getText().toString();
            String ExpDate = edtExpDate.getText().toString();
            String mCost = edtMedicineCostPrice.getText().toString();
            String mQty = edtMedicineQty.getText().toString();
            String mQtyLeft = edtMedicineQtyLeft.getText().toString();
            String mTotalAmt = edtMedicineTotalAmt.getText().toString();

            MedicineModel medicineModel = new MedicineModel();

            medicineModel.setMedcineId(Integer.parseInt(edtMedicineId.getText().toString()));
            medicineModel.setMedicineName(mName);
            medicineModel.setMedicineCode(mCode);
            medicineModel.setMedicineCategory(mCategory);
            medicineModel.setMedicineDescription(mDescription);
            medicineModel.setMedicinePrice1(mPrice1);
            medicineModel.setMedicinePrice2(mPrice2);
            medicineModel.setMedicinePrice3(mPrice3);
            medicineModel.setMedicinePrice4(mPrice4);
            medicineModel.setMedicinePrice5(mPrice5);
            medicineModel.setSupplierName(supplierName);
            medicineModel.setReceivedDate(receivedDate);
            medicineModel.setExpDate(ExpDate);
            medicineModel.setMedicineCost(mCost);
            medicineModel.setMedicineQty(mQty);
            medicineModel.setMedicineQtyLeft(mQtyLeft);
            medicineModel.setMedicineTotalAmt(mTotalAmt);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(medicineModel);
                Toast.makeText(MedicineAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }
        if (item.getItemId() == R.id.menu_update){
            String mName = edtMedicineName.getText().toString();
            String mCode = edtMedicineCode.getText().toString();
            String mCategory = edtMedicineCategory.getText().toString();
            String mDescription = edtMedicineDescription.getText().toString();
            String mPrice1 = edtMedicinePrice1.getText().toString();
            String mPrice2 = edtMedicinePrice2.getText().toString();
            String mPrice3 = edtMedicinePrice3.getText().toString();;
            String mPrice4 = edtMedicinePrice4.getText().toString();
            String mPrice5 = edtMedicinePrice5.getText().toString();
            String supplierName = edtSupplierName.getText().toString();
            String receivedDate = edtReceivedDate.getText().toString();
            String ExpDate = edtExpDate.getText().toString();
            String mCost = edtMedicineCostPrice.getText().toString();
            String mQty = edtMedicineQty.getText().toString();
            String mQtyLeft = edtMedicineQtyLeft.getText().toString();
            String mTotalAmt = edtMedicineTotalAmt.getText().toString();

            MedicineModel medicineModel = new MedicineModel();

            medicineModel.setMedcineId(Integer.parseInt(edtMedicineId.getText().toString()));
            medicineModel.setMedicineName(mName);
            medicineModel.setMedicineCode(mCode);
            medicineModel.setMedicineCategory(mCategory);
            medicineModel.setMedicineDescription(mDescription);
            medicineModel.setMedicinePrice1(mPrice1);
            medicineModel.setMedicinePrice2(mPrice2);
            medicineModel.setMedicinePrice3(mPrice3);
            medicineModel.setMedicinePrice4(mPrice4);
            medicineModel.setMedicinePrice5(mPrice5);
            medicineModel.setSupplierName(supplierName);
            medicineModel.setReceivedDate(receivedDate);
            medicineModel.setExpDate(ExpDate);
            medicineModel.setMedicineCost(mCost);
            medicineModel.setMedicineQty(mQty);
            medicineModel.setMedicineQtyLeft(mQtyLeft);
            medicineModel.setMedicineTotalAmt(mTotalAmt);


            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(medicineModel);
                Toast.makeText(MedicineAddActivity.this, "Successfully Update Data", Toast.LENGTH_SHORT).show();
            });
        }

        if(item.getItemId() == R.id.menu_delete){
            mId = medicineModel.getMedcineId();

            final MedicineModel deleteResults = realm.where(MedicineModel.class).equalTo("medicineId", mId).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (deleteResults != null) {
                        deleteResults.deleteFromRealm();
                    }
                    Toast.makeText(MedicineAddActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        if(item.getItemId() == R.id.menu_cancel){
           edtMedicineId.setText("");
           edtMedicineName.setText("");
           edtMedicineCode.setText("");
           edtMedicineCategory.setText("");
           edtMedicineDescription.setText("");
           edtMedicinePrice1.setText("");
           edtMedicinePrice2.setText("");
           edtMedicinePrice3.setText("");
           edtMedicinePrice4.setText("");
           edtMedicinePrice5.setText("");
           edtSupplierName.setText("");
           edtReceivedDate.setText("");
           edtExpDate.setText("");
           edtMedicineCostPrice.setText("");
           edtMedicineQty.setText("");
           edtMedicineQtyLeft.setText("");
           edtMedicineTotalAmt.setText("");

        }

        return super.onOptionsItemSelected(item);
    }
}
