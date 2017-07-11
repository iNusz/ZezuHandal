package com.seunghoshin.android.zezuhan_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference zezuRef;

    @BindView(R.id.editHome)
    EditText editHome;
    @BindView(R.id.editAdress)
    EditText editAdress;
    @BindView(R.id.editPrice)
    EditText editPrice;
    @BindView(R.id.btnPost)
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("zezu");

        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);

        zezuRef.setValue("Hello, World!");

    }

    //데이터 전송
    public void postData(View view){
        String dtHomeName = editHome.getText().toString();
        String dtAdress = editAdress.getText().toString();
        String dtPrice = editPrice.getText().toString();

        // 파이어 베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
        ZezuInfo info = new ZezuInfo(dtHomeName,dtAdress,dtPrice);
        // 2. 입력할 데이터의 키 생성
        String zezuKey = zezuRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. key아래쪽으로 한칸 내려간 노드에서 작업을 할 것이다 / 생성된 키를 래퍼런스로 데이터를 입력
        zezuRef.child(zezuKey).setValue(info);

    }

}
