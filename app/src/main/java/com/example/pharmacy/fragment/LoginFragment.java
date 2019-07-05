package com.example.pharmacy.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pharmacy.adapter.LoginAdapter;
import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.yy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "LoginFragment";
    private LoginAdapter loginAdapter;

    @BindView(R.id.swipeLogin)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvLogin)
    RecyclerView rvLogin;

    private Realm realm;
    private LinearLayoutManager linearLayoutManager;
    private Context context;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this,view);
        context = view.getContext();
        init();

        return view;
    }

    private void init() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        realm = Realm.getDefaultInstance();
        loginAdapter = new LoginAdapter();
        linearLayoutManager = new LinearLayoutManager(context);
        rvLogin.setLayoutManager(linearLayoutManager);
        rvLogin.setAdapter(loginAdapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        loginAdapter.clear();

    }
}
