package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.MedicineDetailActivity;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private static final String TAG="MedicineAdapter";
    private List<MedicineModel> medicineModelList = new ArrayList<>();

    public void clear(){
        medicineModelList.clear();
    }

    public List<MedicineModel> getMedicineModelList(){
        return medicineModelList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_medicine,viewGroup,false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder medicineViewHolder, int position) {

        medicineViewHolder.bind(medicineModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return medicineModelList.size();
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        private Context context;

        @BindView(R.id.tvMedicineName)
        TextView tvMedicineName;

        @BindView(R.id.tvMedicineCode_detail)
        TextView tvMedicineCode_detail;

        @BindView(R.id.tvMedicineCategory_detail)
        TextView tvMedicineCategory_detail;

        @BindView(R.id.tvMedicineCostPerPc)
        TextView tvMedicineCostPerPc;

        @BindView(R.id.tvMedicineCostPerDz)
        TextView tvMedicineCostPerDz;

        @BindView(R.id.tvMedicineQtyPerPc)
        TextView tvMedicineQtyPerPc;

        @BindView(R.id.tvMedicineQtyPerDz)
        TextView tvMedicineQtyPerDz;

        @BindView(R.id.tvCompanyName)
        TextView tvCompanyName;

        @BindView(R.id.tvMedicineSupplierName)
        TextView tvMedicineSupplierName;

        @BindView(R.id.tvMedicinePh1)
        TextView tvMedicinePh1;

        @BindView(R.id.tvMedicinePh2)
        TextView tvMedicinePh2;

        @BindView(R.id.tvMedicinePh3)
        TextView tvMedicinePh3;

        @BindView(R.id.tvMedicineViberPh)
        TextView tvMedicineViberPh;

         @BindView(R.id.tvMedicinePayment)
         TextView tvMedicinePayment;

         @BindView(R.id.tvMedicineSalePerPc1)
         TextView tvMedicineSalePerPc1;

         @BindView(R.id.tvMedicineSalePerDz1)
         TextView tvMedicineSalePerDz1;

         @BindView(R.id.tvMedicineSalePerPc2)
         TextView tvMedicineSalePerPc2;

         @BindView(R.id.tvMedicineSalePerDz2)
         TextView tvMedicineSalePerDz2;

         @BindView(R.id.tvMedicineSalePerPc3)
         TextView tvMedicineSalePerPc3;

         @BindView(R.id.tvMedicineSalePerDz3)
         TextView tvMedicineSalePerDz3;

         @BindView(R.id.tvMedicineSalePerPc4)
         TextView tvMedicineSalePerPc4;

         @BindView(R.id.tvMedicineSalePerDz4)
         TextView tvMedicineSalePerDz4;

         @BindView(R.id.tvMedicineReceivedDate)
         TextView tvMedicineReceivedDate;

         @BindView(R.id.tvMedicineExpDate)
         TextView tvMedicineExpDate;

         @BindView(R.id.tvMedicineNote)
         TextView tvMedicineNote;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            mView = itemView;
        }

        public void bind(MedicineModel medicineModel) {
            if(medicineModel.getMedicineName() != null){
                tvMedicineName.setText(medicineModel.getMedicineName());
            }
            if (medicineModel.getMedicineCode() != null){
                tvMedicineCode_detail.setText(medicineModel.getMedicineCode());
            }
            if(medicineModel.getMedicineCategory() != null){
                tvMedicineCategory_detail .setText(medicineModel.getMedicineCategory());
            }
            if(medicineModel.getMedicineCostPerPc() != null){
                tvMedicineCostPerPc.setText(medicineModel.getMedicineCostPerPc());
            }
            if (medicineModel.getMedicineCostPerDz() != null){
                tvMedicineCostPerDz.setText(medicineModel.getMedicineCostPerDz());
            }
            if (medicineModel.getMedicineQtyPerPc() != null){
                tvMedicineQtyPerPc.setText(medicineModel.getMedicineQtyPerPc());
            }
            if (medicineModel.getMedicineQtyPerDz() != null) {
                tvMedicineQtyPerDz.setText(medicineModel.getMedicineQtyPerDz());
            }
            if (medicineModel.getMedicineCompanyName() != null){
                tvCompanyName.setText(medicineModel.getMedicineCompanyName());
            }
            if (medicineModel.getMedicineSupplierName() != null){
                tvMedicineSupplierName.setText(medicineModel.getMedicineSupplierName());
            }
            if (medicineModel.getMedicineContactPh1() != null){
                tvMedicinePh1.setText(medicineModel.getMedicineContactPh1());
            }
            if (medicineModel.getMedicineContactPh2() != null){
                tvMedicinePh2.setText(medicineModel.getMedicineContactPh2());
            }
            if (medicineModel.getMedicineContactPh3() != null){
                tvMedicinePh3.setText(medicineModel.getMedicineContactPh3());
            }
            if (medicineModel.getMedicineViberPh() != null){
                tvMedicineViberPh.setText(medicineModel.getMedicineViberPh());
            }
            if (medicineModel.getMedicinePayment() != null){
                tvMedicinePayment.setText(medicineModel.getMedicinePayment());
            }
            if (medicineModel.getMedicineSalePcPerPrice1() != null){
                tvMedicineSalePerPc1.setText(medicineModel.getMedicineSalePcPerPrice1());
            }
            if (medicineModel.getMedicineSaleDzPerPrice1() != null){
                tvMedicineSalePerDz1.setText(medicineModel.getMedicineSaleDzPerPrice1());
            }

            if (medicineModel.getMedicineSalePcPerPrice2() != null){
                tvMedicineSalePerPc2.setText(medicineModel.getMedicineSalePcPerPrice1());
            }
            if (medicineModel.getMedicineSaleDzPerPrice2() != null){
                tvMedicineSalePerDz2.setText(medicineModel.getMedicineSaleDzPerPrice1());
            }

            if (medicineModel.getMedicineSalePcPerPrice3() != null){
                tvMedicineSalePerPc3.setText(medicineModel.getMedicineSalePcPerPrice1());
            }
            if (medicineModel.getMedicineSaleDzPerPrice3() != null){
                tvMedicineSalePerDz3.setText(medicineModel.getMedicineSaleDzPerPrice1());
            }

            if (medicineModel.getMedicineSalePcPerPrice4() != null){
                tvMedicineSalePerPc4.setText(medicineModel.getMedicineSalePcPerPrice1());
            }
            if (medicineModel.getMedicineSaleDzPerPrice4() != null){
                tvMedicineSalePerDz4.setText(medicineModel.getMedicineSaleDzPerPrice1());
            }
            if (medicineModel.getMedicineReceivedDate() != null){
                tvMedicineReceivedDate.setText(medicineModel.getMedicineReceivedDate());
            }
            if (medicineModel.getMedicineExpDate() != null){
                tvMedicineExpDate.setText(medicineModel.getMedicineExpDate());
            }
            if (medicineModel.getMedicineNote() != null){
                tvMedicineNote.setText(medicineModel.getMedicineNote());
            }
        }
    }
}
