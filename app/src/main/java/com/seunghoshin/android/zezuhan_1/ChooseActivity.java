package com.seunghoshin.android.zezuhan_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.seunghoshin.android.zezuhan_1.permission.PermissionControl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends AppCompatActivity implements PermissionControl.CallBack{


    @BindView(R.id.btnMain)
    TextView btnMain;
    @BindView(R.id.btnWrite)
    TextView btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        PermissionControl.checkVersion(this);
        ButterKnife.bind(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.onResult(this, requestCode, grantResults);
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

    @Override
    public void init() {

    }
}
