package com.example.pharmacy.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pharmacy.adapter.CustomerAdapter;
import com.example.pharmacy.adapter.SaleAdapter;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleReportFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "SaleReportFragment";
    private SaleAdapter saleAdapter;
    private CustomerAdapter customerAdapter;

    @BindView(R.id.swipeSaleReort)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvSaleReport)
    RecyclerView rvSaleReport;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    public SaleReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_report, container, false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        init();
//        nfo info;
//        try {
//            info = context.getPackageManager().getPackageInfo("com.you.name", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                //String something = new String(Base64.encodeBytes(md.digest()));
//                Log.e("hash key", something);
//            }
//        } catch (PackageManager.NameNotFoundException e1) {
//            Log.e("name not found", e1.toString());
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("no such an algorithm", e.toString());
//        } catch (Exception e) {
//            Log.e("exception", e.toString());
//        }
//        getAllMedicine();
        getAllSaleReport();
        return view;
    }

    private void getAllSaleReport() {
        final List<SaleModel> saleModelList = realm.where(SaleModel.class).findAll();
        final List<CustomerModel> customerModelList = realm.where(CustomerModel.class).findAll();
        Log.d(TAG,"getData : "+saleModelList.size());
        Log.d(TAG,"getData :"+customerModelList.size());


    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        linearLayoutManager = new LinearLayoutManager(context);
        rvSaleReport.setLayoutManager(linearLayoutManager);
//        rvMedicine.setAdapter(medicineAdapter);
        rvSaleReport.setAdapter(saleAdapter);
        rvSaleReport.setAdapter(customerAdapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);

    }
}
