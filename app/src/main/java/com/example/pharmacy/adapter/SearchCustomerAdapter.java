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

import com.example.pharmacy.SaleAddActivity;
import com.example.pharmacy.SaleMedicineDetailInformationActivity;
import com.example.pharmacy.helper.SingleCustomerModel;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCustomerAdapter extends RecyclerView.Adapter<SearchCustomerAdapter.SearchCustomerViewHolder> {

    private static final String TAG = "SearchCustomerAdapter";
    private List<CustomerModel> customerModelList = new ArrayList<>();

    public void clear() {
        customerModelList.clear();
    }

    public List<CustomerModel> getCustomerModelList() {
        return customerModelList;
    }

    @NonNull
    @Override
    public SearchCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_customer_search, viewGroup, false);
        return new SearchCustomerAdapter.SearchCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCustomerViewHolder searchViewHolder, int i) {
        searchViewHolder.bind(customerModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    public class SearchCustomerViewHolder extends RecyclerView.ViewHolder {
        public View cView;
        @BindView(R.id.customerName_itemSearch)
        TextView customerName;

        private Context context;
        private CustomerModel cModel;

        public SearchCustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            cView = itemView;
            cModel = SingleCustomerModel.getCustomer();
        }

        public void bind(CustomerModel model) {
           // tvName.setText(model.getMedicineName());
            customerName.setText(model.getCustomerName());
           // customerPhone.setText(model.getCustomerPhNo1());

            cView.setOnClickListener(v -> {

               // Log.d(TAG, "bind: cModel : "+cModel.getCustomerName());

                cModel.setCustomerId(model.getCustomerId());
                cModel.setCustomerName(model.getCustomerName());
                cModel.setCustomerAddress(model.getCustomerAddress());
                cModel.setCustomerInvoiceDate(model.getCustomerInvoiceDate());
                cModel.setCustomerPhNo1(model.getCustomerPhNo1());
                cModel.setCustomerNote(model.getCustomerNote());

                cModel.setSaleModels(model.getSaleModels());

                 Log.d(TAG, "bind: cModel : "+cModel.getCustomerName());

                Intent intent = new Intent(context, SaleAddActivity.class);
                context.startActivity(intent);
                ((Activity)context).finish();

            });
        }
    }
}