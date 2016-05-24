package com.custom.controls.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
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
public class BorderTxt extends RelativeLayout implements View.OnTouchListener {
    //不可以点击时，初始颜色
    private int CHECKABLE_FALSE_DEFAULT = getResources().getColor(R.color.gray);
    //可以点击时，初始颜色
    private int CHECKABLE_TRUE_DEFAULT = getResources().getColor(R.color.border_txt_color_default);
    //可以点击时，选中时文本颜色
    private int CHECKABLE_TRUE_TXT = getResources().getColor(R.color.white);

    //是否可以点击
    private boolean checkable;
    /**
     * checkable=false时：unCheckedColor代表不可点击时的初始颜色;
     * checkable=true时:unCheckedColor代表可点击时的初始颜色；checkedColor表示选中时的文字颜色
     */
    private int unCheckedColor,checkedColor;
    private String text1, text2;//文本内容
    private float textSize1, textSize2;//文字大小
    private TextView txt1, txt2;//文本
    private LayoutParams txt1Params, txt2Params, relParams;//位置属性
    private RelativeLayout relativeLayout;//中间两行文本防止在此布局中，便于定位
    private PointF pointF;//存储手指按下的位置
    private Context mContext;//上下文

    public BorderTxt(Context context) {
        super(context);
    }

    public BorderTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BorderTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        pointF = new PointF();//初始化手指按下时的坐标对象
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.borderTxt);
        unCheckedColor = array.getColor(R.styleable.borderTxt_bt_unCheckedColor, CHECKABLE_TRUE_DEFAULT);
        checkedColor = array.getColor(R.styleable.borderTxt_bt_checkedColor, CHECKABLE_TRUE_TXT);
        checkable = array.getBoolean(R.styleable.borderTxt_bt_checkable, false);
        text1 = array.getString(R.styleable.borderTxt_bt_text1);
        text2 = array.getString(R.styleable.borderTxt_bt_text2);
        textSize1 = array.getDimensionPixelSize(R.styleable.borderTxt_bt_textSize1, 14);
        textSize2 = array.getDimensionPixelSize(R.styleable.borderTxt_bt_textSize2, 10);
        //自定义属性获取完之后，及时回收
        array.recycle();
        //初始化背景、文字颜色及事件绑定
        initUi();
        //控件位置布局
        relativeLayout = new RelativeLayout(mContext);
        relParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relParams.addRule(CENTER_IN_PARENT);
        relativeLayout.setLayoutParams(relParams);

        txt1 = new TextView(mContext);
        txt1.setId(StringUtil.generateViewId());
        txt1.setText(text1);
        txt1.setTextColor(unCheckedColor);
        txt1.setTextSize(textSize1);
        //当第二行文本内容为空时，第一行居中显示，否则，将整体居中
        if(StringUtil.isEmpty(text2)){
            txt1Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            txt1Params.addRule(RelativeLayout.CENTER_IN_PARENT);
        }else{
            txt1Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            txt1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            txt1.setGravity(Gravity.CENTER);

            txt2 = new TextView(mContext);
            txt2.setText(text2);
            txt2.setTextSize(textSize2);
            txt2.setTextColor(unCheckedColor);

            txt2Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            txt2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            txt2Params.addRule(BELOW, txt1.getId());
            relativeLayout.addView(txt2, txt2Params);
        }
        //初始化文本颜色
        setTxtColor(unCheckedColor);
        relativeLayout.addView(txt1, txt1Params);
        addView(relativeLayout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        /**
         * 可以点击时，手指第一次按下时存储此时的位置坐标；
         * 当手指抬起或者移动与按下时的坐标任意方向上的长度大于三分之一控件长度时，
         * 控件的press效果失效，并改变文本颜色。
         */
        if(checkable){
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                pointF.x = event.getX();
                pointF.y = event.getY();
                //按下
                setTxtColor(checkedColor);
            } else if (event.getAction() ==  MotionEvent.ACTION_UP
                    || getWidth() / 3 - Math.abs(event.getX() - pointF.x) < 0
                    || getHeight() / 3 - Math.abs(event.getY() - pointF.y) < 0
                    ) {
                //抬起
                this.setPressed(false);
                setTxtColor(unCheckedColor);
            }
        }
        return false;
    }

    /**
     * 设置文本颜色
     * @param color
     */
    public void setTxtColor(int color){
        txt1.setTextColor(color);
        if(!StringUtil.isEmpty(text2)){
            txt2.setTextColor(color);
        }
    }

    /**
     * 得到是否可以点击
     * @return
     */
    public boolean isCheckable() {
        return checkable;
    }

    /**
     * 设置是否可以点击
     * 并刷新控件状态
     * @param checkable
     */
    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
        refreshDrawableState();//执行改变逻辑
        setTxtColor(unCheckedColor);//初始化文字颜色
    }

    /**
     * 执行控件改变
     */
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        //改变时的切换逻辑
        initUi();
    }

    /**
     * 初始化背景、文字颜色及事件绑定
     */
    private void initUi() {
        if(checkable){
            unCheckedColor = CHECKABLE_TRUE_DEFAULT;
            checkedColor = CHECKABLE_TRUE_TXT;
            setBackgroundResource(R.drawable.custom_border_txt_bg);
            setOnTouchListener(this);
            /**
             * 绑定点击事件监听，否则点击时无法切换背景，selector效果无效;
             * 我的理解就是让空间拥有获得焦点的能力，尽管什么都没做
             */
            this.setOnClickListener(null);
        }else{
            unCheckedColor = CHECKABLE_FALSE_DEFAULT;
            setBackgroundResource(R.drawable.custom_border_txt_bg2);
        }
    }
}
