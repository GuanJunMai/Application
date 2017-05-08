package com.example.myplay.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.widget.ImageView;

import com.example.myplay.R;
import com.example.myplay.bean.AppInfo;
import com.example.myplay.http.Url;
import com.example.myplay.utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dance on 2017/5/5.
 * 终极Adapter的编写方式
 */

public class RecyclerAdapter extends AnimationAdapter<AppInfo> {


    public RecyclerAdapter(Context context, int layoutId, List<AppInfo> datas) {
        super(context, layoutId, datas);
    }

    /**
     * 只需要实现绑定数据
     * @param holder
     * @param appInfo
     * @param position
     */
    @Override
    protected void convert(ViewHolder holder, AppInfo appInfo, int position) {
        super.convert(holder,appInfo,position);
        holder.setText(R.id.tv_name, appInfo.name)
              .setRating(R.id.rb_star,appInfo.stars)
              .setText(R.id.tv_size, Formatter.formatFileSize(holder.itemView.getContext(),appInfo.size))
              .setText(R.id.tv_des,appInfo.des);

        //加载图片
        GlideUtil.loadCircleImage(Url.ImagePrefix+appInfo.iconUrl, (ImageView) holder.getView(R.id.iv_icon));

    }
}
