package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.CustomerDetailActivity;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private static final String TAG = "CustomerAdapter";
    private List<CustomerModel> customerModelList = new ArrayList<>();

    public void clear() {
        customerModelList.clear();
    }

    public List<CustomerModel> getCustomerModelList() {
        return customerModelList;
    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_customer, viewGroup, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerViewHolder customerViewHolder, int position) {

        customerViewHolder.bind(customerModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        public View cView;
        private Context context;

        @BindView(R.id.tvMedicineName)
        TextView tvMedicineName;

        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;

        @BindView(R.id.tvCustomerPhNo1_detail)
        TextView tvCustomerPhNo1_detail;

        @BindView(R.id.tvCustomerAddress)
        TextView tvCustomerAddress;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            cView = itemView;

        }

        public void bind(CustomerModel customerModel) {

            if (customerModel.getCustomerName() != null) {
                tvCustomerName.setText(customerModel.getCustomerName());
                Log.d(TAG, "bind: name : " + customerModel.getCustomerName());
            }
            if (customerModel.getCustomerAddress() != null) {
                tvCustomerAddress.setText(customerModel.getCustomerAddress());
            }
            if (customerModel.getCustomerPhNo1() != null) {
                tvCustomerPhNo1_detail.setText(customerModel.getCustomerPhNo1());
                Log.d(TAG, "bind: level : " + customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerAddress() != null) {
                tvCustomerAddress.setText(customerModel.getCustomerLevel());
                Log.d(TAG, "bind: address : " + customerModel.getCustomerAddress());
            }

            cView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CustomerDetailActivity.class);
                intent.putExtra("CustomerModel", customerModel);
                context.startActivity(intent);
            });
        }
    }
}
