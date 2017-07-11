package com.seunghoshin.android.zezuhan_1;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    RecyclerView recycler;
    DetailAdapter adapter;
    List<ZezuInfo> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액션바 이름바꾸기
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("상세보기");
        setContentView(R.layout.activity_detail);


        // Loader 부분
        ZezuInfo info = new ZezuInfo();
        info.dtMainImage = String.valueOf(R.mipmap.ic_launcher);
        info.dtHomeName = "이곳은 dtHomeName";
        info.dtAdress = "이곳은 주소입니다";
        info.dtIntro = "이곳은 dtIntro";
        info.dtPrice = "이곳은 dtPrice";
        info.dtPreprice = "이곳은 dtPreprice";
        info.dtImageinfo = "이곳은 dtImageinfo";
        data.add(info);


        actionBarColor();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapter = new DetailAdapter(this,data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        refreshList(data);

    }

    private void actionBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorActionbar));
        }
    }

    public void refreshList(List<ZezuInfo> data){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

}
