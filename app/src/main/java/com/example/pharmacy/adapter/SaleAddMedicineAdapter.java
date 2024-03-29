package com.example.pharmacy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pharmacy.helper.AmountCallback;
import com.example.pharmacy.helper.MedicineModelList;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SingleQty;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class SaleAddMedicineAdapter extends RecyclerView.Adapter<SaleAddMedicineAdapter.SaleAddViewHolder> {

    private static final String TAG = "SaleAddMedicineAdapter";
    private List<MedicineModel> saleModelList = new ArrayList<>();
    private AmountCallback amountCallback;
    private String sale_medicine;

    public SaleAddMedicineAdapter(String sale_medicine) {
        this.sale_medicine = sale_medicine;
    }

    public void registerCallback(AmountCallback callbackClass) {
        amountCallback = callbackClass;
    }

    public void clear() {
        saleModelList.clear();
    }

    public List<MedicineModel> getSaleModelList() {
        return saleModelList;
    }

    @NonNull
    @Override
    public SaleAddMedicineAdapter.SaleAddViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale_add, viewGroup, false);
        return new SaleAddMedicineAdapter.SaleAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAddMedicineAdapter.SaleAddViewHolder saleAddViewHolder, int position) {

        saleAddViewHolder.bind(saleModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return saleModelList.size();
    }

    public class SaleAddViewHolder extends RecyclerView.ViewHolder {

        public View saleAddView;
        @BindView(R.id.ivDelete_itemMedicineAdd)
        ImageView ivDelete;
        @BindView(R.id.tvSaleMedicineName)
        TextView tvSaleMedicineName;
        @BindView(R.id.tvSaleQtyPerPc)
        TextView tvSaleQtyPerPc;
        @BindView(R.id.tvSaleSubAmtPerPc)
        TextView tvSaleSubAmtPerPc;
        String qty = "";
        private Realm realm;
        private RealmList<MedicineModel> medicineList;
        private Context context;
        private ArrayList<String> luck;


        public SaleAddViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            saleAddView = itemView;
            realm = Realm.getDefaultInstance();
            medicineList = MedicineModelList.getInstance().getMedicineModelRealmList();
            luck = SingleQty.getQty();

        }

        public void bind(MedicineModel medicineModel) {

            HashMap<String, String> hashMap = new HashMap<>();
            String[] parts;



            if (sale_medicine != null) {
                for (String comma : TextUtils.split(sale_medicine, ",")) {
                    parts = comma.split(":", 2);
                    hashMap.put(parts[0], parts[1]);

                }
                if (medicineModel.getMedicineName() != null) {
                    for (String ss : hashMap.keySet()) {
                        if (ss.toLowerCase().trim().equals(medicineModel.getMedicineName().toLowerCase().trim())) {
                            qty = hashMap.get(ss);
                            tvSaleQtyPerPc.setText(hashMap.get(ss));
                        }
                    }

                }
            }

            if (medicineModel.getMedicineName() != null) {
                tvSaleMedicineName.setText(medicineModel.getMedicineName());
            }

            if (medicineModel.getMedicineSubAmt() != null) {
                tvSaleSubAmtPerPc.setText(medicineModel.getMedicineSubAmt());
            }


            ivDelete.setOnClickListener(view -> {
                medicineList.remove(medicineModel);
                hashMap.remove(medicineModel.getMedicineName());
                String k = medicineModel.getMedicineName() + ":" + qty;

                luck.remove(k);


                int amount = 0;
                for (int i = 0; i < medicineList.size(); i++) {
                    if (medicineList.get(i) != null) {
                        amount += Integer.parseInt(medicineList.get(i).getMedicineSubAmt());
                    }

                }
                amountCallback.onTotalAmount(String.valueOf(amount));

                clear();
                getSaleModelList().addAll(medicineList);
                notifyDataSetChanged();

                Log.d(TAG, "bind: after delete : " + medicineList.size());
            });

        }

    }
}
