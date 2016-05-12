package com.custom.activity.top;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.custom.R;
import com.custom.controls.top.TopBar;

public class TopBarActivity extends AppCompatActivity {

    private TopBar topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setCenterText("代码中设置的title");
    }
}
