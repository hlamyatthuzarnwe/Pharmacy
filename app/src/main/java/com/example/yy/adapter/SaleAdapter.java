package com.example.yy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yy.MedicineDetailActivity;
import com.example.yy.R;
import com.example.yy.SaleDetailActivity;
import com.example.yy.model.SaleModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleViewHolder> {

    private static final String TAG = "SaleAdapter";
    private List<SaleModel> saleModelList = new ArrayList<>();

    public void clear(){
        saleModelList.clear();
    }

    public List<SaleModel> getSaleModelList() {
        return saleModelList;
    }

    @NonNull
    @Override
    public SaleAdapter.SaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale,viewGroup,false);
        return new SaleAdapter.SaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.SaleViewHolder saleViewHolder, int position) {

        saleViewHolder.bind(saleModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder {

        public View saleView;
        private Context context;

        @BindView(R.id.tvSaleInvoiceNo)
        TextView tvSaleInvoiceNo;

        @BindView(R.id.tvSaleInvoiceDate)
        TextView tvSaleInvoiceDate;

        @BindView(R.id.tvSaleCustomerName)
        TextView tvSaleCustomerName;

        @BindView(R.id.tvSaleCustomerLevel_detail)
        TextView tvSaleCustomerLevel_detail;

        @BindView(R.id.tvSaleCustomerAddress)
        TextView tvSaleCustomerAddress;

        @BindView(R.id.tvSaleMedicineName)
        TextView tvSaleMedicineName;

        @BindView(R.id.tvSaleMedicineCategory)
        TextView tvSaleMedicineCategory;

        @BindView(R.id.tvSaleMedicinePrice)
        TextView tvSaleMedicinePrice;

        @BindView(R.id.tvSaleTotalAmt)
        TextView tvSaleTotalAmt;

        @BindView(R.id.tvSaleDueDate)
        TextView tvSaleDueDate;

        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            saleView = itemView;
        }

        public void bind(SaleModel saleModel) {
//             if(saleModel.getSaleInvoiceNo() != null){
//                tvSaleInvoiceNo.setText(saleModel.getSaleInvoiceNo());
//            }
            if(saleModel.getSaleInvoiceDate() != null){
                tvSaleInvoiceDate.setText(saleModel.getSaleInvoiceDate());
            }
            if(saleModel.getSaleCustomerName() != null){
                tvSaleCustomerName.setText(saleModel.getSaleCustomerName());
            }
            if(saleModel.getSaleCustomerLevel() != null){
                tvSaleCustomerLevel_detail.setText(saleModel.getSaleCustomerLevel());
            }
            if(saleModel.getSaleCustomerAddress() != null){
                tvSaleCustomerAddress.setText(saleModel.getSaleCustomerAddress());
            }
            if (saleModel.getSaleMedicineName() !=  null){
                tvSaleMedicineName.setText(saleModel.getSaleMedicineName());
            }
            if(saleModel.getSaleCategory() != null){
                tvSaleMedicineCategory.setText(saleModel.getSaleCategory());
            }
            if(saleModel.getSalePrice() != null){
                tvSaleMedicinePrice.setText(saleModel.getSalePrice());
            }
            if (saleModel.getSaleTotalAmt() != null){
                tvSaleTotalAmt.setText(saleModel.getSaleTotalAmt());
            }
            if (saleModel.getSaleDuedate() != null){
                tvSaleDueDate.setText(saleModel.getSaleDuedate());
            }

            saleView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleDetailActivity.class);
                intent.putExtra("SaleModel",saleModel);
                context.startActivity(intent);
            });
        }
    }
}