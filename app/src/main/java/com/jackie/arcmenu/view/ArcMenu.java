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
public class ArcMenu extends ViewGroup {
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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
