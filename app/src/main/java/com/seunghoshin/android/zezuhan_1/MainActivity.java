package com.seunghoshin.android.zezuhan_1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seunghoshin.android.zezuhan_1.permission.PermissionControl;
import com.seunghoshin.android.zezuhan_1.tablayout.DongbuFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.SubuFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.SugiFragment;
import com.seunghoshin.android.zezuhan_1.tablayout.ZezuFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PermissionControl.CallBack {

    ViewPager pager;
    TabLayout tab;
    Spinner spinner;
    Fragment zezu, sugi, subu, dongbu;

    LocationManager manager;
    android.support.v7.app.ActionBar zezuActionBar;

    static final String TAG = "MainActivity";
    FirebaseDatabase database;
    DatabaseReference myRef;


    public LocationManager getLocationManager() {
        return manager;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 파이어베이스 데이터베이스 연결
        database = FirebaseDatabase.getInstance();
        // 레퍼런스를 가져옴
        myRef = database.getReference("zezu");
        // 값 세팅 
//        myRef.setValue("Hello,World2");
        initData();


        /**
         * 메인화면 제주한달 이미지 넣기
         */
        zezuActionBar = getSupportActionBar();
        zezuActionBar.setLogo(R.drawable.group);
        zezuActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);


        //Context에 있는 Location상수라는걸 알려주는 것이다
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        actionBarColor();


        // Spinner
        String prio[] = {"체험우선", "레저우선", "관광우선", "휴식우선"};

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter spa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, prio);
        spinner.setAdapter(spa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // 1. ViewPager 위젯 연결
        pager = (ViewPager) findViewById(R.id.pager);
        // 1.1 TabLayout 위젯 연결
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("제주시"));
        tab.addTab(tab.newTab().setText("서귀포"));
        tab.addTab(tab.newTab().setText("서부"));
        tab.addTab(tab.newTab().setText("동부"));

        // 2. 프래그먼트 생성
        zezu = new ZezuFragment();
        sugi = new SugiFragment();
        subu = new SubuFragment();
        dongbu = new DongbuFragment();

        // 3. 프래그먼트를 datas 저장소에 담은 후

        List<Fragment> datas = new ArrayList<>();
        datas.add(zezu);
        datas.add(sugi);
        datas.add(subu);
        datas.add(dongbu);

        // 4. 프래그먼트 매니저와 함께 아답터에 전달
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);

        // 5. 아답터를 페이저 위젯에 연결
        pager.setAdapter(adapter);

        // 6. 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // 7. 탭이 변경되었을때 페이저를 변경해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));


    }

    private void actionBarColor() {
        // Action Bar의 색상을 빨간색으로 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorActionbar));
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // 액션바에 필터로 이동하는 버튼 달기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 액션바에서 필터를 누르면 화면 전환
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void init() {

    }


    private void initData() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                // todo dataSnapshot.getKey --> 키값 위에는 value임
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
