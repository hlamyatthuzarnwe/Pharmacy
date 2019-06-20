package com.example.yy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yy.MedicineDetailActivity;
import com.example.yy.R;
import com.example.yy.SaleReportDetailActivity;
import com.example.yy.model.SaleReportModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleReportAdapter extends RecyclerView.Adapter<SaleReportAdapter.SaleReportViewHolder> {

    private static final String TAG = "SaleReportAdapter";
    private List<SaleReportModel> saleReportModelList = new ArrayList<>();

    public void clear(){saleReportModelList.clear();}
    public List<SaleReportModel> getSaleReportModelList(){return saleReportModelList;}

    @NonNull
    @Override
    public SaleReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale_report,viewGroup,false);
        return new SaleReportAdapter.SaleReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleReportViewHolder saleReportViewHolder, int position) {

        saleReportViewHolder.bind(saleReportModelList.get(position));

    }

    @Override
    public int getItemCount() {

        return saleReportModelList.size();
    }

    public class SaleReportViewHolder extends RecyclerView.ViewHolder {

        private View saleReportView;
        private Context context;

        @BindView(R.id.tvSaleReportSaleInvoiceNo)
        TextView tvSaleReportSaleInvoiceNo;

        @BindView(R.id.tvSaleReportSaleInvoiceDate)
        TextView tvSaleReportSaleInvoiceDate;

        @BindView(R.id.tvSaleReportCustmerName)
        TextView tvSaleReportCustmerName;

        public SaleReportViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            saleReportView = itemView;
        }

        public void bind(SaleReportModel saleReportModel) {

            if(saleReportModel.getSaleReportSaleIInvoiceDate() != null){
                tvSaleReportSaleInvoiceDate.setText(saleReportModel.getSaleReportSaleIInvoiceDate());
            }
            if(saleReportModel.getSaleReportCustomerName() != null){
                tvSaleReportCustmerName.setText(saleReportModel.getSaleReportCustomerName());
            }

            saleReportView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleReportDetailActivity.class);
                intent.putExtra("SaleReportModel",saleReportModel);
                context.startActivity(intent);
            });
        }
    }
}
