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

public class ChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ChannelInfoEntity> datas;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoEntity> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
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
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_channel = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_channel = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoEntity channelInfoEntity = datas.get(position);
        //根据Glide设置图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + channelInfoEntity.getImage()).into(viewHolder.iv_channel);
        viewHolder.tv_channel.setText(channelInfoEntity.getChannel_name());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_channel;
        TextView tv_channel;
    }

}
