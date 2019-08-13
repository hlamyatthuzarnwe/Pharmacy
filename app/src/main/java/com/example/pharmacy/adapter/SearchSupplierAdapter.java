package com.example.pharmacy.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.MedicineAddActivity;
import com.example.pharmacy.helper.SingleCustomerModel;
import com.example.pharmacy.model.SingleSupplierModel;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchSupplierAdapter extends RecyclerView.Adapter<SearchSupplierAdapter.SearchSupplierViewHolder> {

    private static final String TAG = "SearchSupplierAdapter";
    private List<SupplierModel> supplierModelList = new ArrayList<>();

    public void clear() {
        supplierModelList.clear();
    }

    public List<SupplierModel> getSupplierModelList() {
        return supplierModelList;
    }

    @NonNull
    @Override
    public SearchSupplierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_supplier_search, viewGroup, false);
        return new SearchSupplierAdapter.SearchSupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSupplierViewHolder searchSupplierViewHolder, int i) {
        searchSupplierViewHolder.bind(supplierModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return supplierModelList.size();
    }

    public class SearchSupplierViewHolder extends RecyclerView.ViewHolder {

        public View sView;
        @BindView(R.id.supplierName_itemSearch)
        TextView supplierName;

        @BindView(R.id.companyName_itemSearch)
        TextView companyName;

        private Context context;
        private SupplierModel sModel;

        public SearchSupplierViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            sView = itemView;
            sModel = SingleSupplierModel.getSupplier();
        }

        public void bind(SupplierModel supplierModel) {

            supplierName.setText(supplierModel.getSupplierName());
            companyName.setText(supplierModel.getCompanyName());

            sView.setOnClickListener(view -> {
                sModel.setSupplierId(supplierModel.getSupplierId());
                sModel.setSupplierName(supplierModel.getSupplierName());
                sModel.setCompanyName(supplierModel.getCompanyName());
                sModel.setSuplier_phno1(supplierModel.getSuplier_phno1());
                sModel.setCompanyAddress(supplierModel.getCompanyAddress());
                sModel.setSupplierNote(supplierModel.getSupplierNote());

               Log.d(TAG, "bind: get supplirt name "+sModel.getSupplierName());

                Intent intent = new Intent(context, MedicineAddActivity.class);
               // intent.putExtra("SupplierModel",supplierModel);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                //((Activity)context).finish();
            });

        }
    }
}
