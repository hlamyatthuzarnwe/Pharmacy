package com.example.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pharmacy.MainActivity;
import com.example.pharmacy.model.LoginModel;
import com.example.yy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginViewHolder> {

    private static final String TAG = "LoginAdapter";
    private List<LoginModel> loginModelList = new ArrayList<>();

    public void clear(){loginModelList.clear();}

    public List<LoginModel> getLoginModelList(){return loginModelList;}

    @NonNull
    @Override
    public LoginViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.activity_login,viewGroup,false);
        return new LoginAdapter.LoginViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginViewHolder loginViewHolder, int position) {

        loginViewHolder.bind(loginModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return loginModelList.size();
    }

    public class LoginViewHolder extends RecyclerView.ViewHolder {

        public View loginView;
        private Context context;
        private LoginModel loginModel;



        @BindView(R.id.edtLoginUserName)
        EditText edtLoginUserName;

        public LoginViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            loginView = itemView;
        }

        public void bind(LoginModel loginModel) {
            if (loginModel.getUserName() != null){
                edtLoginUserName.setText(loginModel.getUserName());
            }

            loginView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("LoginModel",loginModel);
                context.startActivity(intent);
            });
        }
    }
}
