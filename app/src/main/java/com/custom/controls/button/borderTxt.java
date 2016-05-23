package com.custom.controls.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.R;
import com.custom.utils.StringUtil;

/**
 * 带边框点击背景变色的textview
 * Created by Kevin on 2016/5/16.
 */
public class BorderTxt extends RelativeLayout {
    private int unCheckedColor, checkedBgColor, checkedTxtColor;
    private boolean isChecked;
    private String text1, text2;
    private float strokeWight, cornersRadius, textSize1, textSize2;

    private TextView txt1, txt2;
    private LayoutParams txt1Params, txt2Params, relParams;
    private RelativeLayout relativeLayout;

    public BorderTxt(Context context) {
        super(context);
    }

    public BorderTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BorderTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.borderTxt);
        unCheckedColor = array.getColor(R.styleable.borderTxt_bt_unCheckedColor, Color.GRAY);
        checkedBgColor = array.getColor(R.styleable.borderTxt_bt_checkedBgColor, Color.BLUE);
        checkedTxtColor = array.getColor(R.styleable.borderTxt_bt_checkedTxtColor, Color.WHITE);
        isChecked = array.getBoolean(R.styleable.borderTxt_bt_isChecked, false);
        text1 = array.getString(R.styleable.borderTxt_bt_text1);
        text2 = array.getString(R.styleable.borderTxt_bt_text2);
        strokeWight = array.getDimension(R.styleable.borderTxt_bt_strokeWight, 1);
        cornersRadius = array.getDimension(R.styleable.borderTxt_bt_cornersRadius, 5);
        textSize1 = array.getDimensionPixelSize(R.styleable.borderTxt_bt_textSize1, 14);
        textSize2 = array.getDimensionPixelSize(R.styleable.borderTxt_bt_textSize2, 10);
        array.recycle();

        txt1 = new TextView(context);
        txt2 = new TextView(context);
        txt1.setId(StringUtil.generateViewId());
        txt1.setText(text1);
        txt1.setTextSize(textSize1);
        txt1.setTextColor(getResources().getColor(R.drawable.custom_border_txt_color));
        txt2.setText(text2);
        txt2.setTextSize(textSize2);
        txt2.setTextColor(getResources().getColor(R.drawable.custom_border_txt_color));

        relativeLayout = new RelativeLayout(context);
        relParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relParams.addRule(CENTER_IN_PARENT);
        relativeLayout.setLayoutParams(relParams);

        txt1Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        txt1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        txt1.setGravity(Gravity.CENTER);

        txt2Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        txt2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        txt2Params.addRule(BELOW, txt1.getId());

        relativeLayout.addView(txt1, txt1Params);
        relativeLayout.addView(txt2, txt2Params);
        addView(relativeLayout);

        Drawable shape = getResources().getDrawable(R.drawable.custom_border_txt_bg);//问题1：点击无法变色
        setBackgroundResource(R.drawable.custom_border_txt_bg);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFocused()){
                    //已获得焦点
                }else{
                    requestFocus();//获得焦点
                    txt1.requestFocus();
                    txt2.requestFocus();
                }
            }
        });
    }
}
