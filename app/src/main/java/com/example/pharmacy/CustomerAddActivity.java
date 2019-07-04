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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.model.CustomerModel;
import com.example.yy.R;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CustomerAddActivity extends AppCompatActivity {

    private static final String TAG = "CustomerAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private CustomerModel customerModel;
    private Toolbar toolbar;
    private Context context;
    int cId;

    @BindView(R.id.edtSaleInvoiceDate)
    EditText edtSaleInvoiceDate;

    @BindView(R.id.edtCustomerName_add)
    EditText edtCustomerName_add;

    @BindView(R.id.edtCustomerAddress_add)
    EditText edtCustomerAddress_add;

//    @BindView(R.id.edtCustomerLevel_add)
//    EditText edtCustomerLevel_add;

    @BindView(R.id.edtCustomerPhNo1)
    EditText edtCustomerPhNo1;

    @BindView(R.id.edtCustomerPhNo2)
    EditText edtCustomerPhNo2;

    @BindView(R.id.edtCustomerPhNo3)
    EditText edtCustomerPhNo3;

    @BindView(R.id.edtCustomerPhNo4)
    EditText edtCustomerPhNo4;

    @BindView(R.id.edtCustomerPhNo5)
    EditText edtCustomerPhNo5;

    @BindView(R.id.edtSaleTotalAmt_add)
    EditText edtSaleTotalAmt_add;

    @BindView(R.id.edtSaleUpFront)
    EditText edtSaleUpFront;

    @BindView(R.id.edtSaleBalance)
    EditText edtSaleBalance;

    @BindView(R.id.edtSaleDueDate)
    EditText edtSaleDueDate;

    @BindView(R.id.edtCustomerNote)
    EditText edtCustomerNote;

    @BindView(R.id.relativeSave_customerAdd)
    RelativeLayout relativeSave;

    private String idEdit;
    private boolean is_edit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setUpInvoiceDate();
        setUpDueDate();

        customerModel = (CustomerModel) getIntent().getParcelableExtra("Customer");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Data");
        setSupportActionBar(toolbar);

        if(customerModel != null){
            idEdit = customerModel.getCustomerId();
            if (idEdit.isEmpty()){
                is_edit = false;
            }else {
                is_edit = true;
            }
         //   idEdit = customerModel.getCustomerId();
            edtCustomerName_add.setText(customerModel.getCustomerName());
            edtCustomerAddress_add.setText(customerModel.getCustomerAddress());
//            edtCustomerLevel_add.setText(customerModel.getCustomerLevel());
            edtCustomerPhNo1.setText(customerModel.getCustomerPhNo1());
            edtCustomerPhNo2.setText(customerModel.getCustomerPhNo2());
            edtCustomerPhNo3.setText(customerModel.getCustomerPhNo3());
            edtCustomerPhNo4.setText(customerModel.getCustomerPhNo4());
            edtCustomerPhNo5.setText(customerModel.getCustomerPhNo5());
            edtSaleTotalAmt_add.setText(customerModel.getCustomerTotalAmt());
            edtSaleUpFront.setText(customerModel.getCustomerUpFront());
            edtSaleBalance.setText(customerModel.getCustomerBalance());
            edtSaleDueDate.setText(customerModel.getCustomerDueDate());
            edtCustomerNote.setText(customerModel.getCustomerNote());
        }
        relativeSave.setOnClickListener(v -> {{
            String cName = edtCustomerName_add.getText().toString();
            String cAddress = edtCustomerAddress_add.getText().toString();
            // String clevel = edtCustomerLevel_add.getText().toString();
            String cPhNo1 = edtCustomerPhNo1.getText().toString();
            String cPhNo2 = edtCustomerPhNo2.getText().toString();
            String cPhNo3 = edtCustomerPhNo3.getText().toString();
            String cPhNo4 = edtCustomerPhNo4.getText().toString();
            String cPhNo5 = edtCustomerPhNo5.getText().toString();
            String cTotalAmt = edtSaleTotalAmt_add.getText().toString();
            String cUpFront = edtSaleUpFront.getText().toString();
            String cBalance = edtSaleBalance.getText().toString();
            String cDueDate = edtSaleDueDate.getText().toString();
            String cNote = edtCustomerNote.getText().toString();

            CustomerModel customerModel = new CustomerModel();

            if (is_edit){
                customerModel.setCustomerId(idEdit);
            }else {
                customerModel.setCustomerId(UUID.randomUUID().toString());
            }

            customerModel.setCustomerId(UUID.randomUUID().toString());
            customerModel.setCustomerName(cName);
            customerModel.setCustomerAddress(cAddress);
            //customerModel.setCustomerLevel(clevel);
            customerModel.setCustomerPhNo1(cPhNo1);
            customerModel.setCustomerPhNo2(cPhNo2);
            customerModel.setCustomerPhNo3(cPhNo3);
            customerModel.setCustomerPhNo4(cPhNo4);
            customerModel.setCustomerPhNo5(cPhNo5);
            customerModel.setCustomerTotalAmt(cTotalAmt);
            customerModel.setCustomerUpFront(cUpFront);
            customerModel.setCustomerBalance(cBalance);
            customerModel.setCustomerDueDate(cDueDate);
            customerModel.setCustomerNote(cNote);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(customerModel);
                Toast.makeText(CustomerAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });
        }

        });

    }

    private void setUpDueDate() {
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
            // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    private void setUpInvoiceDate() {
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
        if (is_edit){
            customerModel.setCustomerId(idEdit);
        }else {
            customerModel.setCustomerId(UUID.randomUUID().toString());
        }

        return super.onOptionsItemSelected(item);
    }
}
