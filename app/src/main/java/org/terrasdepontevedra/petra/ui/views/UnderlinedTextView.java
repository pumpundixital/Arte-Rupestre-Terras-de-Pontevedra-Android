package org.terrasdepontevedra.petra.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;

import org.terrasdepontevedra.petra.R;

public class UnderlinedTextView extends AppCompatTextView {

    private Rect mRect;
    private Paint mPaint;
    private float mStrokeWidth;

    public UnderlinedTextView(Context context) {
        this(context, null, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyle) {

        float density = context.getResources().getDisplayMetrics().density;

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.UnderlinedTextView, defStyle, 0);
        int underlineColor = typedArray.getColor(R.styleable.UnderlinedTextView_underlineColor, 0xFFFF0000);
        mStrokeWidth = typedArray.getDimension(R.styleable.UnderlinedTextView_underlineWidth, density * 2);
        typedArray.recycle();

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(underlineColor);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    public int getUnderLineColor() {
        return mPaint.getColor();
    }

    public void setUnderLineColor(int mColor) {
        mPaint.setColor(mColor);
        invalidate();
    }

    public float getUnderlineWidth() {
        return mStrokeWidth;
    }

    public void setUnderlineWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int count = getLineCount();

        final Layout layout = getLayout();
        float x_start, x_stop, x_diff;
        int firstCharInLine, lastCharInLine;
        try {

            for (int i = 0; i < count; i++) {
                int baseline = getLineBounds(i, mRect);
                firstCharInLine = layout.getLineStart(i);
                lastCharInLine = layout.getLineEnd(i);

                x_start = layout.getPrimaryHorizontal(firstCharInLine);
                x_diff = layout.getPrimaryHorizontal(firstCharInLine + 1) - x_start;
                x_stop = layout.getPrimaryHorizontal(lastCharInLine - 1) + x_diff;

                canvas.drawLine(x_start, baseline + mStrokeWidth, x_stop, baseline + mStrokeWidth, mPaint);
            }
        }
        catch (Exception e){

        }

        super.onDraw(canvas);
    }
}