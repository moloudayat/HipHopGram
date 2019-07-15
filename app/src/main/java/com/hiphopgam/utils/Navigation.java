package com.hiphopgam.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Navigation {
    private static Intent intent;

    public Navigation(AppCompatActivity source, Class<?> destination) {
        intent = new Intent(source, destination);
        source.startActivity(intent);
    }

    public static void navigate(AppCompatActivity source, Class<?> destination){
        intent = new Intent(source, destination);
        source.startActivity(intent);
    }
}
