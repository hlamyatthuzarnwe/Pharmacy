package com.example.pharmacy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class SearchViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    List<MedicineModel> medicineModelList;
    ArrayList<MedicineModel> medicineModelArrayList;

    public SearchViewAdapter(Context con,List<MedicineModel> medicine){
        context = con;
        medicineModelList =  medicine;
        this.layoutInflater = LayoutInflater.from(context);
        this.medicineModelArrayList = new ArrayList<MedicineModel>();
        this.medicineModelArrayList.addAll(medicine);
    }

    public class ViewHolder{
        TextView textView;

    }

    @Override
    public int getCount() {
        return medicineModelList.size();
    }

    @Override
    public MedicineModel getItem(int position) {
        return medicineModelList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder = new ViewHolder();

        if (view ==  null){
          //  view = layoutInflater.inflate(R.layout.menu)
        }

        return null;
    }
}
