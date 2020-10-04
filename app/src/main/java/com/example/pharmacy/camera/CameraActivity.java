package com.example.pharmacy.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yy.R;
import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    private static final char UNIX_SEPARATOR = '/';
    private ImageView ivPhoto;
    private Button btnPhoto;
    public static final int IMAGE_REQUEST_CODE = 732;
    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ivPhoto = findViewById(R.id.ivTestPhoto);
        btnPhoto = findViewById(R.id.btnTestPhoto);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()){
                    takePictureIntent();
                /*    Matisse.from(CameraActivity.this)
                            .choose(MimeType.ofAll())
                            .countable(true)
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider","test"))
                            .imageEngine(new Glide4Engine())
                            .maxSelectable(9)
                            .forResult(11);
*/
                }
            }
        });
    }
    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, IMAGE_REQUEST_CODE);
        }
    }
    public String BitMapToString(Bitmap saveImageBitmap){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            saveImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            String imageFileName = "IMG_" + timeStamp + ".jpg";

            File file = new File(getStorageDir(this), imageFileName);
            if (file.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baos.toByteArray());
                fos.close();
                return file.getAbsolutePath();
//                saveResult = new ImageCropperSaveResult(Uri.fromFile(file), file.getAbsolutePath());
            } else {
                Log.d(TAG, "New File created fail!");
            }
        } catch (IOException ioe) {
            Log.e(TAG, "Error Saving File", ioe);
        }
        return "error";
    }
    public static File getStorageDir(Context context) {
        String rootStoragePath = context.getFilesDir().getAbsolutePath() + UNIX_SEPARATOR + "TEST";
        File rootStorage = new File(rootStoragePath);
        if (!rootStorage.exists()) {
            if (rootStorage.mkdir()) {
                Log.d(TAG, "getStorageDir: Storage Created at " + rootStorage.getAbsolutePath());
            }
        }

        return rootStorage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.

                } else {
//                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
//                            .show();
                }
                // permissions list of don't granted permission
            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE ){
            Bundle extras = data.getExtras();
            Log.d(TAG, "onActivityResult: data : "+data.getData());
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String ss = BitMapToString(imageBitmap);
            Log.d(TAG, "onActivityResult: photo string : "+ss);
            File ff = new File(ss);
            Picasso.with(this).load(ff)
                    .error(android.R.drawable.btn_star_big_on)
                    .into(ivPhoto);

        }else  if (requestCode == 11 && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult: gallery");
            Log.d(TAG, "onActivityResult: data : "+data);
            Log.d(TAG, "onActivityResult: extra : "+data.getExtras());
            Log.d(TAG, "onActivityResult: DATA : "+data.getData());
            List mSelected = Matisse.obtainPathResult(data);
            String path = mSelected.get(0).toString();
            Log.d(TAG, "mSelected: " + mSelected);
            Log.d(TAG, "onActivityResult: path : "+path);

            File ff = new File(path);
            Picasso.with(this).load(ff)
                    .error(android.R.drawable.btn_star_big_on)
                    .into(ivPhoto);

        }

    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(CameraActivity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }
}
