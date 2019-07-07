package com.hiphopgam.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.hiphopgam.R;

public class Login extends AppCompatActivity {
    @BindView(R.id.edt_username)
    AppCompatEditText username;

    @BindView(R.id.edt_password)
    AppCompatEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
