package com.hiphopgam.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hiphopgam.MainActivity;
import com.hiphopgam.R;
import com.hiphopgam.model.IResposeListener;
import com.hiphopgam.service.AppSignatureHelper;
import com.hiphopgam.ui.verification.VerificationActivity;
import com.hiphopgam.utils.BaseActivity;
import com.hiphopgam.utils.Navigation;
import com.hiphopgam.webservice.WebservieCaller;

import java.util.ArrayList;

// hash code : kF9eLfUbV7t
public class Login extends BaseActivity{
    @BindView(R.id.edt_username)
    AppCompatEditText username;

    @BindView(R.id.edt_password)
    AppCompatEditText password;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    WebservieCaller webservieCaller;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmsRetrieverClient client = SmsRetriever.getClient(this /* context */);
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

//        AppSignatureHelper appSigniture = new AppSignatureHelper(this);
//        ArrayList<String> HashCode =appSigniture.getAppSignatures();
//        for(String s: HashCode){
//            Log.d("---------------HashCode-----------------",s);
//        }
        webservieCaller = new WebservieCaller();

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("login", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.d("------------------token---------------", token);
                    }
                });
    }

//    private static final int SMS_CONSENT_REQUEST = 2;  // Set to an unused request code
//    private final BroadcastReceiver smsVerificationReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
//                Bundle extras = intent.getExtras();
//                Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
//
//                switch (smsRetrieverStatus.getStatusCode()) {
//                    case CommonStatusCodes.SUCCESS:
//                        // Get consent intent
//                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
//                        try {
//                            // Start activity to show consent dialog to user, activity must be started in
//                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
//                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
//                        } catch (ActivityNotFoundException e) {
//                            // Handle the exception ...
//                        }
//                        break;
//                    case CommonStatusCodes.TIMEOUT:
//                        // Time out occurred, handle the error.
//                        break;
//                }
//            }
//        }
//    };

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            // ...
//            case SMS_CONSENT_REQUEST:
//                if (resultCode == RESULT_OK) {
//                    // Get SMS message content
//                    String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
//                    // Extract one-time code from the message and complete verification
//                    // `sms` contains the entire text of the SMS message, so you will need
//                    // to parse the string.
//                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//                    Log.d("ddd message", message);
////                    String oneTimeCode = parseOneTimeCode(message); // define this function
//
//                    // send one time code to the server
//                } else {
//                    // Consent canceled, handle the error ...
//                }
//                break;
//        }
//    }

    @OnClick(R.id.btn_login)
    public void login(View view) {

        progressBar.setVisibility(View.VISIBLE);
        webservieCaller.login(username.getText().toString(), password.getText().toString(), new IResposeListener() {
            @Override
            public void onResponse(Object response) {
                progressBar.setVisibility(View.GONE);
                Navigation.navigate(Login.this, VerificationActivity.class);
                finish();
            }

            @Override
            public void onFailure(String errorResponse) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), errorResponse, Toast.LENGTH_LONG).show();
            }
        });

//        firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(Task<AuthResult> task) {
//                        Log.d("","");
//                        if(task.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"auth success!!!!", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(getApplicationContext(),"auth fail!!!!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_login;
    }
}
