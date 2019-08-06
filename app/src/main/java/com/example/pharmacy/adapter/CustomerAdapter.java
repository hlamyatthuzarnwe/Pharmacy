package com.example.pharmacy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.model.CustomerModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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

        private Realm realm;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            cView = itemView;
            realm = Realm.getDefaultInstance();
        }

        public void bind(CustomerModel customerModel) {

            if (customerModel.getMedicineLists() != null){
                ArrayList<String> medicineList = new ArrayList<>();
                for (int i = 0;i < customerModel.getMedicineLists().size();i++){
                    medicineList.add(customerModel.getMedicineLists().get(i).getMedicineName());
                }
                String lists = TextUtils.join(", ",medicineList);
                tvMedicineName.setText(lists);
                Log.d(TAG, "bind: medicineList : " + lists);
            }

            if (customerModel.getCustomerName() != null) {
                tvCustomerName.setText(customerModel.getCustomerName());
                Log.d(TAG, "bind: name : " + customerModel.getCustomerName());
            }

            if (customerModel.getCustomerAddress() != null) {
                tvCustomerAddress.setText(customerModel.getCustomerAddress());
                Log.d(TAG, "bind: address : " + customerModel.getCustomerAddress());
            }
            if (customerModel.getCustomerPhNo1() != null) {
                tvCustomerPhNo1_detail.setText(customerModel.getCustomerPhNo1());
                Log.d(TAG, "bind: ph no : " + customerModel.getCustomerPhNo1());
            }


        }
    }
}
