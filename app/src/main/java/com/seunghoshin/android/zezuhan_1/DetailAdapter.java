//package com.seunghoshin.android.zezuhan_1;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by SeungHoShin on 2017. 7. 10..
// */
//
//public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Holder> {
//
//    private List<ZezuInfo> data;
//    private LayoutInflater inflater;
//
//    //glide 사용 을 위한 컨텍 스트
//    private Context context = null;
//
//    public DetailAdapter(Context context, List<ZezuInfo> data) {
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.data = data;
//    }
//
//    public void setData(List<ZezuInfo> data) {
//        this.data = data;
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    @Override
//    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(context == null)
//            context = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detail, parent, false);
//        return new Holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(DetailAdapter.Holder holder, int position) {
//
//        ZezuInfo info = data.get(position);
//
//        holder.dtHomeName.setText(info.dtHomeName);
//        holder.dtAdress.setText(info.dtAdress);
//        holder.dtIntro.setText(info.dtIntro);
//        holder.monthPrice.setText(info.monthPrice);
//        holder.dtPreprice.setText(info.dtPreprice);
//
//        // 글라이드로 뺴야된다
//        Glide.with(context)
//                .load(data.get(position).fileUriString) //로드할 대상
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher) //로드가 안되었을 경우
//                .into(holder.imageView);
//
//        holder.position = position;
//
//    }
//
//    class Holder extends RecyclerView.ViewHolder {
//
//        private int position;
//
//        @BindView(R.id.dtHomeName)
//        TextView dtHomeName;
//        @BindView(R.id.imageView)
//        ImageView imageView;
//        @BindView(R.id.dtAdress)
//        TextView dtAdress;
//        @BindView(R.id.dtIntro)
//        TextView dtIntro;
//        @BindView(R.id.monthPrice)
//        TextView monthPrice;
//        @BindView(R.id.dtPreprice)
//        TextView dtPreprice;
//
//
//        public Holder(View v) {
//            super(v);
//            ButterKnife.bind(this, v);
//        }
//    }
//
//}
