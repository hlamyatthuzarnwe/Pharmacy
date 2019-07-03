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
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

    @BindView(R.id.edtCustomerAddress_add)
    EditText edtCustomerAddress_add;

    @BindView(R.id.edtCustomerPhNo1)
    EditText edtCustomerPhNo1;

    @BindView(R.id.edtCustomerPhNo2)
    EditText edtCustomerPhNo2;

    @BindView(R.id.edtCustomerPhNo3)
    EditText edtCustomerPhNo3;


  @BindView(R.id.relativeSave_saleAdd)
   RelativeLayout relativeSave;


    private String medcineId;
    private String customerId;

    private String idEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        setUpSaleInvoiceDate();

        saleModel = (SaleModel)getIntent().getParcelableExtra("Sale");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Data");
        setSupportActionBar(toolbar);

        if(saleModel != null){

            edtSaleInvoiceId_add.setText(String.valueOf(saleModel.getSaleInvoiceNo()));
            edtSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            edtCustomerName_add.setText(customerModel.getCustomerName());

            edtCustomerAddress_add.setText(customerModel.getCustomerAddress());
            edtCustomerPhNo1.setText(customerModel.getCustomerPhNo1());

        }

      relativeSave.setOnClickListener(v -> {
        String saleInvoiceDate =  edtSaleInvoiceDate.getText().toString();
        String saleCustomerName = edtCustomerName_add.getText().toString();

        String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
        String saleCustomerPhNo1 = edtCustomerPhNo1.getText().toString();
        String saleCustomerPhNo2 = edtCustomerPhNo2.getText().toString();
        String saleCustomerPhNo3 = edtCustomerPhNo3.getText().toString();

        SaleModel saleModel = new SaleModel();
        MedicineModel medicineModel = new MedicineModel();
        CustomerModel customerModel = new CustomerModel();

        saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
        saleModel.setSaleInvoiceDate(saleInvoiceDate);

        customerModel.setCustomerAddress(saleCustomerAddress);
        customerModel.setCustomerPhNo1(saleCustomerPhNo1);
        customerModel.setCustomerPhNo2(saleCustomerPhNo2);
        customerModel.setCustomerPhNo3(saleCustomerPhNo3);

       /* realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(saleModel);
            realm.copyToRealmOrUpdate(medicineModel);
            realm.copyToRealmOrUpdate(customerModel);
            Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
        });*/
          Intent intent = new Intent(SaleAddActivity.this,SaleMedicineInformationActivity.class);
          startActivity(intent);

       });

    }

    private void setUpSaleInvoiceDate() {
        // Registration Date
       // edtSaleInvoiceDate.setInputType(InputType.TYPE_NULL);
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
            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel);
                realm.copyToRealmOrUpdate(medicineModel);
                realm.copyToRealmOrUpdate(customerModel);
                Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        return super.onOptionsItemSelected(item);
    }
}
