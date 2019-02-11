package com.example.notificeuserapp.view.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notificeuserapp.R;
import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.view.callback.IListNoticeCallback;

import java.util.List;

public class NoticeAdapter extends BaseAdapter<Notice> {
    private IListNoticeCallback callback;

    public NoticeAdapter(List<Notice> noticeList, IListNoticeCallback callback) {
        list = noticeList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NoticeViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notice_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if(holder instanceof NoticeViewHolder){
            NoticeViewHolder noticeHolder = (NoticeViewHolder)holder;
            noticeHolder.setIsRecyclable(false);
            noticeHolder.tvNoticeText.setText(list.get(position).getTextNotice());
            noticeHolder.tvNoticeId.setText(String.valueOf(list.get(position).getIdNotice()));
            noticeHolder.imgDeleteNotice.setOnClickListener(view -> {
                callback.deleteNotice(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
            });
            noticeHolder.llContainer.setOnClickListener(view -> callback.openDetail(list.get(position)));
        }
    }



    class NoticeViewHolder extends BaseViewHolder{
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
