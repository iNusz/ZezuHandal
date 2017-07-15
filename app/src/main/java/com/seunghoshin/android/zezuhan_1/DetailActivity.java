package com.seunghoshin.android.zezuhan_1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;
import com.seunghoshin.android.zezuhan_1.tablayout.DongbuFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.SugiFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.ZezuFragment;

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

    //todo 버튼을 누르면 자동으로 전화연결이 되게끔 DB에서 phoneNum을 받아와서 연결시켜주자

    // Detail Header Infomation
    TextView arrayInfo[] = new TextView[6];

    // Detail Header Facility
    TextView arrayFacility[] = new TextView[12];

    // Detail Header Safety
    TextView arraySafety[] = new TextView[6];

    //todo asdasasd
//    static List<String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액션바 이름바꾸기
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("상세보기");
        setContentView(R.layout.activity_detail);
        actionBarColor();
        ButterKnife.bind(this);
        setWidgetData();
        setData();

    }

    @Override
    protected void onPause() {
        super.onPause();
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
            setWidgetDetailData(info);
        }
    }

public void getDetailData(){

}


    public void setDetailData(ZezuInfo info) {
        // 이미지 세팅
        if (info.fileUriString != null && !"".equals(info.fileUriString)) {
            Glide.with(this)
                    .load(info.fileUriString)
                    .centerCrop()
                    .into(imageView);
        }

        dtHomeName.setText(info.getDtHomeName());
        dtAdress.setText(info.dtAdress);
        dtIntro.setText(info.dtIntro);
        monthPrice.setText("한달 " + info.monthPrice);
        dtPreprice.setText("계약금 " + info.dtPreprice);
        dtDeposit.setText("보증금 " + info.dtDeposit);
    }


    public void setWidgetData() {

        // Detail info 데이터 가져오기

        for (int i = 0; i < arrayInfo.length; i++) {
            int resid = getResources().getIdentifier("arrayInfo" + i ,"id", this.getPackageName());
            arrayInfo[i] = (TextView)findViewById(resid);
        }


        // Detail facility 데이터 가져오기

        for (int i = 0; i < arrayFacility.length; i++) {
            int resid = getResources().getIdentifier("arrayFacility" + i, "id", this.getPackageName());
            arrayFacility[i] = (TextView)findViewById(resid);
        }

        // Detail Safety 데이터 가져오기
        for (int i = 0; i < arraySafety.length; i++) {
            int resid = getResources().getIdentifier("arraySafety" + i, "id", this.getPackageName());
            arraySafety[i] = (TextView)findViewById(resid);
        }


    }

    public void setWidgetDetailData(ZezuInfo info) {

        // Detail info 값넣기
        arrayInfo[0].setText(info.houseStyle);
        arrayInfo[1].setText("인원 " + info.numPeople);
        arrayInfo[2].setText("침실 " + info.numRoom);
        arrayInfo[3].setText("침대 " + info.numBed);
        arrayInfo[4].setText("욕실 " + info.numShower);
        arrayInfo[5].setText("주차 " + info.numParking);


        // Detail facility invisible 설정
        int facilitySize = info.getmFacility().size();
        int facilityCount;

        if (facilitySize <= 4) {
            facilityCount = 4;
        } else if (facilitySize <= 8) {
            facilityCount = 8;
        } else {
            facilityCount = 12;
        }

        for (int i = 0; i < facilityCount; i++) {
            arrayFacility[i].setVisibility(View.VISIBLE);
        }

        int Ftemp_count = 0;
        for (String item : info.getmFacility()) {
            arrayFacility[Ftemp_count].setText(item);
            Ftemp_count++;
        }


        // Detail Safety invisible 설정

        int safetySize = info.getmSafety().size();
        int safetyCount;

        if (safetySize <= 4) {
            safetyCount = 4;
        } else {
            safetyCount = 6;
        }

        for (int i = 0; i < safetyCount; i++) {
            arraySafety[i].setVisibility(View.VISIBLE);
        }

        int Stemp_count = 0;
        for (String items : info.getmSafety()) {
            arraySafety[Stemp_count].setText(items);
            Stemp_count++;
        }


    }


    private void actionBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorActionbar));
        }
    }


}
