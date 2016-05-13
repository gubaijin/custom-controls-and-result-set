package com.custom.controls.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
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
    private float lineHeight, lineWeight, textSize;
    private int unCheckedColor, checkedColor;
    private boolean isChecked;
    private String text;

    private TextView textView;//文字
    private View view;//下划线
    private LayoutParams textViewParams, viewParams;

    public UnderLineBtn(Context context) {
        super(context);
    }

    public UnderLineBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private StateListDrawable stateListDrawable;
    public UnderLineBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.underLineBtn);
        stateListDrawable = (StateListDrawable) array.getDrawable(R.styleable.underLineBtn_isChecked);
        lineHeight = array.getDimension(R.styleable.underLineBtn_lineHeight, 1);
        lineWeight = array.getDimension(R.styleable.underLineBtn_lineWight, LayoutParams.MATCH_PARENT);
        textSize = array.getDimensionPixelSize(R.styleable.underLineBtn_textSize, 14);
        unCheckedColor = array.getColor(R.styleable.underLineBtn_unCheckedColor, Color.WHITE);
        checkedColor = array.getColor(R.styleable.underLineBtn_checkedColor, Color.BLUE);
        isChecked = array.getBoolean(R.styleable.underLineBtn_isChecked, false);
        text = array.getString(R.styleable.underLineBtn_text);
        array.recycle();


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

        textViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(textView, textViewParams);

        viewParams = new LayoutParams((int) lineWeight, (int) lineHeight);
        viewParams.addRule(RelativeLayout.BELOW, textView.getId());
        addView(view, viewParams);
    }

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(stateListDrawable != null){
            if(isChecked){
                textView.setText("111");
            }else{
                textView.setText("222");
            }
        }
    }
}
