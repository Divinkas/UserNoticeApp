package com.example.notificeuserapp.View.Notices;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notificeuserapp.Base.BaseActivity;

public class NoticesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        initView();

    }


    public void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                openNewNoticeActivity();
                break;
            case 2:
                logOut();
                break;
        }
        return true;
    }

    private void openNewNoticeActivity() {
        BaseActivity.startActivity(this, NewNoticeActivity.class);
    }

    @Override
    protected void onDestroyActivity() {

    }
}
