package com.example.notificeuserapp.view.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.notificeuserapp.R;
import com.example.notificeuserapp.view.fragment.base.BaseFragment;
import com.example.notificeuserapp.view.fragment.ListNoticeFragment;

import java.util.Objects;

public abstract class BaseFragmentActivity extends AuthentificationActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else { super.onBackPressed(); }
    }

    public void showDefaultFragment() {
        clearBackStack();
        showFragment(getDefaultFragment());
    }

    protected BaseFragment getDefaultFragment(){
        return new ListNoticeFragment();
    }

    public void showFragment(BaseFragment fragment){
        if(!isUsedFragment(fragment)) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, fragment.getFragmentTag())
                    .addToBackStack(fragment.getFragmentTag())
                    .commitAllowingStateLoss();
        } else if(!isTopPosition(fragment, getSupportFragmentManager())){
            fragmentManager.popBackStackImmediate(fragment.getFragmentTag(), 0);
        }
    }

    private boolean isTopPosition(BaseFragment fragment, FragmentManager fm) {
        return Objects.requireNonNull(fm.findFragmentByTag(fragment.getFragmentTag())).isResumed();
    }

    private boolean isUsedFragment(BaseFragment fragment){
        Fragment newFragment = fragmentManager.findFragmentByTag(fragment.getFragmentTag());
        return newFragment != null;
    }

    private void clearBackStack() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
        ft.commitAllowingStateLoss();
    }

}
