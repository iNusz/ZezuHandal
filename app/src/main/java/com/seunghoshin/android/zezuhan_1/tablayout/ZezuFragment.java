package com.seunghoshin.android.zezuhan_1.tablayout;


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
import com.google.firebase.database.ValueEventListener;
import com.seunghoshin.android.zezuhan_1.Data;
import com.seunghoshin.android.zezuhan_1.R;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZezuFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference zezuRef;

    RecyclerView recycler;
    ZezuAdapter adapter;
    List<ZezuInfo> data = new ArrayList<>();

//    @BindView(R.id.imageView)
//    ImageView imageView;
//    @BindView(R.id.dtHomeName)
//    TextView dtHomeName;
//    @BindView(R.id.dtAdress)
//    TextView dtAdress;
//    @BindView(R.id.dtPrice)
//    TextView dtPrice;


    public ZezuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zezu, container, false);

        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("zezu");

        ButterKnife.bind(this,view);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = new ZezuAdapter(container.getContext(), data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(container.getContext()));


//        // 임시 Loader 부분
//        ZezuInfo info = new ZezuInfo();
//        info.dtHomeName = "이곳은 dtHomeName";
//        info.dtAdress = "이곳은 주소입니다";
//        info.dtPrice = "이곳은 dtPrice";
//        data.add(info);



        loadData();

        return view;

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
                }
                refreshList(Data.list);
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
