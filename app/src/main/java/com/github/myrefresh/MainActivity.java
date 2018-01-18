package com.github.myrefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    PtrClassicFrameLayout pcfl;
    Banner bn_home;
    private List<String> bannerList;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl);
        pcfl.disableWhenHorizontalMove(true);
        pcfl.setYOffsetMultiple(3);
        pcfl.setXOffsetMultiple(3);
        pcfl.setScaledTouchMultiple((float) 0.5);
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

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

        pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl);
        bn_home = (Banner) findViewById(R.id.bn_home);
        initView();

        iv = (ImageView) findViewById(R.id.iv);
        Glide.with(MainActivity.this).load("http://pic.qqtn.com/file/2013/2014-12/2014122616202514075.gif").listener(mRequestListener).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(600, 200).into(iv);

    }
    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            Log.d("TAG", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
            iv.setImageResource(R.mipmap.ic_launcher);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            Log.e("TAG",  "model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }
    };
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
//        bannerList.add("http://storage.slide.news.sina.com.cn/slidenews/77_ori/2018_03/74766_810957_613903.gif");
        bannerList.add("http://121.40.186.118:1145/upload/zixun.png");
        bannerList.add("http://img.ivsky.com/img/tupian/pre/201709/30/haitan-009.jpg");
        bannerList.add("http://img.ivsky.com/img/tupian/pre/201008/23/haianshatan-001.jpg");
        bn_home.setImages(bannerList);
        bn_home.setImageLoader(new GlideLoader());


//        bn_home.start();

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
