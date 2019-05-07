package uestc.hxf.shop.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import uestc.hxf.shop.R;
import uestc.hxf.shop.adapter.HomeFragmentAdapter;
import uestc.hxf.shop.base.BaseFragment;
import uestc.hxf.shop.bean.ResultBeanData;
import uestc.hxf.shop.utils.Constants;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private final static String TAG = HomeFragment.class.getSimpleName();

    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageView ib_top;
    private HomeFragmentAdapter adapter;
    //返回的数据
    private ResultBeanData.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG, "主页面的Fragment的UI被初始化了");

        View view = View.inflate(mContext, R.layout.fragment_home, null);
        //初始化布局控件
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);

        //设置点击事件
        ib_top.setOnClickListener(this);
        tv_search_home.setOnClickListener(this);
        tv_message_home.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页面的Fragment的数据被初始化了");

        //联网请求首页数据
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败=="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功=="+response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        //使用FastJson去解析Json数据，将json字符串转换成一个ResultBeanData对象
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());

        if (resultBean != null){
            //有数据就设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rv_home.setAdapter(adapter);

            GridLayoutManager manager = new GridLayoutManager(mContext,1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 3){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else {
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            //设置布局管理者
            rv_home.setLayoutManager(manager);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_top: //置顶的监听
                rv_home.scrollToPosition(0);
                break;
            case R.id.tv_search_home:  //搜索的监听
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home: //消息监听
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
