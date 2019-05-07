package uestc.hxf.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import uestc.hxf.shop.R;
import uestc.hxf.shop.bean.ResultBeanData;
import uestc.hxf.shop.utils.Constants;

public class SeckillRecycleViewAdapter extends RecyclerView.Adapter<SeckillRecycleViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoEntity.ListEntity> list;

    public SeckillRecycleViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoEntity.ListEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(itemView);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoEntity.ListEntity listEntity = list.get(position);
        //绑定数据
        //使用Glide获取到图片数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listEntity.getFigure()).into(holder.iv_figure);
        //获取价钱的数据
        holder.tv_cover_price.setText(listEntity.getCover_price());
        //获取降价的数据
        holder.tv_origin_price.setText(listEntity.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  Toast.makeText(mContext,"秒杀=="+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if (onSeckillRecyclerView != null) {
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnSeckillRecyclerView {
        //当某条被点击的时候回调
        public void onItemClick(int position);
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    /**
     * 设置item的监听器
     *
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }

}
