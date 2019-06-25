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

    private static final String TAG="CustomerAdapter";
    private List<CustomerModel> customerModelList = new ArrayList<>();

    public void clear(){
        customerModelList.clear();
    }

    public List<CustomerModel>getCustomerModelList(){
        return customerModelList;
    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_customer,viewGroup,false);
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

        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;

        @BindView(R.id.tvCustomerLevel)
        TextView tvCustomerLevel;

        @BindView(R.id.tvCustomerAddress)
        TextView tvCustomerAddress;

        @BindView(R.id.tvCustomerPhNo1_detail)
        TextView tvCustomerPhNo1_detail;

        @BindView(R.id.tvCustomerPhNo2_detail)
        TextView tvCustomerPhNo2_detail;

        @BindView(R.id.tvCustomerPhNo3_detail)
        TextView tvCustomerPhNo3_detail;

        @BindView(R.id.tvCustomerPhNo4_detail)
        TextView tvCustomerPhNo4_detail;

        @BindView(R.id.tvCustomerPhNo5_detail)
        TextView tvCustomerPhNo5_detail;

        @BindView(R.id.tvCustomerNote_detail)
        TextView tvCustomerNote_detail;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            cView = itemView;
        }

        public void bind(CustomerModel customerModel) {
            if (customerModel.getCustomerName() != null){
                tvCustomerName.setText(customerModel.getCustomerName());
                Log.d(TAG, "bind: name : "+customerModel.getCustomerName());
            }
            if(customerModel.getCustomerAddress() != null){
                tvCustomerAddress.setText(customerModel.getCustomerAddress());
            }
            if (customerModel.getCustomerPhNo1() != null){
                tvCustomerPhNo1_detail.setText(customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerPhNo2() != null){
                tvCustomerPhNo2_detail.setText(customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerPhNo3() != null){
                tvCustomerPhNo3_detail.setText(customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerPhNo4() != null){
                tvCustomerPhNo4_detail.setText(customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerPhNo5() != null){
                tvCustomerPhNo5_detail.setText(customerModel.getCustomerPhNo1());
            }
            if (customerModel.getCustomerNote() != null){
                tvCustomerNote_detail.setText(customerModel.getCustomerNote());
            }
//            if(customerModel.getCustomerLevel() != null){
//                tvCustomerLevel.setText(customerModel.getCustomerLevel());
//                Log.d(TAG, "bind: level : "+customerModel.getCustomerLevel());
//            }
//            if(customerModel.getCustomerAddress() != null){
//                tvCustomerAddress.setText(customerModel.getCustomerLevel());
//                Log.d(TAG, "bind: address : "+customerModel.getCustomerAddress());
//            }

            cView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CustomerDetailActivity.class);
                intent.putExtra("CustomerModel",customerModel);
                context.startActivity(intent);
            });
        }
    }
}
