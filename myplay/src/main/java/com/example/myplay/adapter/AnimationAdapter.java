package com.example.myplay.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.animation.OvershootInterpolator;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dance on 2017/5/7.
 * 带有动画的adapter
 */

public class AnimationAdapter<T> extends CommonAdapter<T> {
    public AnimationAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, T t, int position) {
        holder.itemView.setScaleX(0.5f);
        holder.itemView.setScaleY(0.5f);
        ViewCompat.animate(holder.itemView).scaleX(1f).scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(500).start();
    }
}
