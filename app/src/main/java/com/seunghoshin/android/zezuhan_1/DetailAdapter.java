package com.seunghoshin.android.zezuhan_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 10..
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Holder> {

    private List<ZezuInfo> data;
    private LayoutInflater inflater;

    public DetailAdapter(Context context, List<ZezuInfo> data) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detail, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(DetailAdapter.Holder holder, int position) {
        ZezuInfo info = data.get(position);
        holder.dtMainImage.setImageResource(Integer.parseInt(info.dtMainImage));
        holder.dtHomeName.setText(info.dtHomeName);
        holder.dtAdress.setText(info.dtAdress);
        holder.dtIntro.setText(info.dtIntro);
        holder.dtPrice.setText(info.dtPrice);
        holder.dtPreprice.setText(info.dtPreprice);
        holder.dtImageinfo.setText(info.dtImageinfo);
        holder.position = position;

    }

    class Holder extends RecyclerView.ViewHolder {

        private int position;
        private ImageView dtMainImage;
        private TextView dtHomeName;
        private TextView dtAdress;
        private TextView dtIntro;
        private TextView dtPrice;
        private TextView dtPreprice;
        private TextView dtImageinfo;

        public Holder(View v) {
            super(v);
            dtMainImage = (ImageView) v.findViewById(R.id.dtMainImage);
            dtHomeName = (TextView) v.findViewById(R.id.dtHomeName);
            dtAdress = (TextView) v.findViewById(R.id.dtAdress);
            dtIntro = (TextView) v.findViewById(R.id.dtIntro);
            dtPrice = (TextView) v.findViewById(R.id.dtPrice);
            dtPreprice = (TextView) v.findViewById(R.id.dtPreprice);
            dtImageinfo = (TextView) v.findViewById(R.id.dtImageinfo);

        }
    }

}
