package com.example.myplay.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myplay.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dance on 2017/5/5.
 */

public class GlideUtil {
    /**
     * 封装Glide加载图片的代码
     * @param imgUrl
     * @param imageView
     */
    public static void loadImage(String imgUrl, ImageView imageView){
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.ic_default)//设置加载过程中的占位图片
                .error(R.drawable.ic_default)//设置加载失败显示的图片
                .crossFade(800)//设置图片渐渐显示出来的时间
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imgUrl
     * @param imageView
     */
    public static void loadRoundImage(String imgUrl, ImageView imageView,int radius){
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(),radius,0))
                .placeholder(R.drawable.ic_default)//设置加载过程中的占位图片
                .error(R.drawable.ic_default)//设置加载失败显示的图片
                .crossFade(800)//设置图片渐渐显示出来的时间
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imgUrl
     * @param imageView
     */
    public static void loadCircleImage(String imgUrl, ImageView imageView){
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .placeholder(R.drawable.ic_default)//设置加载过程中的占位图片
                .error(R.drawable.ic_default)//设置加载失败显示的图片
                .crossFade(800)//设置图片渐渐显示出来的时间
                .into(imageView);
    }
}
