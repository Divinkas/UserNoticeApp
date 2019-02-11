package com.example.notificeuserapp.view.fragment.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setTitle(int title){
        Objects.requireNonNull(getActivity()).setTitle(title);
    }

    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }
}
