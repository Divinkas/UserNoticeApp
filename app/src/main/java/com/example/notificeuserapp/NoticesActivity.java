package com.example.notificeuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notificeuserapp.View.BaseFragmentActivity;

public class NoticesActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){
            if(getCurrentUser()== null)
                openLoginActivity();
            else {
                initView();
            }
        }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_notices);
        initFragmentViewer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newNotice:
                openNewNoticeActivity();
                break;
            case R.id.logOut:
                logOut();
                break;
        }
        return true;
    }

    private void openNewNoticeActivity() {
        Intent intent = new Intent(this, NewNoticeActivity.class);
        startActivity(intent);
    }
}
