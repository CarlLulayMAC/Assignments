package com.example.admin.myapplication.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.myapplication.R;

public class MyRectView extends View {

    int boxHeight;
    int boxWidth;
    int boxStartX;
    int boxStartY;
    int fillColor;
    private Paint paint;
    int width;
    int height;

    public MyRectView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public MyRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public MyRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        // A typed array holds all attribute values - specifically for holding view attribute values
        // i.e. primitives
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MyRectView, defStyleAttr, defStyleRes);

        boxHeight = typedArray.getInt(R.styleable.MyRectView_boxHeight, 40);
        boxWidth = typedArray.getInt(R.styleable.MyRectView_boxWidth, 40);
        boxStartX = typedArray.getInt(R.styleable.MyRectView_boxStartX, 40);
        boxStartY = typedArray.getInt(R.styleable.MyRectView_boxStartY, 40);

        fillColor = typedArray.getColor(R.styleable.MyRectView_fillColor,
                getResources().getColor(android.R.color.black));

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(fillColor);
        canvas.drawRect(boxStartX, boxStartY, boxStartX + boxWidth, boxStartY + boxHeight, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredHeight = 400;
        int desiredWidth = 400;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredWidth;
        }
        setMeasuredDimension(width, height);
    }

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
        invalidate();
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
        invalidate();
    }

    public void setFillColor(int color) {
        this.fillColor = color;
        invalidate();
    }

    public void setBoxStartX(int boxStartX) {
        this.boxStartX = boxStartX;
    }

    public void setBoxStartY(int boxStartY) {
        this.boxStartY = boxStartY;
    }
}