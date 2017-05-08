package com.example.myplay.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myplay.http.Url;
import com.example.myplay.utils.GlideUtil;

import java.util.ArrayList;

/**
 * Created by dance on 2017/5/7.
 */

public class HomeHeaderAdapter extends PagerAdapter {
    private ArrayList<String> urlList;

    public HomeHeaderAdapter(ArrayList<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return 100 * 10000;
//        return urlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        //让图片平铺到ImageView的整个宽高
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //加载图片
        GlideUtil.loadImage(Url.ImagePrefix + urlList.get(position% urlList.size()), imageView);
        //添加进来
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
