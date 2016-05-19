package com.custom.activity.resourcedrawable;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.custom.R;

public class ReaourceDrawableActivity extends Activity implements View.OnClickListener {
    private Button levelList, level_1_, level_0, level_5, level_10, level_15, level_20, level_100;
    private Button btn_startTransition, btn_reverseTransition;
    private ImageView img_transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaource_drawable);
        levelList = (Button) findViewById(R.id.levelList);
        level_1_ = (Button) findViewById(R.id.level_1_);
        level_0 = (Button) findViewById(R.id.level_0);
        level_5 = (Button) findViewById(R.id.level_5);
        level_10 = (Button) findViewById(R.id.level_10);
        level_15 = (Button) findViewById(R.id.level_15);
        level_20 = (Button) findViewById(R.id.level_20);
        level_100 = (Button) findViewById(R.id.level_100);
        level_1_.setOnClickListener(this);
        level_0.setOnClickListener(this);
        level_5.setOnClickListener(this);
        level_10.setOnClickListener(this);
        level_15.setOnClickListener(this);
        level_20.setOnClickListener(this);
        level_100.setOnClickListener(this);

        img_transition = (ImageView) findViewById(R.id.img_transition);
        btn_startTransition = (Button) findViewById(R.id.btn_startTransition);
        btn_reverseTransition = (Button) findViewById(R.id.btn_reverseTransition);
        final TransitionDrawable drawable = (TransitionDrawable) img_transition.getDrawable();
        btn_startTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.startTransition(2000);
            }
        });
        btn_reverseTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.reverseTransition(2000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int level=0;
        switch (v.getId()) {
            case R.id.level_1_:
                level = -1;
                break;
            case R.id.level_0:
                level = 0;
                break;
            case R.id.level_5:
                level = 5;
                break;
            case R.id.level_10:
                level = 10;
                break;
            case R.id.level_15:
                level = 15;
                break;
            case R.id.level_20:
                level = 20;
                break;
            case R.id.level_100:
                level = 100;
                break;
        }
        levelList.getBackground().setLevel(level);
    }
}
