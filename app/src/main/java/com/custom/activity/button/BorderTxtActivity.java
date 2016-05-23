package com.custom.activity.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.custom.R;

public class BorderTxtActivity extends Activity {
    private TextView bbbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_border_txt);

        bbbb = (TextView) findViewById(R.id.bbbb);
        bbbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bbbb.isFocused()){
                    //已获得焦点
                }else{
                    bbbb.requestFocus();//获得焦点
                }
            }
        });
    }
}
