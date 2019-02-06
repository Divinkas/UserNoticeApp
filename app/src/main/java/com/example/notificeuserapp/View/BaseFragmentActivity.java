package com.example.notificeuserapp.View;

import com.example.notificeuserapp.Utils.FragmentViewer;

public abstract class BaseFragmentActivity extends AuthentificationActivity {
    public FragmentViewer fragmentViewer;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else { super.onBackPressed(); }
    }

    public void initFragmentViewer(){
        fragmentViewer = new FragmentViewer(this);
    }

}
