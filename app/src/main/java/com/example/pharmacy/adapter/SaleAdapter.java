package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.SaleDetailActivity;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

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

        @BindView(R.id.tvSaleCustomerLevel1_detail)
        TextView tvSaleCustomerLevel1_detail;


        @BindView(R.id.tvSaleCustomerLevel2_detail)
        TextView tvSaleCustomerLevel2_detail;

        @BindView(R.id.tvSaleCustomerLevel3_detail)
        TextView tvSaleCustomerLevel3_detail;

        @BindView(R.id.tvSaleCustomerLevel4_detail)
        TextView tvSaleCustomerLevel4_detail;

        @BindView(R.id.tvSaleCustomerAddress)
        TextView tvSaleCustomerAddress;

        @BindView(R.id.tvSaleMedicineName)
        TextView tvSaleMedicineName;

        @BindView(R.id.tvSaleMedicineCategory)
        TextView tvSaleMedicineCategory;

        @BindView(R.id.tvSalePricePerPc_detail)
        TextView tvSalePricePerPc_detail;

        @BindView(R.id.tvSalePricePerDz_detail)
        TextView tvSalePricePerDz_detail;

        @BindView(R.id.tvMedicineQtyPerPc)
        TextView tvMedicineQtyPerPc;

        @BindView(R.id.tvMedicineQtyPerDz)
        TextView tvMedicineQtyPerDz;

        @BindView(R.id.tvSaleTotalAmt)
        TextView tvSaleTotalAmt;

        @BindView(R.id.tvSaleUpFront_detail)
        TextView tvSaleUpFront_detail;

        @BindView(R.id.tvSaleBalance_detail)
        TextView tvSaleBalance_detail;

        @BindView(R.id.tvSaleDueDate)
        TextView tvSaleDueDate;

        @BindView(R.id.tvSaleNote_detail)
        TextView tvSaleNote_detail;

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
            if(saleModel.getSaleCustomerLevel1() != null){
                tvSaleCustomerLevel1_detail.setText(saleModel.getSaleCustomerLevel1());
            }
            if(saleModel.getSaleCustomerLevel2() != null){
                tvSaleCustomerLevel2_detail.setText(saleModel.getSaleCustomerLevel2());
            }
            if(saleModel.getSaleCustomerLevel3() != null){
                tvSaleCustomerLevel3_detail.setText(saleModel.getSaleCustomerLevel3());
            }
            if(saleModel.getSaleCustomerLevel4() != null){
                tvSaleCustomerLevel4_detail.setText(saleModel.getSaleCustomerLevel4());
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
            if(saleModel.getSaleCostPerPc() != null){
                tvSalePricePerPc_detail.setText(saleModel.getSaleCostPerPc());
            }
            if (saleModel.getSaleCostPerDz() != null){
                tvSalePricePerDz_detail.setText(saleModel.getSaleCostPerDz());
            }
            if(saleModel.getSaleQtyPerPc() != null){
                tvMedicineQtyPerPc.setText(saleModel.getSaleQtyPerPc());
            }
            if (saleModel.getSaleQtyPerDz() != null){
                tvMedicineQtyPerDz.setText(saleModel.getSaleQtyPerDz());
            }
            if (saleModel.getSaleTotalAmt() != null){
                tvSaleTotalAmt.setText(saleModel.getSaleTotalAmt());
            }
            if (saleModel.getSaleUpFront() != null){
                tvSaleUpFront_detail.setText(saleModel.getSaleUpFront());
            }
            if (saleModel.getSaleBalance() != null){
                tvSaleBalance_detail.setText(saleModel.getSaleBalance());
            }
            if (saleModel.getSaleDuedate() != null){
                tvSaleDueDate.setText(saleModel.getSaleDuedate());
            }
            if (saleModel.getSaleNote() != null){
                tvSaleNote_detail.setText(saleModel.getSaleNote());
            }

        }
    }
}
