package com.custom.top;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.R;

/**
 * Created by Kevin on 2016/5/11.
 * 左图左字   中字  右字右图
 */
public class TopBar extends RelativeLayout {
    private boolean leftImgIsShow;//左侧图标是否显示
    private Drawable leftImg;

    private String leftText;
    private float leftTextSize;
    private int leftTextColor;

    private String centerText;
    private float centerTextSize;
    private int centerTextColor;

    private String rightText;
    private float rightTextSize;
    private int rightTextColor;

    private boolean rightImgIsShow;//右侧图标是否显示
    private Drawable rightImg;

    private ImageView img_left, img_right;
    private TextView txt_left, txt_center, txt_right;
    private LayoutParams leftImgParams, leftTxtParams, centerParams, rightTxtParams, rightImgParams;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        leftImgIsShow = array.getBoolean(R.styleable.topBar_leftImgIsShow, false);
        if (leftImgIsShow) {
            leftImg = array.getDrawable(R.styleable.topBar_leftImg);
            img_left = new ImageView(context);
            img_left.setImageDrawable(leftImg);
            leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            addView(img_left, leftImgParams);
        }

        leftText = array.getString(R.styleable.topBar_leftText);
        leftTextSize = array.getDimension(R.styleable.topBar_leftTextSize, 14);
        leftTextColor = array.getColor(R.styleable.topBar_leftTextColor, Color.WHITE);

        centerText = array.getString(R.styleable.topBar_centerText);
        centerTextSize = array.getDimension(R.styleable.topBar_centerTextSize, 14);
        centerTextColor = array.getColor(R.styleable.topBar_centerTextColor, Color.WHITE);

        rightText = array.getString(R.styleable.topBar_rightText);
        rightTextSize = array.getDimension(R.styleable.topBar_rightTextSize, 14);
        rightTextColor = array.getColor(R.styleable.topBar_rightTextColor, Color.WHITE);

        rightImgIsShow = array.getBoolean(R.styleable.topBar_rightImgIsShow, false);
        if (rightImgIsShow) {
            rightImg = array.getDrawable(R.styleable.topBar_rightImg);
            img_right = new ImageView(context);
            img_right.setImageDrawable(rightImg);
            rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            addView(img_right, rightImgParams);
        }
        array.recycle();

        txt_left = new TextView(context);
        txt_left.setText(leftText);
        txt_left.setTextSize(leftTextSize);
        txt_left.setTextColor(leftTextColor);

        leftTxtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (null == leftImgParams) {
            leftTxtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);    //addRule一个参数
        } else {
            leftTxtParams.addRule(RelativeLayout.RIGHT_OF, img_left.getId()); //addRule一个参数
        }
        addView(txt_left, leftTxtParams);

        txt_center = new TextView(context);
        txt_center.setText(centerText);
        txt_center.setTextSize(centerTextSize);
        txt_center.setTextColor(centerTextColor);

        centerParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(txt_center, centerParams);

        txt_right = new TextView(context);
        txt_right.setText(rightText);
        txt_right.setTextSize(rightTextSize);
        txt_right.setTextColor(rightTextColor);

        rightTxtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (null == rightImgParams) {
            rightTxtParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);    //addRule一个参数
        } else {
            rightTxtParams.addRule(RelativeLayout.LEFT_OF, img_right.getId()); //addRule一个参数
        }
        addView(txt_right, rightTxtParams);
    }
}
