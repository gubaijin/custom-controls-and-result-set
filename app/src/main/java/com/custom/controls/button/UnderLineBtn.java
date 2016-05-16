package com.custom.controls.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.custom.R;
import com.custom.utils.StringUtil;

/**
 * Created by Kevin on 2016/5/13.
 */
public class UnderLineBtn extends RelativeLayout {
    /**
     * 对应属性
     */
    private float lineHeight, lineWeight, textSize;
    private int unCheckedColor, checkedColor;
    private boolean isChecked;
    private String text;

    /**
     * 控件
     */
    private TextView textView;//文字
    private View view;//下划线
    private LayoutParams textViewParams, viewParams;

    public UnderLineBtn(Context context) {
        super(context);
    }

    public UnderLineBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public UnderLineBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 获取自定义属性
         */
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.underLineBtn);
        lineHeight = array.getDimension(R.styleable.underLineBtn_lineHeight, 1);
        lineWeight = array.getDimension(R.styleable.underLineBtn_lineWight, LayoutParams.MATCH_PARENT);
        textSize = array.getDimensionPixelSize(R.styleable.underLineBtn_textSize, 14);
        unCheckedColor = array.getColor(R.styleable.underLineBtn_unCheckedColor, Color.WHITE);
        checkedColor = array.getColor(R.styleable.underLineBtn_checkedColor, Color.BLUE);
        isChecked = array.getBoolean(R.styleable.underLineBtn_isChecked, false);
        text = array.getString(R.styleable.underLineBtn_text);
        array.recycle();//属性获取完之后及时回收

        //给控件赋值
        textView = new TextView(context);
        textView.setId(StringUtil.generateViewId());
        view = new View(context);
        if (isChecked) {
            textView.setTextColor(checkedColor);
            view.setBackgroundColor(checkedColor);
        } else {
            textView.setTextColor(unCheckedColor);
            view.setBackgroundColor(unCheckedColor);
        }
        textView.setText(text);
//        textView.setTextSize(textSize);
        textView.getPaint().setTextSize(textSize);
        //控件位置
        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(textView, textViewParams);

        viewParams = new LayoutParams((int) lineWeight, (int) lineHeight);
        viewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(view, viewParams);
    }

    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            /**
             * 保留改变后的显示，执行drawableStateChanged()中的变化
             * 不执行本方法：drawableStateChanged()中设置的改变将被复原
             */
            refreshDrawableState();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        //改变时的切换逻辑
            if(isChecked){
                textView.setTextColor(checkedColor);
                view.setBackgroundColor(checkedColor);
            }else{
                textView.setTextColor(unCheckedColor);
                view.setBackgroundColor(unCheckedColor);
            }
        }
}
