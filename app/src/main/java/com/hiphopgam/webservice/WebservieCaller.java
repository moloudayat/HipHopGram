package com.hiphopgam.webservice;

import android.util.Log;

import com.hiphopgam.model.IResposeListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebservieCaller {
    IService iService;

    public WebservieCaller() {
        iService = ApiClient.getRetrofit().create(IService.class);
    }

    public void login(String username, String password, IResposeListener iResposeListener) {
        Call<ResponseBody> login = iService.login(username, password);

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("","");
                try {
                    iResposeListener.onResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("","");
                iResposeListener.onFailure(t.getMessage().toString());
            }
        });
    }
}
