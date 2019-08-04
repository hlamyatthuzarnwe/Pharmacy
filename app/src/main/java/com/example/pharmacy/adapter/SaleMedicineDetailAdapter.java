package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.SaleMedicineDetailInformationActivity;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleMedicineDetailAdapter extends RecyclerView.Adapter<SaleMedicineDetailAdapter.SaleMedicineViewHolder> {

    private static final String TAG = "SaleMedicineDetailAdapter";
    private List<MedicineModel> medicineModelList = new ArrayList<>();

    public void clear() {
        medicineModelList.clear();
    }

    public List<MedicineModel> getMedicineModelList() {
        return medicineModelList;
    }

    @NonNull
    @Override
    public SaleMedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale, viewGroup, false);
        return new SaleMedicineDetailAdapter.SaleMedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleMedicineViewHolder saleMedicineViewHolder, int position) {

        saleMedicineViewHolder.bind(medicineModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SaleMedicineViewHolder extends RecyclerView.ViewHolder {

        public View saleView;
        private Context context;

        @BindView(R.id.tvMedicineName_saleMedicine)
        TextView tvMedicineName_saleMedicine;

        @BindView(R.id.tvMedicinePrice_saleMedicine)
        TextView tvMedicinePrice_saleMedicine;

        @BindView(R.id.tvMedicineQty_saleMedicine)
        TextView tvMedicineQty_saleMedicine;

        @BindView(R.id.tvMedicineSubAmt_saleMedicine)
        TextView tvMedicineSubAmt_saleMedicine;

        public SaleMedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            saleView = itemView;
        }

        public void bind(MedicineModel medicineModel) {

            if (medicineModel.getMedicineName() != null) {
                tvMedicineName_saleMedicine.setText(medicineModel.getMedicineName());
            }
            if (medicineModel.getMedicineCostPerPc() != null) {
                tvMedicinePrice_saleMedicine.setText(medicineModel.getMedicineCostPerPc());
            }
            if (medicineModel.getMedicineQtyPerPc() != null) {
                tvMedicineQty_saleMedicine.setText(medicineModel.getMedicineQtyPerPc());
            }
            if (medicineModel.getMedicineSubAmt() != null) {
                tvMedicineSubAmt_saleMedicine.setText(medicineModel.getMedicineSubAmt());
            }
            saleView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleMedicineDetailInformationActivity.class);
                intent.putExtra("MedicineModel", medicineModel);
                context.startActivity(intent);
            });
        }
    }
}
