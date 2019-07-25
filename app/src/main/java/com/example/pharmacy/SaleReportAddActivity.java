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
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleReportAddActivity extends AppCompatActivity {

    private static final String TAG = "SaleReportAddActivity";
    private static final char UNIX_SEPRATOR = '/';

    private Realm realm;
    private SaleModel saleModel;
    private CustomerModel customerModel;
    private Toolbar toolbar;
    private Context context;

    @BindView(R.id.edtSaleInvoiceId_add)
    EditText edtSaleInvoiceId_add;

    @BindView(R.id.edtSaleInvoiceDate)
    EditText edtSaleInvoiceDate;

    @BindView(R.id.edtCustomerName_add)
    EditText edtCustomerName_add;

    @BindView(R.id.edtSaleTotalAmt_add)
    EditText edtSaleTotalAmt_add;

    @BindView(R.id.edtSaleProfit)
    EditText edtSaleProfit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report_add);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
       setUpSaleInvoiceDate();

        saleModel = (SaleModel) getIntent().getParcelableExtra("Sale");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SaleReportActivity Data");
        setSupportActionBar(toolbar);

        if (saleModel != null && customerModel != null){
            edtSaleInvoiceId_add.setText(saleModel.getSaleInvoiceNo());
            edtSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            edtCustomerName_add.setText(customerModel.getCustomerName());
            edtSaleTotalAmt_add.setText(saleModel.getSaleTotalAmt());
//            edtSaleProfit.setText(saleModel.getSaleProfit());
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
/*

        if(item.getItemId() == R.id.menu_add){
            String saleInvoiceNo = edtSaleInvoiceId_add.getText().toString();
            String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();
            String medicineCustomerName = edtCustomerName_add.getText().toString();
            String saleAmt = edtSaleTotalAmt_add.getText().toString();
            String saleProfit = edtSaleProfit.getText().toString();

            SaleModel saleModel = new SaleModel();
            CustomerModel customerModel = new CustomerModel();

            saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            customerModel.setCustomerName(medicineCustomerName);
            saleModel.setSaleTotalAmt(saleAmt);
            //saleModel.setSaleProfit(saleProfit);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel );
                Toast.makeText(SaleReportAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });
            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(customerModel );
                Toast.makeText(SaleReportAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }
        if(item.getItemId() == R.id.menu_update){
            String saleInvoiceNo = edtSaleInvoiceId_add.getText().toString();
            String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();
            String medicineCustomerName = edtCustomerName_add.getText().toString();
            String saleAmt = edtSaleTotalAmt_add.getText().toString();
            String saleProfit = edtSaleProfit.getText().toString();

            SaleModel saleModel = new SaleModel();
            CustomerModel customerModel = new CustomerModel();

            saleModel.setSaleInvoiceNo(UUID.randomUUID().toString());
            saleModel.setSaleInvoiceDate(saleInvoiceDate);
            customerModel.setCustomerName(medicineCustomerName);
            saleModel.setSaleTotalAmt(saleAmt);
          //  saleModel.setSaleProfit(saleProfit);

            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(saleModel );
                Toast.makeText(SaleReportAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });
            realm.executeTransaction(realm -> {
                realm.copyToRealmOrUpdate(customerModel );
                Toast.makeText(SaleReportAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
            });

        }
        if (item.getItemId() == R.id.menu_delete){
            String saleId = String.valueOf(saleModel.getSaleInvoiceNo());

            final SaleModel deleteResults1 = realm.where(SaleModel.class).equalTo("saleReportInvoiceNo", saleId).findFirst();
            final CustomerModel deleteResult2 = realm.where(CustomerModel.class).equalTo("SaleReportInvoiceNo",saleId).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (deleteResults1 != null) {
                        deleteResults1.deleteFromRealm();
                    }
                    if(deleteResult2 != null){
                        deleteResult2.deleteFromRealm();
                    }
                    Toast.makeText(SaleReportAddActivity.this, "Successfully Delete Data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        if(item.getItemId() == R.id.menu_cancel){
            edtSaleInvoiceId_add.setText("");
            edtSaleInvoiceDate.setText("");
            edtCustomerName_add.setText("");
            edtSaleTotalAmt_add.setText("");
            edtSaleProfit.setText("");
        }


 */
        return super.onOptionsItemSelected(item);
    }
}
