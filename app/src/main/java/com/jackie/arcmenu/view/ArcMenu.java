package com.jackie.arcmenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.jackie.arcmenu.R;

/**
 * Created by Jackie on 2016/1/10.
 * 扇形菜单
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener {
    private enum POSITION { LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM};
    private POSITION mPosition = POSITION.RIGHT_BOTTOM;
    private int mRadius;

    private enum STATUS { OPEN, CLOSE }
    /**
     * 菜单当前状态
     */
    private STATUS mCurrentStatus = STATUS.CLOSE;

    /**
     * 菜单主按钮
     */
    private View mMainButton;

    public interface onArcMenuItemClickListener {
        void onItemClick(View view, int position);
    }

    private onArcMenuItemClickListener mOnArcMenuItemClickListener;

    public void setonArcMenuItemClickListener(onArcMenuItemClickListener onArcMenuItemClickListener) {
        this.mOnArcMenuItemClickListener = onArcMenuItemClickListener;
    }

    private static final int LEFT_TOP = 0;
    private static final int LEFT_BOTTOM = 1;
    private static final int RIGHT_TOP = 2;
    private static final int RIGHT_BOTTOM = 3;

    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ArcMenu);
        int position = ta.getInt(R.styleable.ArcMenu_position, RIGHT_BOTTOM);
        switch (position) {
            case LEFT_TOP:
                mPosition = POSITION.LEFT_TOP;
                break;
            case LEFT_BOTTOM:
                mPosition = POSITION.LEFT_BOTTOM;
                break;
            case RIGHT_TOP:
                mPosition = POSITION.RIGHT_TOP;
                break;
            case RIGHT_BOTTOM:
                mPosition = POSITION.RIGHT_BOTTOM;
                break;
        }

        mRadius = (int) ta.getDimension(R.styleable.ArcMenu_radius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics()));
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutMainButton();
            layoutChild();
        }
    }

    private void layoutMainButton() {
        //布局主Button
        mMainButton = getChildAt(0);
        mMainButton.setOnClickListener(this);

        int left = 0;
        int top = 0;

        int width = mMainButton.getMeasuredWidth();
        int height = mMainButton.getMeasuredHeight();

        switch (mPosition) {
            case LEFT_TOP:
                left = 0;
                top = 0;
                break;
            case LEFT_BOTTOM:
                left = 0;
                top = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                left = getMeasuredWidth() - width;
                top = 0;
                break;
            case RIGHT_BOTTOM:
                left = getMeasuredWidth() - width;
                top = getMeasuredHeight() - height;
                break;
        }

        mMainButton.layout(left, top, left + width, top + height);
    }

    private void layoutChild() {
        int childCount = getChildCount();  //包括主按钮
        for (int i = 0; i < childCount - 1; i++) {
            View child = getChildAt(i + 1); //主按钮是第0个，必须跳过布局

            int l = (int) (mRadius * Math.sin(Math.PI / 2 / (childCount - 2) * i));
            int t = (int) (mRadius * Math.cos(Math.PI / 2 / (childCount - 2) * i));

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            //当菜单的位置在坐下或者右下
            if (mPosition == POSITION.LEFT_BOTTOM || mPosition == POSITION.RIGHT_BOTTOM) {
                t = getMeasuredHeight() - height - t;
            }

            //当菜单的位置在右上或者右下
            if (mPosition == POSITION.RIGHT_TOP || mPosition == POSITION.RIGHT_BOTTOM) {
                l = getMeasuredWidth() - width - l;
            }

            child.layout(l, t, l + width, t + height);
        }
    }

    @Override
    public void onClick(View v) {
    }
}
