package com.example.pharmacy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.pharmacy.fragment.CustomerFragment;
import com.example.pharmacy.fragment.MedicineFragment;
import com.example.pharmacy.helper.SharepreferenceHelper;
import com.example.yy.R;
import com.example.pharmacy.fragment.SaleFragment;
import com.example.pharmacy.fragment.SupplierFragment;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.yy.R.string.navigation_drawer_close;
import static com.example.yy.R.string.navigation_drawer_open;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    Fragment fragment = null;
    Class fragmentClass;
    private static int LOCATE_ACTIVITY = 0;
    @BindView(R.id.fabAddMedicine)
    FloatingActionButton fabAdd;
    SharepreferenceHelper share;

    private static final String TAG = "MainActivity";
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        share = SharepreferenceHelper.getHelper(this);
        handler = new Handler();

      /*  PackageInfo info;
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
        }*/


        mDrawer = (DrawerLayout) findViewById(R.id.drawerMain);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, navigation_drawer_open, navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        nvDrawer.setNavigationItemSelectedListener(this);
        LOCATE_ACTIVITY = 0;
        MenuItem menuItem = nvDrawer.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        // fragmentClass = MedicineFragment.class;
      /*  try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/

        fabAdd.setOnClickListener(v -> {
            switch (LOCATE_ACTIVITY) {

                case 1:
                    Intent intent1 = new Intent(MainActivity.this, MedicineAddActivity.class);
                    startActivity(intent1);
                    break;

               /*
                case 2:
                    Intent intent2 = new Intent(MainActivity.this, CustomerAddActivity.class);
                    startActivity(intent2);
                    break;

                case 3:
                    Intent intent3 = new Intent(MainActivity.this, SupplierAddActivity.class);
                    startActivity(intent3);
                    break;
                */

                case 4:
                    Intent intent4 = new Intent(MainActivity.this, SaleAddActivity.class);
                    startActivity(intent4);
                    break;


              /*
                default:
                    Intent intent = new Intent(MainActivity.this,MedicineAddActivity.class);
                    startActivity(intent);

               */
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
        switch (menuItem.getItemId()) {
            case R.id.drawer_view_product:
                fragmentClass = MedicineFragment.class;
                LOCATE_ACTIVITY = 1;
                fabAdd.show();
                break;
            case R.id.drawer_view_customer:
                fragmentClass = CustomerFragment.class;
                LOCATE_ACTIVITY = 2;
                fabAdd.hide();
                break;
            case R.id.drawer_view_supplier:
                fragmentClass = SupplierFragment.class;
                LOCATE_ACTIVITY = 3;
                fabAdd.hide();
                break;
            case R.id.drawer_view_sale:
                fragmentClass = SaleFragment.class;
                LOCATE_ACTIVITY = 4;
                fabAdd.show();
                break;
            case R.id.drawer_view_logOut:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                (dialog, whichButton) -> {
                                    FirebaseAuth.getInstance().signOut();
                                    share.setLogIn(false);
                                    Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());
                                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                })
                        .setNegativeButton("No",
                                (dialog, whichButton) -> dialog.dismiss()).show();
                break;
            default:
                LOCATE_ACTIVITY = 1;
                fragmentClass = MedicineFragment.class;
                break;
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
