package com.github.myrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    private PtrClassicFrameLayout pcfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl);
        pcfl.disableWhenHorizontalMove(true);
        pcfl.setHorizontalMoveFlag(3);
        pcfl.setOffsetXFlag(3);
        pcfl.setScaledTouchSlopFlag((float) 0.5);
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

    }
}
