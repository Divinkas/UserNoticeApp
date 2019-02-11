package com.example.notificeuserapp.view.activity.base;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;

import com.example.notificeuserapp.R;

public abstract class BaseNetworkActivity extends BaseActivity {

    public abstract void initView();

    public void errorConnection(){
        setContentView(R.layout.error_connection);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(view -> {
            if(isNetworkConnection())
                initView();
        });
    }

    protected boolean isNetworkConnection(){
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (networkInfo != null) {
            if (networkInfo.isConnected()) {
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnected()) || (wifi != null && wifi.isConnected());
            } else
                return false;
        }
        return false;
    }
}
