package com.seunghoshin.android.zezuhan_1.tablayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seunghoshin.android.zezuhan_1.R;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZezuFragment extends Fragment {

    RecyclerView recycler;
    ZezuAdapter adapter;
    List<ZezuInfo> data = new ArrayList<>();


    public ZezuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zezu, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = new ZezuAdapter(container.getContext(),data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(container.getContext()));


        // Loader 부분
        ZezuInfo info = new ZezuInfo();
        info.dtHomeName = "이곳은 dtHomeName";
        info.dtAdress = "이곳은 주소입니다";
        info.dtPrice = "이곳은 dtPrice";
        data.add(info);


        adapter.setData(data);
        adapter.notifyDataSetChanged();

        return view;

    }


}
