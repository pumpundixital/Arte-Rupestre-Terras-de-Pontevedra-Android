package org.terrasdepontevedra.petra.ui.walk.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.views.UnderlinedTextView;
import org.terrasdepontevedra.petra.util.ScreenUtils;


public class BottomBarTab extends FrameLayout {
    private LinearLayout lLContainer;
    private UnderlinedTextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

    private TextView mTvUnreadCount;

    public BottomBarTab(Context context,  CharSequence title) {
        this(context, null, 0, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, CharSequence title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, title);
    }

    private void init(Context context, CharSequence title) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

         lLContainer = new LinearLayout(context);
        lLContainer.setOrientation(LinearLayout.VERTICAL);
        lLContainer.setGravity(Gravity.CENTER);
        LayoutParams paramsContainer = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContainer.gravity = Gravity.CENTER;
        lLContainer.setLayoutParams(paramsContainer);

        mTvTitle = new UnderlinedTextView(context);
        mTvTitle.setText(title);
        LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTv.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        mTvTitle.setTextSize(18);
        mTvTitle.setTextColor(ContextCompat.getColor(context, R.color.grey_dark));
        mTvTitle.setLayoutParams(paramsTv);
        mTvTitle.setUnderlineWidth(-100);
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
        mTvTitle.setTypeface(face);
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_mid));
        mTvTitle.setUnderLineColor(ContextCompat.getColor(mContext,R.color.grey_mid));

        lLContainer.addView(mTvTitle);

        addView(lLContainer);

        int min = dip2px(context, 20);
        int padding = dip2px(context, 5);
        mTvUnreadCount = new TextView(context);
        mTvUnreadCount.setMinWidth(min);
        mTvUnreadCount.setTextColor(Color.WHITE);
        mTvUnreadCount.setPadding(padding, 0, padding, 0);
        mTvUnreadCount.setGravity(Gravity.CENTER);
        LayoutParams tvUnReadParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, min);
        tvUnReadParams.gravity = Gravity.CENTER;
        tvUnReadParams.leftMargin = dip2px(context, 17);
        tvUnReadParams.bottomMargin = dip2px(context, 14);
        mTvUnreadCount.setLayoutParams(tvUnReadParams);
        mTvUnreadCount.setVisibility(GONE);

        addView(mTvUnreadCount);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            mTvTitle.setUnderLineColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            mTvTitle.setUnderlineWidth(dip2px(mContext,3));

        } else {
            setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_mid));
            mTvTitle.setUnderLineColor(ContextCompat.getColor(mContext,R.color.grey_dark));
            mTvTitle.setUnderLineColor(ContextCompat.getColor(mContext,R.color.grey_mid));

        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }


    private int dip2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
