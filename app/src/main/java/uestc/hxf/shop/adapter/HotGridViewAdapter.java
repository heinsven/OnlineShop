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

public class HotGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.HotInfoEntity> datas;

    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoEntity> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到相对应的数据
        ResultBeanData.ResultBean.HotInfoEntity hotInfoEntity = datas.get(position);
        //设置图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + hotInfoEntity.getFigure()).into(viewHolder.iv_hot);
        //设置名称
        viewHolder.tv_name.setText(hotInfoEntity.getName());
        //设置价格
        viewHolder.tv_price.setText("￥" + hotInfoEntity.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
