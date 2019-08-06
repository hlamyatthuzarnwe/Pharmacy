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

    private static final String TAG = "MedicineAdapter";
    private List<MedicineModel> medicineModelList = new ArrayList<>();

    public void clear() {
        medicineModelList.clear();
    }

    public List<MedicineModel> getMedicineModelList() {
        return medicineModelList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_medicine, viewGroup, false);
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

        @BindView(R.id.tvMedicineCompanyName)
        TextView tvMedicineCompanyName;

        @BindView(R.id.tvMedicineSupplierName)
        TextView tvMedicineSupplierName;

        @BindView(R.id.tvMedicineQtyPerPc)
        TextView tvMedicineQtyPerPc;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            mView = itemView;
        }

        public void bind(MedicineModel medicineModel) {
            if (medicineModel.getMedicineName() != null) {
                tvMedicineName.setText(" " + medicineModel.getMedicineName());
            }
            if (medicineModel.getSupplierModel().getCompanyName() != null) {
                tvMedicineCompanyName.setText(" " + medicineModel.getSupplierModel().getCompanyName());
            }
            if (medicineModel.getSupplierModel() != null) {
                tvMedicineSupplierName.setText(" " + medicineModel.getSupplierModel().getSupplierName());
            }

            if (medicineModel.getMedicineQtyPerPc() != null) {
                tvMedicineQtyPerPc.setText(medicineModel.getMedicineQtyPerPc());
            }

            mView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MedicineDetailActivity.class);
                intent.putExtra("MedicineModel", medicineModel);
                context.startActivity(intent);
            });
        }
    }
}
