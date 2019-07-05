package com.example.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

//    @BindView(R.id.imgLogo)
//    ImageView imgLogo;
//
//    @BindView(R.id.tvLogoName)
//    TextView tvLogoName;

    private Realm realm;

    @BindView(R.id.edtLoginUserName)
    EditText edtLoginUserName;

    @BindView(R.id.edtLoginPin)
    EditText edtLoginPin;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.btnExit)
    Button btnExit;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(edtLoginUserName.getText().toString(), edtLoginPin.getText().toString());
            }
        });

        btnExit.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }

    private void validate(String userName,String password){
        {
            if ( userName.equals(edtLoginUserName) && password.equals(edtLoginPin )  ){
                Toast.makeText(LoginActivity.this, "Your Pin Number is Correct and Your Name is "+userName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                //finish();
            }
            else {
                counter--;
                if(counter == 0){
                    btnLogin.setEnabled(false);
                }
                Toast.makeText(LoginActivity.this, "No of attempts reamining : "+String.valueOf(counter), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
