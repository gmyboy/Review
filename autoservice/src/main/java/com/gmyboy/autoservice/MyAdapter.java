package com.gmyboy.autoservice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gmy on 2017/1/12.
 * E-mail me via gmyboy@qq.com
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private List<String> datas;
    private onBtnClickListener listener;

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    public void setListener(onBtnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv1.setText(datas.get(position));
        holder.btn1.setText(datas.get(position));
        holder.btn2.setText(datas.get(position));
        holder.btn3.setText(datas.get(position));
        holder.btn1.setTag(position);
        holder.btn2.setTag(position);
        holder.btn3.setTag(position);
        holder.btn1.setOnClickListener(this);
        holder.btn2.setOnClickListener(this);
        holder.btn3.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View view) {
        if (null != listener) {
            switch (view.getId()) {
                case R.id.btn1:
                    listener.onBtn1(view, (Integer) view.getTag());
                    break;
                case R.id.btn2:
                    listener.onBtn2(view, (Integer) view.getTag());
                    break;
                case R.id.btn3:
                    listener.onBtn3(view, (Integer) view.getTag());
                    break;
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.btn1)
        Button btn1;
        @BindView(R.id.btn2)
        Button btn2;
        @BindView(R.id.btn3)
        Button btn3;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    interface onBtnClickListener {
        void onBtn1(View v, int position);

        void onBtn2(View v, int position);

        void onBtn3(View v, int position);
    }
}
