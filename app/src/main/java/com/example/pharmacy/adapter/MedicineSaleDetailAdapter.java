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

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicineSaleDetailAdapter extends RecyclerView.Adapter<MedicineSaleDetailAdapter.MedicineSaleDetailViewHolder> {

    private static final String TAG = MedicineSaleDetailAdapter.class.getSimpleName();
    private List<MedicineModel> medicineModelList = new ArrayList<>();
    private String sale_qty;

    public MedicineSaleDetailAdapter(String sale_qty) {
        this.sale_qty = sale_qty;
    }

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
        @BindView(R.id.tvSaleMedicineName_detail)
        TextView tvMedicineName;
        @BindView(R.id.tvSupplierName_detail)
        TextView tvSupplierName;
        @BindView(R.id.tvSaleTotalAmt_detail)
        TextView tvAmount;
        @BindView(R.id.tvSaleQuantityPerPc_detail)
        TextView tvQty;
        private Context context;


        public MedicineSaleDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            mView = itemView;
        }

        public void bind(MedicineModel medicineModel) {
            Log.d(TAG, "bind: ");
            HashMap<String, String> hashMap = new HashMap<>();
            String[] parts;
            String qty = "";
            try {
                if (sale_qty != null){
                    for (String comma : TextUtils.split(sale_qty, ",")) {
                        parts = comma.split(":", 2);
                        hashMap.put(parts[0], parts[1]);

                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){
                Log.d(TAG, "bind: "+e.getLocalizedMessage());
            }





            if (medicineModel.getMedicineName() != null) {
                tvMedicineName.setText(medicineModel.getMedicineName());
            }
            if (medicineModel.getMedicineName() != null && sale_qty != null) {
                for (String ss : hashMap.keySet()) {
                    if (ss.toLowerCase().trim().equals(medicineModel.getMedicineName().toLowerCase().trim())) {
                        qty = hashMap.get(ss);
                        tvQty.setText(hashMap.get(ss));
                    }
                }

            }
            if (medicineModel.getSupplierModel() != null) {
                tvSupplierName.setText(medicineModel.getSupplierModel().getSupplierName());
            }


            if (!TextUtils.isEmpty(qty) &&
                    medicineModel.getMedicineSalePcPerPrice1() != null) {
                int price = Integer.parseInt(medicineModel.getMedicineSalePcPerPrice1());

                int amount = 0;
                if (qty != null) {
                    amount = price * Integer.parseInt(qty);
                }
                tvAmount.setText(String.valueOf(amount));
            }

        }
    }
}
