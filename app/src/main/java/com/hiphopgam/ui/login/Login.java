package com.hiphopgam.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hiphopgam.MainActivity;
import com.hiphopgam.R;
import com.hiphopgam.model.IResposeListener;
import com.hiphopgam.ui.verification.VerificationActivity;
import com.hiphopgam.utils.BaseActivity;
import com.hiphopgam.utils.Navigation;
import com.hiphopgam.webservice.WebservieCaller;

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
