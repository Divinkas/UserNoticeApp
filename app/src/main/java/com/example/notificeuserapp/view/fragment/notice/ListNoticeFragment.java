package com.example.notificeuserapp.view.fragment.notice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.presenter.notice.NoticePresenter;
import com.example.notificeuserapp.presenter.notice.interfaces.ILoadNoticePresenter;
import com.example.notificeuserapp.R;
import com.example.notificeuserapp.utils.Constants;
import com.example.notificeuserapp.view.application.MyApplication;
import com.example.notificeuserapp.view.adapter.NoticeAdapter;
import com.example.notificeuserapp.view.activity.base.BaseFragmentActivity;
import com.example.notificeuserapp.view.callback.IListNoticeCallback;
import com.example.notificeuserapp.view.fragment.base.BaseFragment;
import com.example.notificeuserapp.view.interfaces.INoticeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListNoticeFragment extends BaseFragment implements INoticeView, IListNoticeCallback {
    private ILoadNoticePresenter presenter;
    private String userId;

    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private NoticeAdapter adapter;

    private BaseFragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new NoticePresenter(context);
        ((NoticePresenter) presenter).setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        setTitle(R.string.app_name);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvListNotices);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        activity = (BaseFragmentActivity) getActivity();

        if(this.isAdded()){
            adapter = new NoticeAdapter(new ArrayList<>(), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);

            refreshLayout.setOnRefreshListener(() -> {
                refreshLayout.setRefreshing(false);
                presenter.loadMyNotices(userId);
            });

            if(MyApplication.getFirebaseAuth() != null)
                userId = Objects.requireNonNull(MyApplication.getFirebaseAuth().getCurrentUser()).getUid();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadMyNotices(userId);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(context, R.string.error_loading, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadListNotice(List<Notice> noticeList) {
        List<Notice> list = new ArrayList<>();
        if(noticeList!=null){ list = noticeList; }
        adapter.setList(list);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void deleteNotice(Notice notice) {
        presenter.deleteNotice(notice);
    }

    @Override
    public void openDetail(Notice notice) {
        BaseFragment optionNoticeFragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ID_NOTICE, notice.getIdNotice());
        bundle.putString(Constants.USER_ID_NOTICE, notice.getUserId());
        bundle.putString(Constants.TEXT_NOTICE, notice.getTextNotice());
        optionNoticeFragment.setArguments(bundle);
        activity.showFragment(optionNoticeFragment);
    }
}
