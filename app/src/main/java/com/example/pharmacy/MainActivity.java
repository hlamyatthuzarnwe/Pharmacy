package com.example.pharmacy;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.pharmacy.adapter.MedicineAdapter;
import com.example.pharmacy.fragment.CustomerFragment;
import com.example.pharmacy.fragment.InventoryFragment;
import com.example.pharmacy.fragment.MedicineFragment;
import com.example.pharmacy.fragment.SaleReportFragment;
import com.example.pharmacy.fragment.UserManageFragment;
import com.example.pharmacy.model.MedicineModel;
import com.example.yy.R;
import com.example.pharmacy.fragment.SaleFragment;
import com.example.pharmacy.fragment.SupplierFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.example.yy.R.string.navigation_drawer_close;
import static com.example.yy.R.string.navigation_drawer_open;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    Fragment fragment = null;
    Class fragmentClass;
    private int LOCATE_ACTIVITY;
    @BindView(R.id.fabAddMedicine)
    FloatingActionButton fabAdd;

    private static final String TAG = "MainActivity";
    private MedicineAdapter medicineAdapter;
    private LinearLayoutManager linearLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            Log.d(TAG, "onCreate: PN" + getPackageName());
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

        RealmList<MedicineModel> medicineModelRealmList = new RealmList<>();

        mDrawer = (DrawerLayout) findViewById(R.id.drawerMain);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawer,toolbar, navigation_drawer_open, navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        nvDrawer.setNavigationItemSelectedListener(this);
        fragmentClass = MedicineFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        fabAdd.setOnClickListener(v -> {
            switch (LOCATE_ACTIVITY){
                case 1:
                    Intent intent1 = new Intent(MainActivity.this,MedicineAddActivity.class);
                startActivity(intent1);
                break;

                case 2:
                    Intent intent2 = new Intent(MainActivity.this,CustomerAddActivity.class);
                startActivity(intent2);
                break;

                case 3:
                    Intent intent3 = new Intent(MainActivity.this,SupplierAddActivity.class);
                startActivity(intent3);
                break;

                default:
                    Intent intent = new Intent(MainActivity.this,MedicineAddActivity.class);
                startActivity(intent);
            }

        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        selectDrawerItem(menuItem);
        return true;
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.drawer_view_product :
                fragmentClass = MedicineFragment.class;
                LOCATE_ACTIVITY = 1;
                break;
            case R.id.drawer_view_customer:
                fragmentClass = CustomerFragment.class;
                LOCATE_ACTIVITY = 2;
                break;
            case R.id.drawer_view_supplier:
                fragmentClass = SupplierFragment.class;
                LOCATE_ACTIVITY = 3;
                break;
            case R.id.drawer_view_sale_report:
                fragmentClass = SaleReportFragment.class;
                LOCATE_ACTIVITY = 4;
                break;
            case R.id.drawer_view_inventory:
                fragmentClass = InventoryFragment.class;
                LOCATE_ACTIVITY = 5;
                break;
            case R.id.drawer_view_user_manage:
                fragmentClass = UserManageFragment.class;
                LOCATE_ACTIVITY = 6;
                break;
            case R.id.drawer_view_sale:
                fragmentClass = SaleFragment.class;
                LOCATE_ACTIVITY = 7;
                break;
            default:
                fragmentClass = MedicineFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();

    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
