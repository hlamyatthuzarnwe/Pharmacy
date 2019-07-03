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
import com.example.pharmacy.SaleMedicineDetailInformationActivity;
import com.example.pharmacy.SaleMedicineInformationActivity;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleMedicineInformationAdapter extends RecyclerView.Adapter<SaleMedicineInformationAdapter.SaleMedicineInformationViewHolder> {

    private static final String TAG = "SaleMedicineInformationAdapter";
    private List<SaleModel> saleModelList = new ArrayList<>();

    public void clear(){
        saleModelList.clear();
    }

    public List<SaleModel> getSaleModelList() {
        return saleModelList;
    }

    @NonNull
    @Override
    public SaleMedicineInformationAdapter.SaleMedicineInformationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale_medicine_information,viewGroup,false);
        return new SaleMedicineInformationAdapter.SaleMedicineInformationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleMedicineInformationAdapter.SaleMedicineInformationViewHolder saleMedicineInformationViewHolder, int position) {

        saleMedicineInformationViewHolder.bind(saleModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class SaleMedicineInformationViewHolder extends RecyclerView.ViewHolder {

        public View saleView;
        private Context context;

        @BindView(R.id.tvMedicineName_saleInfo)
        TextView tvMedicineName_saleInfo;

        @BindView(R.id.tvMedicineCategory)
        TextView tvMedicineCategory;

        public SaleMedicineInformationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            saleView = itemView;
        }


        public void bind(SaleModel saleModel) {

            if (saleModel.getSaleMedicineName() != null){
                tvMedicineName_saleInfo.setText(saleModel.getSaleMedicineName());
            }
            if (saleModel.getSaleCategory() != null){
                tvMedicineCategory.setText(saleModel.getSaleCategory());
            }
            saleView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleMedicineInformationActivity.class);
                intent.putExtra("SaleModel",saleModel);
                context.startActivity(intent);
            });
        }
    }
}
