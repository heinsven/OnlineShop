package uestc.hxf.shop.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import uestc.hxf.shop.bean.ResultBeanData;
import uestc.hxf.shop.utils.Constants;

public class ActAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ActInfoEntity> act_info;

    public ActAdapter(Context mContext, List<ResultBeanData.ResultBean.ActInfoEntity> act_info) {
        this.mContext = mContext;
        this.act_info = act_info;
    }

    @Override
    public int getCount() {
        return act_info.size();
    }

    /**
     * @param view   页面
     * @param object instantiateItem方法返回的值
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * @param container ViewPager
     * @param position  对应页面的位置
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //实例化ImageView
        ImageView imageView = new ImageView(mContext);
        //设置ImageView的拉伸
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //根据Glide设置图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
        //添加到容器中
        container.addView(imageView);

        //设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
