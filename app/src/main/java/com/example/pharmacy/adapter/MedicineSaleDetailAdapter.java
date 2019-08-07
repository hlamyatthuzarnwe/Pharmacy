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

import com.example.pharmacy.MedicineDetailActivity;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicineSaleDetailAdapter extends RecyclerView.Adapter<MedicineSaleDetailAdapter.MedicineSaleDetailViewHolder> {

    private static final String TAG = MedicineSaleDetailAdapter.class.getSimpleName();
    private List<MedicineModel> medicineModelList = new ArrayList<>();

    @NonNull
    @Override
    public MedicineSaleDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_medicine_sale_detail, viewGroup, false);
        return new MedicineSaleDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineSaleDetailViewHolder medicineSaleDetailViewHolder, int i) {
        medicineSaleDetailViewHolder.bind(medicineModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return medicineModelList.size();
    }

    public void clear() {
        medicineModelList.clear();
    }


    public List<MedicineModel> getMedicineModelList() {
        return medicineModelList;
    }


    public class MedicineSaleDetailViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        private Context context;

        @BindView(R.id.tvSaleMedicineName_detail)
        TextView tvMedicineName;

        @BindView(R.id.tvSupplierName_detail)
        TextView tvSupplierName;

        @BindView(R.id.tvSaleTotalAmt_detail)
        TextView tvAmount;

        @BindView(R.id.tvSaleQuantityPerPc_detail)
        TextView tvQty;

        public MedicineSaleDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            mView = itemView;
        }

        public void bind(MedicineModel medicineModel) {
            Log.d(TAG, "bind: ");
            if (medicineModel.getMedicineName() != null) {
                tvMedicineName.setText(medicineModel.getMedicineName());
            }
            if (medicineModel.getMedicineQtyPerPc() != null) {
                tvQty.setText(medicineModel.getMedicineQtyPerPc());
            }
            if (medicineModel.getSupplierModel() != null) {
                tvSupplierName.setText(medicineModel.getSupplierModel().getSupplierName());
            }


            if (medicineModel.getMedicineQtyPerPc() != null &&
                    medicineModel.getMedicineSalePcPerPrice1() != null) {
                int price = Integer.parseInt(medicineModel.getMedicineSalePcPerPrice1());
                int qty = Integer.parseInt(medicineModel.getMedicineQtyPerPc());
                int amount = price * qty;
                tvAmount.setText(String.valueOf(amount));
            }

        }
    }
}
