package com.custom.activity.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.custom.R;
import com.custom.controls.button.BorderTxt;

public class BorderTxtActivity extends Activity {
    private TextView bbbb;
    private Button btn_change_border_txt;
    private BorderTxt border_txt_flow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_border_txt);

        bbbb = (TextView) findViewById(R.id.bbbb);
        bbbb.setOnClickListener(null);
        border_txt_flow = (BorderTxt) findViewById(R.id.border_txt_flow);
        btn_change_border_txt = (Button) findViewById(R.id.btn_change_border_txt);
        btn_change_border_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheckable = border_txt_flow.isCheckable();
                border_txt_flow.setCheckable(!isCheckable);
            }
        });
    }
}
