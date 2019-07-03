package com.example.pharmacy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacy.model.CustomerModel;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMedicineAdapter  extends RecyclerView.Adapter<SearchMedicineAdapter.SearchViewHolder>  {

    private static final String TAG="SearchMedicineAdapter";
    private List<MedicineModel> medicineModelList = new ArrayList<>();

    public void clear(){
        medicineModelList.clear();
    }

    public List<MedicineModel>getMedicineModelList(){
        return medicineModelList;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_medicine_search,viewGroup,false);
        return new SearchMedicineAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.bind(medicineModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return medicineModelList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public View cView;
        @BindView(R.id.medicineName_itemSearch)
        TextView tvName;
        private Context context;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            cView = itemView;
        }
        public void bind(MedicineModel model){
            tvName.setText(model.getMedicineName());
        }
    }
}
