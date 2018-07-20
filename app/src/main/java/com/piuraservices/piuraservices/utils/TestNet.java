package com.piuraservices.piuraservices.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class TestNet {
    public static boolean test(Context context) {
        if(!isRedConnect(context)){
           // Toast.makeText(context, context.getString(R.string.error_no_red), Toast.LENGTH_SHORT).show();
           Toast.makeText(context, " No esta conectado a una red", Toast.LENGTH_SHORT).show();
        } else if(!isInternetConnect()){
            //Toast.makeText(context, context.getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Error de conexion a internet", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    private static boolean isRedConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }
    private static boolean isInternetConnect() {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}