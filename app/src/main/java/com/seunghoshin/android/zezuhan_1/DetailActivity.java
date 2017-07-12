package com.seunghoshin.android.zezuhan_1;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    RecyclerView recycler;
    DetailAdapter adapter;
    List<ZezuInfo> data = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference zezuRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액션바 이름바꾸기
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("상세보기");
        setContentView(R.layout.activity_detail);


        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("zezu");

//        // Loader 부분 , 임시데이터 처리
//        ZezuInfo info = new ZezuInfo();
//        info.dtHomeName = "이곳은 dtHomeName";
//        info.dtAdress = "이곳은 주소입니다";
//        info.dtIntro = "이곳은 dtIntro";
//        info.monthPrice = "이곳은 monthPrice";
//        info.dtPreprice = "이곳은 dtPreprice";
//        data.add(info);


        actionBarColor();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapter = new DetailAdapter(this,data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    // Read DataBase
    // 데이터를 뿌려주는 이벤트 리스너를 달아준다
    private void loadData() {
        zezuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                // 해당 데이터를 리스트에 저장한다 . 아래에 있는 변경된데이터
                List<ZezuInfo> list = new ArrayList<ZezuInfo>();
                for (DataSnapshot item : data.getChildren()) {
                    // 파이어베이스 모든 데이터 하나단위를 클래스로 변경해준다
                    ZezuInfo info = item.getValue(ZezuInfo.class);
                    list.add(info);
                }
                refreshList(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
