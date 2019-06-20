package com.example.yy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yy.helper.DateTimeHelper;
import com.example.yy.model.MedicineModel;
import com.example.yy.model.SaleModel;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleAddActivity extends AppCompatActivity {

    private static final String TAG = "SaleAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private SaleModel saleModel;
    private Toolbar toolbar;
    private Context context;
    int saleId;

    @BindView(R.id.edtSaleInvoiceId_add)
    TextView edtSaleInvoiceId_add;

    @BindView(R.id.edtSaleInvoiceDate)
    TextView edtSaleInvoiceDate;

    @BindView(R.id.edtSaleCustomerName_add)
    TextView edtSaleCustomerName_add;

    @BindView(R.id.edtSaleCustomerAddress_add)
    TextView edtSaleCustomerAddress_add;

    @BindView(R.id.edtSaleCustomerPhNo_add)
    TextView edtSaleCustomerPhNo_add;

    @BindView(R.id.edtSaleMedicineName_add)
    TextView edtSaleMedicineName_add;

    @BindView(R.id.edtSaleMedicineCode_add)
    TextView edtSaleMedicineCode_add;

    @BindView(R.id.edtSaleMedicineCategory_add)
    TextView edtSaleMedicineCategory_add;

    @BindView(R.id.edtMedicineCostPrice)
    TextView edtMedicineCostPrice;

    @BindView(R.id.edtSaleDiscount_add)
    TextView edtSaleDiscount_add;

    @BindView(R.id.edtSaleQty_add)
    TextView edtSaleQty_add;

    @BindView(R.id.edtSaleSubTotalAmt_add)
    TextView edtSaleSubTotalAmt_add;

    @BindView(R.id.edtSaleTotalAmt_add)
    TextView edtSaleTotalAmt_add;

    @BindView(R.id.edtSaleDueDate)
    EditText edtSaleDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setUpSaleInvoiceDate();
        setUpDuteDate();

        saleModel = (SaleModel)getIntent().getParcelableExtra("Sale");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Data");
        setSupportActionBar(toolbar);


        if(saleModel != null){
            edtSaleInvoiceId_add.setText(String.valueOf(saleModel.getSaleInvoiceNo()));
            edtSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            edtSaleCustomerName_add.setText(saleModel.getSaleCustomerName());
            edtSaleCustomerAddress_add.setText(saleModel.getSaleCustomerAddress());
            edtSaleCustomerPhNo_add.setText(saleModel.getSaleCustomerPhNo());
            edtSaleMedicineName_add.setText(saleModel.getSaleMedicineName());
            edtSaleMedicineCode_add.setText(saleModel.getSaleMedicineCode());
            edtSaleMedicineCategory_add.setText(saleModel.getSaleCategory());
            edtMedicineCostPrice.setText(saleModel.getSalePrice());
            edtSaleDiscount_add.setText(saleModel.getSaleDiscount());
            edtSaleQty_add.setText(saleModel.getSaleQty());
            edtSaleSubTotalAmt_add.setText(saleModel.getSaleSubTotalAmt());
            edtSaleTotalAmt_add.setText(saleModel.getSaleTotalAmt());
            edtSaleDueDate.setText(saleModel.getSaleDuedate());
        }
    }

    private void setUpSaleInvoiceDate() {
        // Registration Date
        edtSaleInvoiceDate.setInputType(InputType.TYPE_NULL);
        edtSaleInvoiceDate.setFocusableInTouchMode(false);
        edtSaleInvoiceDate.setOnClickListener(v -> {
            String localDate = edtSaleInvoiceDate.getText().toString();
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

                        edtSaleInvoiceDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    private void setUpDuteDate() {
        // Registration Date
        edtSaleDueDate.setInputType(InputType.TYPE_NULL);
        edtSaleDueDate.setFocusableInTouchMode(false);
        edtSaleDueDate.setOnClickListener(v -> {
            String localDate = edtSaleDueDate.getText().toString();
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

                        edtSaleDueDate.setText(DateTimeHelper.convertDateFormat(strDate,
                                DateTimeHelper.LOCAL_DATE_FORMAT,
                                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT));


                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
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

        if (TextUtils.isEmpty(edtSaleInvoiceId_add.getText())) {
            saleId = Integer.parseInt(edtSaleInvoiceId_add.getText().toString());
        }
        if(item.getItemId() == R.id.menu_add){
            String saleInvoiceDate =  edtSaleInvoiceDate.getText().toString();
            String saleCustomerName = edtSaleCustomerName_add.getText().toString();
            String saleCustomerAddress = edtSaleCustomerAddress_add.getText().toString();
            String saleCustomerPhNo = edtSaleCustomerPhNo_add.getText().toString();
            String saleMedicineName = edtSaleMedicineName_add.getText().toString();
            String saleMedicineCode = edtSaleMedicineCode_add.getText().toString();
            String saleMedicineCategory = edtSaleMedicineCategory_add.getText().toString();
            String salePrice = edtMedicineCostPrice.getText().toString();
            String saleDiscount = edtSaleDiscount_add.getText().toString();
            String saleQty = edtSaleQty_add.getText().toString();
            String saleSubTotalAmt = edtSaleSubTotalAmt_add.getText().toString();
            String saleTotalAmt = edtSaleTotalAmt_add.getText().toString();
            String saleDueDate = edtSaleDueDate.getText().toString();

            SaleModel saleModel = new SaleModel();

            saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            saleModel.setSaleCustomerName(saleCustomerName);
            saleModel.setSaleCustomerAddress(saleCustomerAddress);
            saleModel.setSaleCustomerPhNo(saleCustomerPhNo);
            saleModel.setSaleMedicineName(saleMedicineName);
            saleModel.setSaleMedicineCode(saleMedicineCode);
            saleModel.setSaleCategory(saleMedicineCategory);
            saleModel.setSalePrice(salePrice);
            saleModel.setSaleDiscount(saleDiscount);
            saleModel.setSaleQty(saleQty);
            saleModel.setSaleSubTotalAmt(saleSubTotalAmt);
            saleModel.setSaleTotalAmt(saleTotalAmt);
            saleModel.setSaleDuedate(saleDueDate);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel);
                Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_update){
            String saleInvoiceDate =  edtSaleInvoiceDate.getText().toString();
            String saleCustomerName = edtSaleCustomerName_add.getText().toString();
            String saleCustomerAddress = edtSaleCustomerAddress_add.getText().toString();
            String saleCustomerPhNo = edtSaleCustomerPhNo_add.getText().toString();
            String saleMedicineName = edtSaleMedicineName_add.getText().toString();
            String saleMedicineCode = edtSaleMedicineCode_add.getText().toString();
            String saleMedicineCategory = edtSaleMedicineCategory_add.getText().toString();
            String salePrice = edtMedicineCostPrice.getText().toString();
            String saleDiscount = edtSaleDiscount_add.getText().toString();
            String saleQty = edtSaleQty_add.getText().toString();
            String saleSubTotalAmt = edtSaleSubTotalAmt_add.getText().toString();
            String saleTotalAmt = edtSaleTotalAmt_add.getText().toString();
            String saleDueDate = edtSaleDueDate.getText().toString();

            SaleModel saleModel = new SaleModel();

            saleModel.setSaleInvoiceNo(saleModel.getSaleInvoiceNo());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            saleModel.setSaleCustomerName(saleCustomerName);
            saleModel.setSaleCustomerAddress(saleCustomerAddress);
            saleModel.setSaleCustomerPhNo(saleCustomerPhNo);
            saleModel.setSaleMedicineName(saleMedicineName);
            saleModel.setSaleMedicineCode(saleMedicineCode);
            saleModel.setSaleCategory(saleMedicineCategory);
            saleModel.setSalePrice(salePrice);
            saleModel.setSaleDiscount(saleDiscount);
            saleModel.setSaleQty(saleQty);
            saleModel.setSaleSubTotalAmt(saleSubTotalAmt);
            saleModel.setSaleTotalAmt(saleTotalAmt);
            saleModel.setSaleDuedate(saleDueDate);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel);
                Toast.makeText(SaleAddActivity.this, "Successfully Update Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_delete){
            String saleId = saleModel.getSaleInvoiceNo();

            final MedicineModel deleteResults = realm.where(MedicineModel.class).equalTo("saleInvoiceNo", saleId).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (deleteResults != null) {
                        deleteResults.deleteFromRealm();
                    }
                    Toast.makeText(SaleAddActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        if(item.getItemId() == R.id.menu_cancel){
            edtSaleInvoiceId_add.setText("");
            edtSaleInvoiceDate.setText("");
            edtSaleCustomerName_add.setText("");
            edtSaleCustomerAddress_add.setText("");
            edtSaleCustomerPhNo_add.setText("");
            edtSaleMedicineName_add.setText("");
            edtSaleMedicineCode_add.setText("");
            edtSaleMedicineCategory_add.setText("");
            edtMedicineCostPrice.setText("");
            edtSaleDiscount_add.setText("");
            edtSaleQty_add.setText("");
            edtSaleSubTotalAmt_add.setText("");
            edtSaleTotalAmt_add.setText("");
            edtSaleDueDate.setText("");
        }


        return super.onOptionsItemSelected(item);
    }
}
