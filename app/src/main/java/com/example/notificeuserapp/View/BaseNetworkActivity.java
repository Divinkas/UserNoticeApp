package com.example.notificeuserapp.View;

import android.view.View;
import android.widget.Button;

import com.example.notificeuserapp.R;

public abstract class BaseNetworkActivity extends BaseActivity {

    public abstract void initView();

    public void errorConnection(){
        setContentView(R.layout.error_connection);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnection())
                    initView();
            }
        });
    }

    protected boolean isNetworkConnection(){

        return true;
    }
}
