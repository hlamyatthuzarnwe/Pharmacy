package com.example.yy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yy.R;
import com.example.yy.model.SaleReportModel;

import java.util.ArrayList;
import java.util.List;

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

        public SaleReportViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(SaleReportModel saleReportModel) {
        }
    }
}
