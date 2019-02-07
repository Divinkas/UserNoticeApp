package com.example.notificeuserapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    protected List<T> list;

    public void setList(List<T> newList){
        list = newList;
        notifyDataSetChanged();
    }

    public void clearList(){
        list.clear();
        notifyDataSetChanged();
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder{
        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
