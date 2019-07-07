package com.hiphopgam.model;

public interface IResposeListener<T> {
    public void onResponse(T response);
    public void onFailure(String errorResponse);

}
