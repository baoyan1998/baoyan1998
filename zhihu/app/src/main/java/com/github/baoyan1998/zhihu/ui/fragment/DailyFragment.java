package com.github.baoyan1998.zhihu.ui.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.github.baoyan1998.zhihu.R;
import com.github.baoyan1998.zhihu.ui.activity.DetailActivity;
import com.github.baoyan1998.zhihu.ui.adapter.DailyAdapter;
import com.github.baoyan1998.zhihu.ui.model.DailyListBean;
import com.github.baoyan1998.zhihu.ui.model.DetailBean;

import java.io.IOException;
import java.io.InputStream;



public class DailyFragment extends Fragment implements DailyAdapter.onItemClickListener {

private RecyclerView mRecyclerView;

private DailyListBean dailyListBean;


  DailyAdapter dailyAdapter;

    public DailyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseJSON();

        dailyAdapter = new DailyAdapter(dailyListBean,getContext());

        dailyAdapter.setItemClickListener(this);


        View view =  inflater.inflate(R.layout.fragment_daily, container, false);
        mRecyclerView = view.findViewById(R.id.rv_daily_items);
        mRecyclerView.setAdapter(dailyAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        return view;


    }

    public void parseJSON(){
        try {
            InputStream inputStream = getContext().getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
             dailyListBean = JSON.parseObject(text, DailyListBean.class);
            for (int i= 0;i<dailyListBean.getStories().size();i++) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        DailyListBean.StoryBean storyBean = dailyListBean.getStories().get(position);
        intent.putExtra("id",storyBean.getId());
        startActivity(intent);
    }
}
