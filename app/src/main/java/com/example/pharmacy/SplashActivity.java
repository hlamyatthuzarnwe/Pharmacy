package com.example.pharmacy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pharmacy.helper.SharepreferenceHelper;
import com.example.yy.R;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private SharepreferenceHelper share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // getSupportActionBar().hide();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        share = SharepreferenceHelper.getHelper(this);

        new Handler().postDelayed(this::initProcess,1500);
    }

    private void initProcess() {
        if (share.isLogIn()){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
