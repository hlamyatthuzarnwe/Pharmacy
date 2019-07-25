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

import com.example.pharmacy.SaleAddActivity;
import com.example.pharmacy.SaleDetailActivity;
import com.example.pharmacy.model.MedicineModel;
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleAddMedicineAdapter extends RecyclerView.Adapter<SaleAddMedicineAdapter.SaleAddViewHolder> {

    private static final String TAG = "SaleAddMedicineAdapter";
    private List<MedicineModel> saleModelList = new ArrayList<>();

    public void clear(){
        saleModelList.clear();
    }

    public List<MedicineModel> getSaleModelList() {
        return saleModelList;
    }

    @NonNull
    @Override
    public SaleAddMedicineAdapter.SaleAddViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale_add,viewGroup,false);
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
        private Context context;

        @BindView(R.id.tvSaleMedicineName)
        TextView tvSaleMedicineName;

        @BindView(R.id.tvSalePricePerPc)
        TextView tvSalePricePerPc;

        @BindView(R.id.tvSaleQtyPerPc)
        TextView tvSaleQtyPerPc;

        @BindView(R.id.tvSaleSubAmtPerPc)
        TextView tvSaleSubAmtPerPc;


        public SaleAddViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            saleAddView = itemView;
        }
        public void bind(MedicineModel saleModel){
            Log.d(TAG, "bind: name : "+saleModel.getMedicineName());
            if (saleModel.getMedicineName() != null){
                tvSaleMedicineName.setText(saleModel.getMedicineName());
            }
            if (saleModel.getMedicineCategory() != null){
                tvSalePricePerPc.setText(saleModel.getMedicineCategory());
            }
            if (saleModel.getMedicineSubAmt() != null){
                tvSaleQtyPerPc.setText(saleModel.getMedicineSubAmt());
            }
          /*  if (saleModel.getSaleSubTotalAmt() != null){
                tvSaleSubAmtPerPc.setText(saleModel.getSaleSubTotalAmt());
            }*/

            saleAddView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleAddActivity.class);
                intent.putExtra("SaleModel",saleModel);
                context.startActivity(intent);
            });
        }

    }
}
