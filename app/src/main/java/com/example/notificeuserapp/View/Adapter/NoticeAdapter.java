package com.example.notificeuserapp.View.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Presenter.interfaces.ILoadNoticePresenter;
import com.example.notificeuserapp.R;
import com.example.notificeuserapp.Utils.Constants;
import com.example.notificeuserapp.View.Fragment.BaseFragment;
import com.example.notificeuserapp.View.Fragment.EditFragment;
import com.example.notificeuserapp.View.IFragmentView;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private List<Notice> noticeList;
    private Context context;
    private ILoadNoticePresenter presenter;
    private IFragmentView view;

    public NoticeAdapter(List<Notice> noticeList, Context context,
                         ILoadNoticePresenter presenter, IFragmentView view) {
        this.noticeList = noticeList;
        this.context = context;
        this.presenter = presenter;
        this.view = view;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NoticeViewHolder(LayoutInflater
                        .from(context).inflate(R.layout.notice_item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.tvNoticeText.setText(noticeList.get(position).getTextNotice());
        holder.tvNoticeId.setText(String.valueOf(noticeList.get(position).getIdNotice()));
        holder.imgDeleteNotice.setOnClickListener(view -> {
            presenter.deleteNotice(noticeList.get(position));
            noticeList.remove(position);
            notifyDataSetChanged();
        });
        holder.llContainer.setOnClickListener(view -> {
            BaseFragment optionNoticeFragment = new EditFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.ID_NOTICE, noticeList.get(position).getIdNotice());
            bundle.putString(Constants.USER_ID_NOTICE, noticeList.get(position).getUserId());
            bundle.putString(Constants.TEXT_NOTICE, noticeList.get(position).getTextNotice());
            optionNoticeFragment.setArguments(bundle);

            showFragment(optionNoticeFragment);
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void setList(List<Notice> newList){
        noticeList = newList;
        notifyDataSetChanged();
    }

    private void showFragment(BaseFragment optionNoticeFragment) {
        view.openFragment(optionNoticeFragment);
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder{
        TextView tvNoticeId, tvNoticeText;
        LinearLayout llContainer;
        ImageView imgDeleteNotice;
        NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoticeId = itemView.findViewById(R.id.tvNoticeId);
            tvNoticeText = itemView.findViewById(R.id.tvTextNotice);
            llContainer = itemView.findViewById(R.id.llNoticeItemContainer);
            imgDeleteNotice = itemView.findViewById(R.id.imgDeleteNotice);
        }
    }

}
