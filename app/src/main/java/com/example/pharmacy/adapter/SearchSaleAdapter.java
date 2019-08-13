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
import com.example.pharmacy.model.SaleModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchSaleAdapter extends RecyclerView.Adapter<SearchSaleAdapter.SearchSaleViewHolder> {

    private static final String TAG = "SearchSaleAdapter";
    private List<SaleModel> saleModelList = new ArrayList<>();

    public void clear(){saleModelList.clear();}

    public List<SaleModel> getSaleModelList(){return  saleModelList;}

    @NonNull
    @Override
    public SearchSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_sale_search, viewGroup, false);
        return new SearchSaleAdapter.SearchSaleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchSaleViewHolder searchSaleViewHolder, int i) {
        searchSaleViewHolder.bind(saleModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return saleModelList.size();
    }

    public class SearchSaleViewHolder extends RecyclerView.ViewHolder {

        public View cView;

        @BindView(R.id.saleCustomerName_itemSearch)
        TextView tvName;

        private Context context;

        public SearchSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            cView = itemView;



        }

        public void bind(SaleModel saleModel) {
            tvName.setText(saleModel.getSaleCustomerName());

            cView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SaleMedicineDetailInformationActivity.class);
                intent.putExtra("SaleModel", saleModel);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                //((Activity)context).finish();
            });
        }
    }
}
