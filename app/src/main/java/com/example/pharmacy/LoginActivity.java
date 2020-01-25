package com.example.pharmacy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmacy.helper.MyanProgressDialog;
import com.example.pharmacy.helper.SharepreferenceHelper;
import com.example.yy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private static final int RC_SIGN_IN = 123;
    String phone;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @BindView(R.id.btnLogin)
    Button btnConfirm;
    @BindView(R.id.edtLoginUserName)
    EditText edtLoginUserName;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;

//    @BindView(R.id.imgLogo)
//    ImageView imgLogo;
//
//    @BindView(R.id.tvLogoName)
//    TextView tvLogoName;
    private SharepreferenceHelper share;
    private Realm realm;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private MyanProgressDialog dialog;
    String phoneNumber1 = "+95786091601";
    String phoneNumber2 = "+9509786091601";
    String smsCode = "987654";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        dialog = new MyanProgressDialog(this);
        realm = Realm.getDefaultInstance();
        share = SharepreferenceHelper.getHelper(this);
        dialog.hideDialog();


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                dialog.hideDialog();
              /*
                Log.d(TAG, "onVerificationCompleted: " + phoneAuthCredential);

                Log.d(TAG, "onVerificationCompleted: current user : "+auth.getCurrentUser().getPhoneNumber());
                Log.d(TAG, "onVerificationCompleted: current uid : "+auth.getCurrentUser().getUid());
               */
                signInWithPhoneAuthCredential(phoneAuthCredential);


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Log.w(TAG, "onVerificationFailed", e);
                dialog.hideDialog();
                showAlert();

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);
                /*
                Log.d(TAG, "onCodeSent: id : " + verificationId);
                Log.d(TAG, "onCodeSent: token : " + forceResendingToken);
                 */

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = forceResendingToken;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                dialog.hideDialog();
                showAlert();
                // Log.d(TAG, "onCodeAutoRetrievalTimeOut: " + s);
            }
        };

        btnConfirm.setOnClickListener(view -> {
            // Log.d(TAG, "onCreate: btnConfirm : "+edtLoginUserName.getText().toString());
            if (TextUtils.isEmpty(edtLoginUserName.getText())){
                Toast.makeText(LoginActivity.this, "Phone Number is required", Toast.LENGTH_SHORT).show();
            }else {
                String ph = "+95" + edtLoginUserName.getText().toString();
                if (ph.trim().equals(phoneNumber1.trim())) {
                    retrievalCode(phoneNumber1);
                } else if (ph.trim().equals(phoneNumber2.trim())) {
                    retrievalCode(phoneNumber2);
                }
                signIn();
            }

        });
    }

    private void retrievalCode(String phNum) {
        //  Log.d(TAG, "retrievalCode: phnum : "+phNum);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phNum, smsCode);
    }

    private void signIn() {
        phone = "+95" + edtLoginUserName.getText().toString().trim();
        //  Log.d(TAG, "onClick: var phone : " + phone);
        if (!TextUtils.isEmpty(phone)) {
            dialog.showDialog();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone,
                    60,
                    TimeUnit.SECONDS,
                    LoginActivity.this,
                    mCallbacks
            );
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

    @Override
    protected void onResume() {
        super.onResume();
        dialog.hideDialog();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.hideDialog();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //  Log.d(TAG, "signInWithCredential:success");
                            share.setLogIn(true);

                            FirebaseUser user = task.getResult().getUser();

                           /*
                            Log.d(TAG, "onComplete: phone number : " + user.getPhoneNumber());
                            Log.d(TAG, "onComplete: display name : " + user.getDisplayName());
                            Log.d(TAG, "onComplete: user id : " + user.getUid());
                            */
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            share.setLogIn(false);
                            showAlert();
                            //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void showAlert() {
        dialog.hideDialog();
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Internet Connection Error")
                .setMessage("Please Try Again")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        (dialog, whichButton) -> signIn())
                .setNegativeButton("No",
                        (dialogInterface, i) -> {
                            dialog.hideDialog();
                            dialogInterface.dismiss();
                        }).show();
    }


}
