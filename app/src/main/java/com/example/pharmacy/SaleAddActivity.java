package com.example.pharmacy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.adapter.SaleAddMedicineAdapter;
import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.helper.MedicineModelList;
import com.example.pharmacy.helper.SaleAddList;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleListModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class SaleAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SaleAddActivity";
    private static final char UNIX_SEPRATOR = '/';
    String selectedCustomerLevel;
    String level;
    @BindView(R.id.edtSaleInvoiceDate)
    EditText edtSaleInvoiceDate;

    @BindView(R.id.edtCustomerName_add)
    EditText edtCustomerName_add;

    @BindView(R.id.edtCustomerAddress_add)
    EditText edtCustomerAddress_add;

    @BindView(R.id.edtCustomerPhNo1)
    EditText edtCustomerPhNo1;


    @BindView(R.id.rvMedicine_SaleAddMedicine)
    RecyclerView rvMedicine;

    @BindView(R.id.tvTotalAmount_saleAdd)
    TextView tvTotalAmount;

    @BindView(R.id.spinnerAdd)
    Spinner spinnerAdd;

    @BindView(R.id.linearLayout_medicine_saleAdd)
    RelativeLayout linearLayout_medicine_saleAdd;

    @BindView(R.id.linearAddMedicine_saleAdd)
    LinearLayout btnMedicineAdd;

    @BindView(R.id.relativeSave_saleAdd)
    RelativeLayout relativeSave;

    private Realm realm;
    private MedicineModel medicineModel;

//    @BindView(R.id.tvMedicineName)
//    EditText tvMedicineName;
//
//    @BindView(R.id.tvMedicineCostPerPc)
//    EditText tvMedicineCostPerPc;
//
//    @BindView(R.id.tvMedicineQtyPerPc)
//    EditText tvMedicineQtyPerPc;
//
//    @BindView(R.id.tvMedicineSubAmt_saleMedicine)
//    EditText tvMedicineSubAmt_saleMedicine;

    private SaleAddMedicineAdapter adapter;
    private Toolbar toolbar;

//    @BindView(R.id.tvMedicieDetail)
//    TextView tvMedicieDetail;
    private Context context;
    private RealmList<MedicineModel> medicineList;
    private SaleListModel saleList;

//
//    @BindView(R.id.relativeSave_medicineSaleAdd)
//    RelativeLayout relativeSave_medicineSaleAdd;

    private String medcineId;
    private String customerId;

    private String idEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        ButterKnife.bind(this);
        medicineList = MedicineModelList.getInstance();
        saleList = SaleAddList.getSaleItems();
        realm = Realm.getDefaultInstance();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapter = new SaleAddMedicineAdapter();
        rvMedicine.setLayoutManager(layoutManager);
        rvMedicine.setAdapter(adapter);

        setUpSaleInvoiceDate();

        //  saleModel = (SaleModel)getIntent().getParcelableExtra("Sale");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Data");
        setSupportActionBar(toolbar);


        if (medicineList != null) {
            Log.d(TAG, "onCreate: medicineList : " + medicineList.size());
            adapter.getSaleModelList().addAll(medicineList);
            adapter.notifyDataSetChanged();
            int amount = 0;
            for (int i = 0; i < medicineList.size(); i++) {
                if (medicineList.get(i) != null) {
                    amount += Integer.parseInt(medicineList.get(i).getMedicineSubAmt());
                }

            }
            tvTotalAmount.setText(String.valueOf(amount));
            /*for (MedicineModel m : medicineList){
                adapter.getSaleModelList().add(m);
            }*/
        }

        if (saleList != null) {
            edtSaleInvoiceDate.setText(saleList.getInvoiceDate());
            edtCustomerName_add.setText(saleList.getCustomerName());
            edtCustomerAddress_add.setText(saleList.getCustomerAddress());
            edtCustomerPhNo1.setText(saleList.getPhone());

        }

        btnMedicineAdd.setOnClickListener(v -> {
            insertSaleItems();


        });
        relativeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                Intent intent = new Intent(SaleAddActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        spinnerAdd.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Level 1");
        list.add("Level 2");
        list.add("Level 3");
        list.add("Level 4");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdd.setAdapter(adapter);

//        tvMedicieDetail.setOnClickListener(v -> {
//            Intent intent = new Intent(SaleAddActivity.this,SaleMedicineDetailInformationActivity.class);
//            startActivity(intent);
//        });

//        relativeSave_medicineSaleAdd.setOnClickListener(v -> {
//            String mName = tvMedicineName.getText().toString();
//            String mPrice = tvMedicineCostPerPc.getText().toString();
//            String mQty = tvMedicineQtyPerPc.getText().toString();
//            String mSubAmt = tvMedicineSubAmt_saleMedicine.getText().toString();
//
//            SaleModel saleModel = new SaleModel();
//
//            saleModel.setSaleMedicineName(mName);
//            saleModel.setSaleCostPerPc(mPrice);
//            saleModel.setSaleQtyPerPc(mQty);
//            saleModel.setSaleSubTotalAmt(mSubAmt);
//        });

    }

    private void insertSaleItems() {
        String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();
        String customeName = edtCustomerName_add.getText().toString();
        String saleCustomerAddress = edtCustomerAddress_add.getText().toString();

        String saleCustomerPhNo1 = edtCustomerPhNo1.getText().toString();

        Log.d(TAG, "insertSaleItems: invoice : "+saleInvoiceDate);
        saleList.setInvoiceDate(saleInvoiceDate);
        saleList.setCustomerName(customeName);
        saleList.setCustomerAddress(saleCustomerAddress);
        saleList.setCustomerLevel(selectedCustomerLevel);
        saleList.setPhone(saleCustomerPhNo1);
        Intent intent = new Intent(SaleAddActivity.this, SaleMedicineInformationActivity.class);
        startActivity(intent);
    }

    private void insertData() {
        SaleModel saleModel = new SaleModel();
        CustomerModel cModel = new CustomerModel();

        String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
        String saleCustomerPhNo1 = edtCustomerPhNo1.getText().toString();
        String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();

        cModel.setCustomerId(UUID.randomUUID().toString());
        cModel.setCustomerName(edtCustomerName_add.getText().toString());
        cModel.setCustomerAddress(saleCustomerAddress);
        cModel.setCustomerPhNo1(saleCustomerPhNo1);
        cModel.setMedicineLists(medicineList);
       // Log.d(TAG, "insertData: invoide : "+MedicineModelList.getNumber());

        saleModel.setSaleInvoiceNo(String.valueOf(MedicineModelList.getNumber()));
        saleModel.setSaleInvoiceDate(saleInvoiceDate);
        saleModel.setSaleCustomerName(edtCustomerName_add.getText().toString());
        saleModel.setSaleCustomerAddress(edtCustomerAddress_add.getText().toString());
        saleModel.setSaleCustomerPhNo1(edtCustomerPhNo1.getText().toString());
        saleModel.setCustomerModel(cModel);
        saleModel.setSaleTotalAmt(tvTotalAmount.getText().toString());

        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(saleModel);
            realm.copyToRealmOrUpdate(cModel);

            Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
        });
        MedicineModelList.clear();
        SaleAddList.clear();

    }
    private String invoiceNumber(){
        int i = MedicineModelList.invoiceNumber + 1;
        MedicineModelList.invoiceNumber = i;
        return String.valueOf(i);
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
        super.onBackPressed();
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        level = adapterView.getItemAtPosition(position).toString();
        selectedCustomerLevel = adapterView.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
