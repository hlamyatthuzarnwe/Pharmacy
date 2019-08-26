package com.example.pharmacy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import com.example.pharmacy.helper.AmountCallback;
import com.example.pharmacy.helper.DateTimeHelper;
import com.example.pharmacy.helper.MedicineModelList;
import com.example.pharmacy.helper.SaleAddList;
import com.example.pharmacy.helper.SingleCustomerModel;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.pharmacy.model.SingleQty;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class SaleAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        AmountCallback {

    private static final String TAG = "SaleAddActivity";
    private static final char UNIX_SEPRATOR = '/';
    String selectedCustomerLevel;
    String level;

    @BindView(R.id.tilCustomerName_saleAdd)
    TextInputLayout tilCustomerName;

    @BindView(R.id.tilCustomerPh_saleAdd)
    TextInputLayout tilCustomerPh;

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

    @BindView(R.id.linearExistingCustomer_saleAdd)
    LinearLayout linearExistingCustomer;
    HashMap<String, String> hashMap = new HashMap<>();
    String[] parts;
    String qty = "";
    ArrayList<String> medicineKeys = new ArrayList<>();
    List<String> arList = new ArrayList<>();

    //    @BindView(R.id.tvMedicieDetail)
//    TextView tvMedicieDetail;
    private Realm realm;
    private MedicineModel medicineModel;
    private ArrayList<String> arrQty;
    private SaleAddMedicineAdapter adapter;
    private Toolbar toolbar;
    private Context context;
    private RealmList<MedicineModel> medicineList;
    private ArrayList<MedicineModel> mModel = new ArrayList<>();
    private SaleModel saleList;
    private CustomerModel singleCustomer;
    private SaleModel saleModel;
    private String idInvoice;
    private String idInvoiceSingle;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Data");
        setSupportActionBar(toolbar);
        medicineList = MedicineModelList.getInstance().getMedicineModelRealmList();
        singleCustomer = SingleCustomerModel.getCustomer();

        saleModel = (SaleModel) getIntent().getParcelableExtra("Sale");
        mModel = getIntent().getParcelableArrayListExtra("Medicine");

        arrQty = SingleQty.getQty();
        saleList = SaleAddList.getSaleItems();
        realm = Realm.getDefaultInstance();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (saleModel != null) {
            arrQty.add(saleModel.getSaleQtyPerPc());

        }
        adapter = new SaleAddMedicineAdapter(TextUtils.join(",", arrQty));

        adapter.registerCallback(SaleAddActivity.this);
        rvMedicine.setLayoutManager(layoutManager);
        rvMedicine.setAdapter(adapter);


        setUpSaleInvoiceDate();


        // singleCustomer = (CustomerModel) getIntent().getParcelableExtra("CustomerModel");


        if (singleCustomer != null) {
            //  Log.d(TAG, "onCreate: singel cu name ; " + singleCustomer.getCustomerName());
            edtCustomerName_add.setText(singleCustomer.getCustomerName());
            edtCustomerAddress_add.setText(singleCustomer.getCustomerAddress());
            edtCustomerPhNo1.setText(singleCustomer.getCustomerPhNo1());
        }

        if (saleList.getSaleCustomerName() != null) {
            //  Log.d(TAG, "onCreate: InvoiceDate : " + saleList.getSaleInvoiceDate());
            idInvoiceSingle = saleList.getSaleInvoiceNo();
            edtSaleInvoiceDate.setText(saleList.getSaleInvoiceDate());
            if (TextUtils.isEmpty(singleCustomer.getCustomerName())) {
                edtCustomerName_add.setText(saleList.getSaleCustomerName());
                edtCustomerAddress_add.setText(saleList.getSaleCustomerAddress());
                edtCustomerPhNo1.setText(saleList.getSaleCustomerPhNo1());
            }


        } else if (saleModel != null) {
            try {
                for (String comma : TextUtils.split(saleModel.getSaleQtyPerPc(), ",")) {
                    parts = comma.split(":", 2);
                    hashMap.put(parts[0], parts[1]);

                }
                for (String ss : hashMap.keySet()) {
                    medicineKeys.add(ss);

                }
                //  List<String> mm = medicineKeys.subList();
                arList = medicineKeys;
                String[] finalAs = arList.toArray(new String[0]);
                RealmResults<MedicineModel> mmLists = realm.where(MedicineModel.class)
                        .in("medicineName", finalAs).findAll();

                adapter.clear();
                medicineList.addAll(mmLists);
                adapter.notifyDataSetChanged();

            } catch (ArrayIndexOutOfBoundsException e) {
                Log.d(TAG, "onCreate: e : " + e.getLocalizedMessage());
            }



          /*  Log.d(TAG, "onCreate: saleModel : " + saleModel.getSaleInvoiceNo());
            Log.d(TAG, "onCreate: " + saleModel.getSaleCustomerName());
            Log.d(TAG, "onCreate: " + saleModel.getSaleCustomerAddress());
            Log.d(TAG, "onCreate: " + saleModel.getSaleCustomerPhNo1());*/
            idInvoice = saleModel.getSaleInvoiceNo();
            isEdit = !TextUtils.isEmpty(idInvoice);
            edtSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            edtCustomerName_add.setText(saleModel.getSaleCustomerName());
            edtCustomerAddress_add.setText(saleModel.getSaleCustomerAddress());
            edtCustomerPhNo1.setText(saleModel.getSaleCustomerPhNo1());
        }

        if (medicineList != null) {
            adapter.clear();
            //  Log.d(TAG, "onCreate: medicineList : " + medicineList.size());
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


        linearExistingCustomer.setOnClickListener(view -> {
            insertSaleItems();
            Intent intent = new Intent(SaleAddActivity.this, SearchCustomerActivity.class);
            startActivity(intent);
        });

        btnMedicineAdd.setOnClickListener(v -> {
            insertSaleItems();
            Intent intent = new Intent(SaleAddActivity.this, SaleMedicineInformationActivity.class);
            startActivity(intent);


        });
        relativeSave.setOnClickListener(view -> {

            if (validateField()) {
                insertData();
                Intent intent = new Intent(SaleAddActivity.this, MainActivity.class);
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

    }

  /*
    private void insertCustomerItems() {
        if(isEdit){
            saleList.setSaleInvoiceNo(saleList.getSaleInvoiceNo());
        }
        saleList.setSaleInvoiceDate(edtSaleInvoiceDate.getText().toString());

    }
   */

    private boolean validateField() {
        if (TextUtils.isEmpty(edtCustomerName_add.getText().toString().trim())) {
            tilCustomerName.setErrorEnabled(true);
            tilCustomerName.setError("Customer Name required");
            return false;
        }
        if (TextUtils.isEmpty(edtCustomerPhNo1.getText().toString().trim())) {
            tilCustomerPh.setErrorEnabled(true);
            tilCustomerPh.setError("Customer Phone Number required");
            return false;
        }
        if (medicineList == null || medicineList.size() == 0) {
            Toast.makeText(SaleAddActivity.this, "Please Choose Medicine", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void insertSaleItems() {
        String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();
        String customeName = edtCustomerName_add.getText().toString();
        String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
        String saleCustomerPhNo1 = edtCustomerPhNo1.getText().toString();

        //  Log.d(TAG, "insertSaleItems: invoice : "+saleInvoiceDate);
        if (saleModel != null) {
            saleList.setSaleInvoiceNo(saleModel.getSaleInvoiceNo());
        }
        saleList.setSaleInvoiceDate(saleInvoiceDate);
        saleList.setSaleCustomerName(customeName);
        saleList.setSaleCustomerAddress(saleCustomerAddress);
        saleList.setSaleCustomerPhNo1(saleCustomerPhNo1);

    }

    private void insertData() {
        RealmList<SaleModel> sale = new RealmList<>();
        SaleModel sModel = new SaleModel();
        CustomerModel cModel = new CustomerModel();

        String saleCustomerAddress = edtCustomerAddress_add.getText().toString();
        String saleCustomerPhNo1 = edtCustomerPhNo1.getText().toString();
        String saleInvoiceDate = edtSaleInvoiceDate.getText().toString();


        // Log.d(TAG, "insertData: invoide : "+MedicineModelList.getNumber());
        RealmResults<SaleModel> ss = realm.where(SaleModel.class).findAll();
        int invoiceNumber = ss.size() + 1;
        if (!TextUtils.isEmpty(saleList.getSaleInvoiceNo())) {
            sModel.setSaleInvoiceNo(saleList.getSaleInvoiceNo());
        } else if (saleModel != null) {
            if (saleModel.getSaleInvoiceNo() != null) {
                sModel.setSaleInvoiceNo(saleModel.getSaleInvoiceNo());
            }

        } else {
            sModel.setSaleInvoiceNo(String.valueOf(invoiceNumber));
        }
        sModel.setSaleInvoiceDate(saleInvoiceDate);
        sModel.setSaleCustomerName(edtCustomerName_add.getText().toString());
        sModel.setSaleCustomerAddress(edtCustomerAddress_add.getText().toString());
        sModel.setSaleCustomerPhNo1(edtCustomerPhNo1.getText().toString());
        sModel.setSaleTotalAmt(tvTotalAmount.getText().toString());
        sModel.setSaleQtyPerPc(TextUtils.join(",", arrQty));
        sModel.setMedicineLists(medicineList);
        sale.add(sModel);

        if (!TextUtils.isEmpty(singleCustomer.getCustomerName())) {
            //  Log.d(TAG, "insertData: sin cu name : " + singleCustomer);
            RealmList<SaleModel> ssm = new RealmList<>();
            ssm.addAll(singleCustomer.getSaleModels());
            ssm.addAll(sale);

            singleCustomer.setSaleModels(ssm);


            //    Log.d(TAG, "insertData: medicine size : " + medicineList.size());
            //  Log.d(TAG, "insertData: ssm : " + ssm);

        } else {
            if (isEdit) {
                CustomerModel customer = realm.where(CustomerModel.class)
                        .equalTo("customerName", saleModel.getSaleCustomerName())
                        .and()
                        .equalTo("customerPhNo1", saleModel.getSaleCustomerPhNo1())
                        .findFirst();
                if (customer != null) {
                    cModel.setCustomerId(customer.getCustomerId());
                }
            }
           /* else if (saleList.getSaleCustomerName() != null){
                CustomerModel customer = realm.where(CustomerModel.class)
                        .equalTo("customerName",saleList.getSaleCustomerName())
                        .and()
                        .equalTo("customerPhNo1",saleList.getSaleCustomerPhNo1())
                        .findFirst();
                if (customer != null) {
                    cModel.setCustomerId(customer.getCustomerId());
                }
            }*/
            else {
                cModel.setCustomerId(UUID.randomUUID().toString());
            }
            //  Log.d(TAG, "insertData: null serach");

            cModel.setCustomerName(edtCustomerName_add.getText().toString());
            cModel.setCustomerAddress(saleCustomerAddress);
            cModel.setCustomerPhNo1(saleCustomerPhNo1);
            cModel.setSaleModels(sale);
        }


        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(sModel);
            if (!TextUtils.isEmpty(singleCustomer.getCustomerName())) {
                //   Log.d(TAG, "insertData: searched Customer");


                realm.copyToRealmOrUpdate(singleCustomer);
            } else {
                //    Log.d(TAG, "insertData: normal customer");
                realm.copyToRealmOrUpdate(cModel);
            }
            MedicineModelList.getInstance().clear();
            SaleAddList.clear();
            SingleCustomerModel.clear();
            SingleQty.clear();

            Toast.makeText(SaleAddActivity.this, "Successfully Add Data", Toast.LENGTH_SHORT).show();
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
        super.onBackPressed();
        MedicineModelList.getInstance().clear();
        SaleAddList.clear();
        SingleCustomerModel.clear();
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


    @Override
    public void onTotalAmount(String amount) {
        tvTotalAmount.setText(amount);
    }
}
