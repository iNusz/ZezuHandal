package com.seunghoshin.android.zezuhan_1.tablayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seunghoshin.android.zezuhan_1.DetailActivity;
import com.seunghoshin.android.zezuhan_1.R;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SeungHoShin on 2017. 7. 13..
 */

public class DongbuAdapter extends RecyclerView.Adapter<DongbuAdapter.Holder> {

    List<ZezuInfo> datas = new ArrayList<>();
    private LayoutInflater inflater;

    //glide 사용 을 위한 컨텍 스트
    private Context context = null;

    public DongbuAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public DongbuAdapter() {

    }

    public void setData(List<ZezuInfo> jejuList) {
        this.datas = jejuList;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 이게 없으면 위에 null 값이 떠서 앱이 죽는다
        if (context == null) {
            this.context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dongbu, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        ZezuInfo info = datas.get(position);

        holder.setPosition(position);
        holder.dtHomeName.setText(info.getDtHomeName());
        holder.dtAdress.setText(info.getDtAdress());
        holder.monthPrice.setText("한달 " + info.getMonthPrice() + "원");

        //이미지 파일은 Glide를 써야한다 . 그렇지 않으면 직접 Bitmap으로 파싱해야한다

        Glide.with(context)
                .load(info.getFilePath()) //로드할 대상
                .centerCrop()
                .into(holder.imageView);
    }

    class Holder extends RecyclerView.ViewHolder {

        int position;

        @BindView(R.id.dtHomeName)
        TextView dtHomeName;
        @BindView(R.id.dtAdress)
        TextView dtAdress;
        @BindView(R.id.monthPrice)
        TextView monthPrice;
        @BindView(R.id.imageView)
        ImageView imageView;


        public Holder(View v) {
            super(v);

            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("FragPosi", datas.get(position));
                    intent.putExtra("LIST_POSITION", position);
                    intent.putExtra("TAB", "dongbu");
                    v.getContext().startActivity(intent);
                }
            });

        }

        private void setPosition(int position) {
            this.position = position;
        }
    }
}





