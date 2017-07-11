package com.seunghoshin.android.zezuhan_1.tablayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seunghoshin.android.zezuhan_1.R;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 11..
 */

public class ZezuAdapter extends RecyclerView.Adapter<ZezuAdapter.Holder> {

    private List<ZezuInfo> data;
    private LayoutInflater inflater;

    public ZezuAdapter(Context context, List<ZezuInfo> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    public void setData(List<ZezuInfo> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_zezu, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        ZezuInfo info = data.get(position);
        holder.dtHomeName.setText(info.dtHomeName);
        holder.dtAdress.setText(info.dtAdress);
        holder.dtPrice.setText(info.dtPrice);
        holder.position = position;

    }


    class Holder extends RecyclerView.ViewHolder {

        private int position;

        private TextView dtHomeName;
        private TextView dtAdress;
        private TextView dtPrice;

        public Holder(View v) {
            super(v);
            dtHomeName = (TextView) v.findViewById(R.id.dtHomeName);
            dtAdress = (TextView) v.findViewById(R.id.dtAdress);
            dtPrice = (TextView) v.findViewById(R.id.dtPrice);

        }
    }


}
