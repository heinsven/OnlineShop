package uestc.hxf.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import uestc.hxf.shop.R;
import uestc.hxf.shop.bean.ResultBeanData;
import uestc.hxf.shop.utils.Constants;

public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.RecommendInfoEntity> datas;

    public RecommendGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoEntity> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = (ImageView) view.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoEntity recommendInfoBean = datas.get(position);
        //设置网络图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + recommendInfoBean.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("¥" + recommendInfoBean.getCover_price());
        return view;
    }

    static class ViewHolder {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
