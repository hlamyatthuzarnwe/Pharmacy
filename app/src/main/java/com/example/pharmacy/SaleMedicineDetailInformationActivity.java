package com.example.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleMedicineDetailInformationActivity extends AppCompatActivity {
    @BindView(R.id.relativeSave_medicineAdd)
    RelativeLayout relativeSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_medicine_detail_information);
        ButterKnife.bind(this);

        relativeSave.setOnClickListener(view -> {

        });
    }
}
