package com.github.myrefresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2018/1/16.
 */

public class MyFragment extends Fragment implements Main2Activity.MyInter{

    private String TAG=this.getClass().getSimpleName();

    @Override
    public boolean move(boolean isHorizontal) {
        float y = pcfl.getScrollY();
        boolean inStartPosition = pcfl.isInTop();
        Log.i(TAG+"===",inStartPosition+"==="+isHorizontal);
       /* if(isHorizontal){
            pcfl.setEnabled(true);
        }
        if(inStartPosition){
            pcfl.setEnabled(false);
        }else{
            pcfl.setEnabled(true);
        }*/
        return inStartPosition;
    }

    PtrClassicFrameLayout pcfl;
    Banner bn_home;

    private List<String> bannerList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pcfl = view.findViewById(R.id.pcfl);
        pcfl.setXOffsetMultiple(3);
        pcfl.setYOffsetMultiple(3);
        pcfl.setScaledTouchMultiple(0.5f);
        pcfl.disableWhenHorizontalMove(true);
        bn_home = view.findViewById(R.id.bn_home);
        initView();
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        Glide.with(this).load("http://storage.slide.news.sina.com.cn/slidenews/77_ori/2018_03/74766_810957_613903.gif").into(imageView);

    }

    private void initView() {
        pcfl.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pcfl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pcfl.refreshComplete();
                    }
                },1000);
            }
        });

//        bn_home.setLayoutParams(ImageSizeUtils.getImageSizeLayoutParams(mContext));
        bannerList= new ArrayList<String>();

        bn_home.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerList.add("http://121.40.186.118:1145/upload/zixun.png");
        bannerList.add("http://121.40.186.118:1145/upload/zixun.png");
        bannerList.add("http://img.ivsky.com/img/tupian/pre/201709/30/haitan-009.jpg");
        bannerList.add("http://img.ivsky.com/img/tupian/pre/201008/23/haianshatan-001.jpg");
        bn_home.setImages(bannerList);
        bn_home.setImageLoader(new GlideLoader());


        bn_home.start();

    }
    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }

}
