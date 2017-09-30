package com.example.administrator.demos;

import android.content.ContentUris;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ZanAdapter extends RecyclerView.Adapter<ZanAdapter.ZanViewHolder> {
    private Context mContext;
    private List<ZanBean> mDatas;
    private LayoutInflater mInflater;
    private ZanCallBack mZanCallBack;

    public ZanAdapter(Context context,List<ZanBean> data) {
        mContext = context;
        mDatas = data;
        mInflater = LayoutInflater.from(context);
        if (mContext instanceof ZanCallBack) {
            mZanCallBack = (ZanCallBack) mContext;
        }
    }

    @Override
    public ZanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZanViewHolder(mInflater.inflate(R.layout.item_zan,parent,false));
    }

    @Override
    public void onBindViewHolder(final ZanViewHolder holder, int position) {

        final ZanBean zanBean = mDatas.get(position);
        holder.tvZan.setText(zanBean.getZanCount() + "");
        holder.tvZan.setSelected(zanBean.isZanded());
        holder.tvZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int pos = holder.getLayoutPosition();

                holder.tvZan.setSelected(!zanBean.isZanded());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mZanCallBack != null) {
                            double random = Math.random();
                            if (random > 0.5) {//成功
                                Toast.makeText(mContext, (zanBean.isZanded() ? "取消点赞" : "点赞") + "操作成功，回调:" + pos, Toast.LENGTH_SHORT).show();
                                mZanCallBack.onSuccess(pos);
                            }else {//失败
                                Toast.makeText(mContext, (zanBean.isZanded() ? "取消点赞" : "点赞") + "操作失败，回调:" + pos, Toast.LENGTH_SHORT).show();
                                mZanCallBack.onError(pos);
                            }
                        }
                    }
                },3000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ?mDatas.size() : 0;
    }

    public static class ZanViewHolder extends RecyclerView.ViewHolder{
        TextView tvZan;

        public ZanViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface ZanCallBack{
        void onSuccess(int pos);
        void onError(int pos);
    }
}
