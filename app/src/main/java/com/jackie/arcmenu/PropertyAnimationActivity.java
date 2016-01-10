package com.jackie.arcmenu;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private  int mImageId[] = { R.id.a, R.id.b, R.id.c, R.id.d, R.id.e, R.id.f, R.id.g, R.id.h };
    private List<ImageView> mImageList = new ArrayList<>();

    private boolean mIsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        for (int i = 0; i < mImageId.length; i++) {
            ImageView imageView = (ImageView) findViewById(mImageId[i]);
            imageView.setOnClickListener(this);
            mImageList.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a:
                if (!mIsOpen) {
                    startAnimator();
                    mIsOpen = true;
                } else {
                    closeAnimator();
                    mIsOpen = false;
                }
                break;
            default:
                break;
        }
    }

    private void startAnimator() {
        /**
         * 多个属性动画的使用方法：
         ImageView imageView = mImageList.get(0);
         //方法1
         ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200).setDuration(500).start();
         ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200).setDuration(500).start();
         ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360).setDuration(500).start();
         //方法2
         PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("translationX", 0f, 200);
         PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("translationY", 0f, 200);
         PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", 0f, 360);
         ObjectAnimator.ofPropertyValuesHolder(xHolder, yHolder, rotationHolder);
         //方法3
         AnimatorSet animatorSet = new AnimatorSet();
         ObjectAnimator xAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200);
         ObjectAnimator yAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200);
         ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360);
         animatorSet.playTogether(xAnimator, yAnimator, rotationAnimator);  //动画同时进行
         animatorSet.playSequentially(xAnimator, yAnimator, rotationAnimator);  //动画顺序进行
         animatorSet.play(xAnimator).with(yAnimator);         //X轴和Y轴的平移动画同时进行
         animatorSet.play(rotationAnimator).after(xAnimator); //旋转动画在平移动画之后进行
         animatorSet.setDuration(500).start();
         */

        for (int i = 0; i < mImageList.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageList.get(i), "translationY", 0f, i * 150);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(500);
            animator.setStartDelay(i * 300);
            animator.start();
        }
    }

    private void closeAnimator() {
        for (int i = 0; i < mImageList.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageList.get(i), "translationY", i * 150, 0f);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(500);
            animator.setStartDelay(i * 300);
            animator.start();
        }
    }
}
