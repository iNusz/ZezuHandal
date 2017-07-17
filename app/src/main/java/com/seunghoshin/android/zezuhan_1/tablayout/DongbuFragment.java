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

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DongbuFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference zezuRef;

    RecyclerView recycler;
    DongbuAdapter adapter;

    // 프로그래스 다이얼로그
    private ProgressDialog dialog;


    public DongbuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dongbu, container, false);

        //다이얼로그
        dialog = new ProgressDialog(container.getContext());
        dialog.setTitle("로딩중");
        dialog.setMessage("목록을 로딩중입니다... 잠시만 기다려주세요");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("Jeju-in-one-month");

        ButterKnife.bind(this, view);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        loadData("동부");
        adapter = new DongbuAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(container.getContext()));




        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.show();
//        loadData();

    }

//    // 뒤로가기 할때 무한반복이 걸림으로 풀어준다
//    @Override
//    public void onResume() {
//        super.onResume();
//        dialog.dismiss();
//    }

    private void loadData(final String area){
        List<ZezuInfo> jejuList = null;
        Query query = zezuRef.orderByChild("area").equalTo(area);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ZezuInfo> jejuList = new ArrayList<ZezuInfo>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    try {
                        ZezuInfo info = item.getValue(ZezuInfo.class);
                        jejuList.add(info);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                refreshList(jejuList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // Read DataBase
    // 데이터를 뿌려주는 이벤트 리스너를 달아준다
//    private void loadData() {
//        Query query = zezuRef.child("제주시").orderByKey();
//
//        query.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                data.clear();
//                for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    ZezuInfo info = item.getValue(ZezuInfo.class);
//                    data.add(info);
//                }
//                refreshList(data);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }


    private void refreshList(List<ZezuInfo> jejuList) {
        adapter.setData(jejuList);
        adapter.notifyDataSetChanged();
    }

}