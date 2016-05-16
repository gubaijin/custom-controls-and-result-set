package com.custom.controls.button;

import android.content.Context;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Kevin on 2016/5/16.
 */
public class BorderTxt extends RelativeLayout{
    public BorderTxt(Context context) {
        super(context);
    }

    public BorderTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//http://tool.oschina.net/apidocs/apidoc?api=android/reference
        Shape shape = new OvalShape();
        shape.s
    }
}
