package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.SaleDetailActivity;
import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleViewHolder> {

    private static final String TAG = "SaleAdapter";
    private List<SaleModel> saleModelList = new ArrayList<>();


    public void clear() {
        saleModelList.clear();
    }

    public List<SaleModel> getSaleModelList() {
        return saleModelList;
    }

    @NonNull
    @Override
    public SaleAdapter.SaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale, viewGroup, false);
        return new SaleAdapter.SaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.SaleViewHolder saleViewHolder, int position) {

        saleViewHolder.bind(saleModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return saleModelList.size();
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder {

        public View saleView;
        private Context context;

        @BindView(R.id.tvSaleInvoiceDate)
        TextView tvSaleInvoiceDate;

        @BindView(R.id.tvMedicineName)
        TextView tvMedicineName;

        @BindView(R.id.tvSaleCustomerName)
        TextView tvSaleCustomerName;

        @BindView(R.id.tvSaleTotalAmt)
        TextView tvSaleTotalAmt;
        private ArrayList<MedicineModel> medicineModels = new ArrayList<>();
        private Realm realm;

        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            saleView = itemView;
            realm = Realm.getDefaultInstance();
        }

        public void bind(SaleModel saleModel) {


           // Log.d(TAG, "bind: "+customerModel.getCustomerName());

            if (saleModel.getSaleInvoiceDate() != null) {
                tvSaleInvoiceDate.setText(saleModel.getSaleInvoiceNo());
            }
            if (saleModel.getSaleCustomerName() != null) {
                tvSaleCustomerName.setText(saleModel.getSaleCustomerName());
            }

            if (saleModel.getSaleTotalAmt() != null) {
                tvSaleTotalAmt.setText(saleModel.getSaleTotalAmt());
            }

                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i < saleModel.getMedicineLists().size(); i++) {
                    medicineModels.add(saleModel.getMedicineLists().get(i));
                    arrayList.add(saleModel.getMedicineLists().get(i).getMedicineName());
                    Log.d(TAG, "bind: name : " + saleModel.getMedicineLists().get(i));

                }
                // Log.d(TAG, "bind: medicine name : "+medicines);
                String medicine = TextUtils.join(", ", arrayList);
                tvMedicineName.setText(medicine);
               /*
                Log.d(TAG, "bind: test : "+medicine);
                Log.d(TAG, "bind: origin size : "+saleModel.getCustomerModel().getMedicineLists().size());
                Log.d(TAG, "bind: size : "+medicineModels.size());
                */



            saleView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleDetailActivity.class);
                intent.putExtra("SaleModel", saleModel);
                intent.putExtra("medicineModel", medicineModels);
                context.startActivity(intent);
            });

        }
    }
}
