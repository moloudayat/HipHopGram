package com.hiphopgam.ui.verification;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.OnClick;

import com.hiphopgam.MainActivity;
import com.hiphopgam.R;
import com.hiphopgam.utils.BaseActivity;
import com.hiphopgam.utils.Navigation;


public class VerificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_verification;
    }

    @OnClick(R.id.btn_verificate)
    public void verificate(){
        // TODO: add sms check
        Navigation.navigate(VerificationActivity.this, MainActivity.class);
        finish();
    }
}
