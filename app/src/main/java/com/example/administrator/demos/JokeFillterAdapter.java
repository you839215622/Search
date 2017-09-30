package com.example.administrator.demos;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public abstract class JokeFillterAdapter extends CommonAdapter<TestBean> implements Filterable {
    private List<TestBean> mCppyDatas;
    private static final String TAG = "TEST_SEARCH";
    private MyFilter mFilter;
    private Context mContext;

    public JokeFillterAdapter(Context context, int layoutId, List<TestBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mCppyDatas = datas; // 备份数据
    }


    //当ListView调用setTextFilter()方法的时候，便会调用该方法
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }

        return mFilter;
    }


    //我们需要定义一个过滤器的类来定义过滤规则

    /**
     * 自定义 Filter类
     * <p>
     * 两个方法:
     * performFiltering 定义搜索规则。
     * publishResults 对搜索结果的处理
     */

    class MyFilter extends Filter {
        //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();

            List<TestBean> tempList; // temp中间集合
            Log.i(TAG, "performFiltering: " + charSequence.toString());
            if (TextUtils.isEmpty(charSequence.toString())) {//当过滤的关键字为空的时候，我们则显示所有的数据
                tempList = mCppyDatas;
            } else {//否则把符合条件的数据对象添加到集合中
                tempList = new ArrayList<>();
                for (TestBean recomend : mCppyDatas) {
                    if (recomend.getName().contains(charSequence) || recomend.getUrl().contains(charSequence)) {
                        Log.d(TAG, "performFiltering:" + recomend.toString());
                        tempList.add(recomend);
                    }
                }
            }
            result.values = tempList; //符合搜索的结果的数据集合
            result.count = tempList.size();//符合搜索的结果的数据个数

            return result;
        }

        //在publishResults方法中告诉适配器更新界面
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            int count = filterResults.count;
            Log.d(TAG, "publishResults:" + count);
            mDatas = (ArrayList<TestBean>) filterResults.values;
            if (count > 0) {
                ((MainActivity) mContext).findViewById(R.id.iv).setVisibility(View.GONE);
                notifyDataSetChanged();//通知数据发生了改变
                Log.d(TAG, "publishResults:notifyDataSetChanged");
            } else {
                Log.d(TAG, "publishResults:notifyDataSetInvalidated");
                ((MainActivity) mContext).findViewById(R.id.iv).setVisibility(View.VISIBLE);
                notifyDataSetInvalidated();//通知数据失效
//                Log.d(TAG,"publishResults:notifyDataSetInvalidated");
            }
        }
    }

}
