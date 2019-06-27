package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.CustomerDetailActivity;
import com.example.pharmacy.SupplierDetailActivity;
import com.example.pharmacy.model.SupplierModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {

    private static final  String TAG="SupplierAdapter";
    private List<SupplierModel> supplierModelList = new ArrayList<>();

    public void clear(){
        supplierModelList.clear();
    }

    public List<SupplierModel> getSupplierModelList(){
        return supplierModelList;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_supplier,viewGroup,false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder supplierViewHolder, int position) {
        //medicineViewHolder.bind(medicineModelList.get(position));
        supplierViewHolder.bind(supplierModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return supplierModelList.size();
    }

    public static class SupplierViewHolder extends RecyclerView.ViewHolder {

        public View sView;
        private Context context;

        @BindView(R.id.tvSupplier_supplierName)
        TextView tvSupplier_supplierName;

        @BindView(R.id.tvCompanyName)
        TextView tvCompanyName;

        @BindView(R.id.tvCompanyAddress)
        TextView tvCompanyAddress;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            sView = itemView;
        }


        public void bind(SupplierModel supplierModel) {

            if(supplierModel.getSupplierName() != null){
                tvSupplier_supplierName.setText(supplierModel.getSupplierName());
            }
            if(supplierModel.getCompanyName() != null){
                tvCompanyName.setText(supplierModel.getCompanyName());
            }
            if(supplierModel.getCompanyAddress() != null){
                tvCompanyAddress.setText(supplierModel.getCompanyAddress());
            }
            sView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SupplierDetailActivity.class);
                intent.putExtra("SupplierModel",supplierModel);
                context.startActivity(intent);
            });
        }
    }
}
