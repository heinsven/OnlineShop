package uestc.hxf.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uestc.hxf.shop.R;
import uestc.hxf.shop.bean.ResultBeanData;
import uestc.hxf.shop.utils.Constants;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    public static final int BANNER = 0;//广告幅类型
    public static final int CHANNEL = 1;//频道类型
    public static final int ACT = 2;//活动类型
    public static final int SECKILL = 3;//秒杀类型
    public static final int RECOMMEND = 4;//推荐类型
    public static final int HOT = 5;//热卖类型

    private LayoutInflater mLayoutInflater;//初始化布局
    private ResultBeanData.ResultBean resultBean;//数据
    private Context mContext;

    private int currenType = BANNER;//当前类型


    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //广告福
        if (viewType == BANNER) {
            //创建BannerViewHolder，Banner里面传布局文件
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
            //频道
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
            //活动
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
            //秒杀
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckkill_item, null));
            //推荐
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
            //热卖
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //轮循广告
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
            //频道
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
            //活动
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
            //秒杀
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
            //推荐
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
            //热卖
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * 广告幅
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoEntity> banner_info) {
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            //设置Banner图片数据
            banner.setImages(imagesUrl);
//            , new OnLoadImageListener() {
//                @Override
//                public void OnLoadImage(ImageView view, Object url) {
//                    //联网请求图片-Glide
//                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);
//                }
//            }
            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    //跳转到商品信息界面
                    startGoodsInfoActivity();
                }
            });
        }
    }

    /**
     * 频道
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoEntity> channel_info) {
            //得到数据后，就设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);

            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 活动
     */
    class ActViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoEntity> act_info) {
            //设置间距
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new ScaleInTransformer());
            //有数据后，就设置数据适配器
            act_viewpager.setAdapter(new ActAdapter(mContext, act_info));
        }
    }

    /**
     * 秒杀
     */
    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecycleViewAdapter adapter;

        /**
         * 相差多少时间-毫秒
         */
        private long dt = 0;
        //不断循环
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                //设置具体的时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String time = dateFormat.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                //发送消息，不断减时间
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt <= 0) {
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            //初始化布局控件
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoEntity seckill_info) {
            //得到数据后，就是设置数据（TextView和RecyclerView）的数据
            adapter = new SeckillRecycleViewAdapter(mContext, seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            //设置item的点击事件
            adapter.setOnSeckillRecyclerView(new SeckillRecycleViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀" + position, Toast.LENGTH_SHORT).show();
                    startGoodsInfoActivity();
                }
            });
            //秒杀倒计时-毫秒
            dt = Integer.valueOf(seckill_info.getEnd_time()) - Integer.valueOf(seckill_info.getStart_time());
            //进入后1秒钟就去发送这个消息
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    /**
     * 推荐
     */
    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

            //设置点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    startGoodsInfoActivity();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoEntity> recommend_info) {
            //有数据了之后，就设置适配器
            adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gv_recommend.setAdapter(adapter);
        }
    }

    /**
     * 热门
     */
    class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter adapter;

        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
            //设置item的点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                    startGoodsInfoActivity();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoEntity> hot_info) {
            //有数据后，就设置GridView的适配器
            adapter = new HotGridViewAdapter(mContext, hot_info);
            gv_hot.setAdapter(adapter);
        }
    }


    /**
     * 启动商品信息列表页面
     */
    private void startGoodsInfoActivity() {
//        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
//        mContext.startActivity(intent);
    }

    /**
     * 根据position得到不同的类型
     * @param position
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER: //广告幅
                currenType = BANNER;
                break;
            case CHANNEL: //频道
                currenType = CHANNEL;
                break;
            case ACT: //活动
                currenType = ACT;
                break;
            case SECKILL: //秒杀
                currenType = SECKILL;
                break;
            case RECOMMEND: //推荐
                currenType = RECOMMEND;
                break;
            case HOT: //热卖
                currenType = HOT;
                break;
        }
        return currenType;
    }
}
