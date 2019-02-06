package com.example.notificeuserapp.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;

public interface IPresenter<T extends IView> {

    void onAttach(T view);

    void onRestoreView(@NonNull Bundle savedInstanceState);

    void onSaveStateView(@NonNull Bundle savedInstanceState);

    void onDetach();

    void setUserAsLoggedOut();
}
