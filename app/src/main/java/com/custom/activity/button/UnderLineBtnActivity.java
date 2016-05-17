package com.custom.activity.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.custom.R;
import com.custom.controls.button.UnderLineBtn;

public class UnderLineBtnActivity extends Activity {
    private UnderLineBtn two_btn_1, two_btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_line_btn);
        two_btn_1 = (UnderLineBtn) findViewById(R.id.under_line_btn_2_1);
        two_btn_2 = (UnderLineBtn) findViewById(R.id.under_line_btn_2_2);
        two_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "左侧被点击", Toast.LENGTH_SHORT).show();
                two_btn_1.setChecked(true);
                two_btn_2.setChecked(false);
            }
        });
        two_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "右侧被点击", Toast.LENGTH_SHORT).show();
                two_btn_1.setChecked(false);
                two_btn_2.setChecked(true);
            }
        });
    }
}
