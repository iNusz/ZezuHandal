package com.seunghoshin.android.zezuhan_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends AppCompatActivity {


    @BindView(R.id.btnMain)
    TextView btnMain;
    @BindView(R.id.btnWrite)
    TextView btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnMain)
    public void GotoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnWrite)
    public void GotoWrite(){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }

}
