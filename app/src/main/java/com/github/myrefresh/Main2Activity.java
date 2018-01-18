package com.github.myrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {

    private   final String TAG = Main2Activity.this.getClass().getSimpleName();
    private MyFragment myFragment;

    public interface MyInter{
        boolean move(boolean isHorizontal);
    }
    private  MyInter inter;
    private boolean mPreventForHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i(TAG+"===","===1");
        myFragment = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.ff);
        inter=myFragment;
        Log.i(TAG+"===","===2");


    }

    float downX,downY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX=e.getX();
                downY= e.getY();
                mPreventForHorizontal = false;
                inter.move(mPreventForHorizontal);
            case MotionEvent.ACTION_MOVE:
                float offsetX =e.getX()-downX;
                float offsetY =(e.getY()- downY)/1.7f;
                if (  !mPreventForHorizontal && (Math.abs(offsetX) * 3 > ViewConfiguration.get(this).getScaledTouchSlop() && 3 * Math.abs(offsetX) > Math.abs(offsetY))) {
                    /*if (mPtrIndicator.isInStartPosition()) {
                        mPreventForHorizontal = true;
                    }*/
                    inter.move(mPreventForHorizontal);
                }
        }
        return super.dispatchTouchEvent(e);

    }
}
