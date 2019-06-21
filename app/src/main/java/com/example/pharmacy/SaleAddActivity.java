package com.example.pharmacy;

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
import android.widget.Toast;

import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

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
    private CustomerModel customerModel;
    private MedicineModel medicineModel;
    private Toolbar toolbar;
    private Context context;
    int saleId;

    @BindView(R.id.edtSaleInvoiceId_add)
    EditText edtSaleInvoiceId_add;

    @BindView(R.id.edtSaleInvoiceDate)
    EditText edtSaleInvoiceDate;

    @BindView(R.id.edtCustomerName_add)
    EditText edtCustomerName_add;

    @BindView(R.id.edtCustomerLevel_add)
    EditText edtCustomerLevel_add;

    @BindView(R.id.edtCustomerAddress_add)
    EditText edtCustomerAddress_add;

    @BindView(R.id.edtCustomerPhNo1)
    EditText edtCustomerPhNo1;

    @BindView(R.id.edtMedicineName)
    EditText edtMedicineName;

    @BindView(R.id.edtMedicineCode)
    EditText edtMedicineCode;

    @BindView(R.id.edtMedicineCategory)
    EditText edtMedicineCategory;

    @BindView(R.id.edtMedicineCostPrice)
    EditText edtMedicineCostPrice;

    @BindView(R.id.edtSaleDiscount_add)
    EditText edtSaleDiscount_add;

    @BindView(R.id.edtSaleQty_add)
    EditText edtSaleQty_add;

    @BindView(R.id.edtSaleSubTotalAmt_add)
    EditText edtSaleSubTotalAmt_add;

    @BindView(R.id.edtSaleTotalAmt_add)
    EditText edtSaleTotalAmt_add;

    @BindView(R.id.edtSaleDueDate)
    EditText edtSaleDueDate;
    private String medcineId;
    private String customerId;

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
            edtCustomerName_add.setText(customerModel.getCustomerName());
            edtCustomerLevel_add.setText(customerModel.getCustomerLevel());
            edtCustomerAddress_add.setText(customerModel.getCustomerAddress());
            edtCustomerPhNo1.setText(customerModel.getCustomerPhNo1());
            edtMedicineName.setText(medicineModel.getMedicineName());
            edtMedicineCode.setText(medicineModel.getMedicineCode());
            edtMedicineCategory.setText(medicineModel.getMedicineCategory());
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
            String saleCustomerName = edtCustomerName_add.getText().toString();
            String saleCustomerlevel = edtCustomerLevel_add.getText().toString();
            String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
            String saleCustomerPhNo = edtCustomerPhNo1.getText().toString();
            String saleMedicineName = edtMedicineName.getText().toString();
            String saleMedicineCode = edtMedicineCode.getText().toString();
            String saleMedicineCategory = edtMedicineCategory.getText().toString();
            String salePrice = edtMedicineCostPrice.getText().toString();
            String saleDiscount = edtSaleDiscount_add.getText().toString();
            String saleQty = edtSaleQty_add.getText().toString();
            String saleSubTotalAmt = edtSaleSubTotalAmt_add.getText().toString();
            String saleTotalAmt = edtSaleTotalAmt_add.getText().toString();
            String saleDueDate = edtSaleDueDate.getText().toString();

            SaleModel saleModel = new SaleModel();
            MedicineModel medicineModel = new MedicineModel();
            CustomerModel customerModel = new CustomerModel();

            saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            customerModel.setCustomerName(saleCustomerName);
            customerModel.setCustomerLevel(saleCustomerlevel);
            customerModel.setCustomerAddress(saleCustomerAddress);
            customerModel.setCustomerPhNo1(saleCustomerPhNo);
            medicineModel.setMedicineName(saleMedicineName);
            medicineModel.setMedicineCode(saleMedicineCode);
            medicineModel.setMedicineCategory(saleMedicineCategory);
            medicineModel.setMedicineSellingPrice(salePrice);
            saleModel.setSaleDiscount(saleDiscount);
            saleModel.setSaleQty(saleQty);
            saleModel.setSaleSubTotalAmt(saleSubTotalAmt);
            saleModel.setSaleTotalAmt(saleTotalAmt);
            saleModel.setSaleDuedate(saleDueDate);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel);
                realm.copyToRealmOrUpdate(medicineModel);
                realm.copyToRealmOrUpdate(customerModel);
                Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_update){
            String saleInvoiceDate =  edtSaleInvoiceDate.getText().toString();
            String saleCustomerName = edtCustomerName_add.getText().toString();
            String saleCustomerlevel = edtCustomerLevel_add.getText().toString();
            String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
            String saleCustomerPhNo = edtCustomerPhNo1.getText().toString();
            String saleMedicineName = edtMedicineName.getText().toString();
            String saleMedicineCode = edtMedicineCode.getText().toString();
            String saleMedicineCategory = edtMedicineCategory.getText().toString();
            String salePrice = edtMedicineCostPrice.getText().toString();
            String saleDiscount = edtSaleDiscount_add.getText().toString();
            String saleQty = edtSaleQty_add.getText().toString();
            String saleSubTotalAmt = edtSaleSubTotalAmt_add.getText().toString();
            String saleTotalAmt = edtSaleTotalAmt_add.getText().toString();
            String saleDueDate = edtSaleDueDate.getText().toString();

            SaleModel saleModel = new SaleModel();
            MedicineModel medicineModel = new MedicineModel();
            CustomerModel customerModel = new CustomerModel();

            saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            customerModel.setCustomerName(saleCustomerName);
            customerModel.setCustomerLevel(saleCustomerlevel);
            customerModel.setCustomerAddress(saleCustomerAddress);
            customerModel.setCustomerPhNo1(saleCustomerPhNo);
            medicineModel.setMedicineName(saleMedicineName);
            medicineModel.setMedicineCode(saleMedicineCode);
            medicineModel.setMedicineCategory(saleMedicineCategory);
            medicineModel.setMedicineSellingPrice(salePrice);
            saleModel.setSaleDiscount(saleDiscount);
            saleModel.setSaleQty(saleQty);
            saleModel.setSaleSubTotalAmt(saleSubTotalAmt);
            saleModel.setSaleTotalAmt(saleTotalAmt);
            saleModel.setSaleDuedate(saleDueDate);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel);
                realm.copyToRealmOrUpdate(medicineModel);
                realm.copyToRealmOrUpdate(customerModel);
                Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }

        if(item.getItemId() == R.id.menu_delete){
            String saleId = saleModel.getSaleInvoiceNo();

            final MedicineModel deleteResults1 = realm.where(MedicineModel.class).equalTo("medicineName", medcineId).findFirst();
            final SaleModel deleteResults2 = realm.where(SaleModel.class).equalTo("saleInvoiceNo", saleId).findFirst();
            final CustomerModel deleteResults3 = realm.where(CustomerModel.class).equalTo("customerName", customerId).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (deleteResults1 != null) {
                        deleteResults1.deleteFromRealm();
                    }
                    if (deleteResults2 != null) {
                        deleteResults2.deleteFromRealm();
                    }
                    if (deleteResults3 != null) {
                        deleteResults3.deleteFromRealm();
                    }
                    Toast.makeText(SaleAddActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        if(item.getItemId() == R.id.menu_cancel){
            edtSaleInvoiceId_add.setText("");
            edtSaleInvoiceDate.setText("");
            edtCustomerName_add.setText("");
            edtCustomerLevel_add.setText("");
            edtCustomerAddress_add.setText("");
            edtCustomerPhNo1.setText("");
            edtMedicineName.setText("");
            edtMedicineCode.setText("");
            edtMedicineCategory.setText("");
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
