package com.example.notificeuserapp.view.fragment.notice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.presenter.notice.NoticePresenter;
import com.example.notificeuserapp.presenter.notice.interfaces.IUpdateNoticePresenter;
import com.example.notificeuserapp.R;
import com.example.notificeuserapp.utils.Constants;
import com.example.notificeuserapp.view.fragment.base.BaseFragment;

public class EditFragment extends BaseFragment {
    private IUpdateNoticePresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);
        presenter = new NoticePresenter(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        int idNotice = getArguments().getInt(Constants.ID_NOTICE);
        String userId = getArguments().getString(Constants.USER_ID_NOTICE);
        String textNotice = getArguments().getString(Constants.TEXT_NOTICE);

        AppCompatEditText apcText = view.findViewById(R.id.apcNoticeTextChanged);
        Button btnChangeNotice = view.findViewById(R.id.btnChangeNotice);

        apcText.setText(textNotice);
        btnChangeNotice.setOnClickListener(view1 -> {
            if(!apcText.getText().toString().isEmpty())
                presenter.updateNotice(
                        new Notice(idNotice, apcText.getText().toString(), userId)
                );
                Toast.makeText(context, R.string.notice_is_changed, Toast.LENGTH_SHORT).show();
        });
    }

}
