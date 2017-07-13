package com.seunghoshin.android.zezuhan_1.tablayout;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.seunghoshin.android.zezuhan_1.R;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SugiFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference zezuRef;

    RecyclerView recycler;
    SugiAdapter adapter;
    List<ZezuInfo> data = new ArrayList<>();

    // 프로그래스 다이얼로그
    private ProgressDialog dialog;


    public SugiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_sugi, container, false);

        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("zezu");

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = new SugiAdapter(container.getContext(), data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(container.getContext()));

        //다이얼로그
        dialog = new ProgressDialog(container.getContext());
        dialog.setTitle("로딩중");
        dialog.setMessage("목록을 로딩중입니다... 잠시만 기다려주세요");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        dialog.show();
        loadData();
    }

    // Read DataBase
    // 데이터를 뿌려주는 이벤트 리스너를 달아준다
    private void loadData() {
        Query query = zezuRef.child("서귀포").orderByKey();

        query.addValueEventListener(new ValueEventListener() {

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
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void refreshList(List<ZezuInfo> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

}
