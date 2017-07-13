package com.seunghoshin.android.zezuhan_1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;
import com.seunghoshin.android.zezuhan_1.tablayout.DongbuFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.SugiFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.ZezuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.dtHomeName)
    TextView dtHomeName;
    @BindView(R.id.dtAdress)
    TextView dtAdress;
    @BindView(R.id.dtIntro)
    TextView dtIntro;
    @BindView(R.id.monthPrice)
    TextView monthPrice;
    @BindView(R.id.dtPreprice)
    TextView dtPreprice;
    @BindView(R.id.dtDeposit)
    TextView dtDeposit;

    List <ZezuInfo> datatemp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액션바 이름바꾸기
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("상세보기");
        setContentView(R.layout.activity_detail);
        actionBarColor();
        ButterKnife.bind(this);
        setData();

    }


    @Override
    protected void onPause() {
        super.onPause();
        datatemp.clear();
    }

    public void setData() {

        String temp = "";
        Intent intent = getIntent();
        int position = intent.getIntExtra("LIST_POSITION", -1); // -1은 없는값이니 , null체크를 한다
        Log.e("Position", "THis is position=========" + position);
        temp = intent.getStringExtra("TAB");
        Log.e("temp", "This is temp=== " + temp);

        if (position > -1) {
            ZezuInfo info = null;         //Data.list.get(position);

            switch (temp) {
                case "zezu":
                    info = ZezuFragment.data.get(position);
                    break;
                case "sugi":
                    info = SugiFragment.data.get(position);
                    break;
//                case "subu":
//                    setDetailData(info);
//                    break;
                case "dongbu":
                    info = DongbuFragment.data.get(position);
                    break;
            }
            setDetailData(info);
        }
    }

    public void setDetailData(ZezuInfo info) {
        // 이미지 세팅
        if (info.fileUriString != null && !"".equals(info.fileUriString)) {
            Glide.with(this)
                    .load(info.fileUriString)
                    .into(imageView);
        }

        dtHomeName.setText(info.dtHomeName);
        dtAdress.setText(info.dtAdress);
        dtIntro.setText(info.dtIntro);
        monthPrice.setText(info.monthPrice);
        dtPreprice.setText(info.dtPreprice);
        dtDeposit.setText(info.dtDeposit);
    }

    private void actionBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorActionbar));
        }
    }


}
