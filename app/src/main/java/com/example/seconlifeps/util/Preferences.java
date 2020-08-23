package com.example.seconlifeps.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public String getFile(Context context) {
        String response = "";
        response =
                context.getSharedPreferences("ps", Context.MODE_PRIVATE).getString("fileName", "");

        return response;
    }

   public void saveFile(Context context, String fileName) {

        SharedPreferences.Editor editor = context.getSharedPreferences("ps", Context.MODE_PRIVATE).edit();
        editor.putString("fileName", fileName);
//        editor.putBoolean("isHomeCreate", false)
        editor.apply();
    }
}
