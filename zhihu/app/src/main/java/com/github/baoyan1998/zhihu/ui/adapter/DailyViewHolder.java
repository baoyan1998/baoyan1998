package com.github.baoyan1998.zhihu.ui.adapter;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baoyan1998.zhihu.R;

/**
 * Created by lenovo on 2018/1/15.
 */

public class DailyViewHolder extends RecyclerView.ViewHolder {

    TextView mTvtitle;
    ImageView mIvImage;

    public DailyViewHolder(View itemView) {
        super(itemView);
        mTvtitle = itemView.findViewById(R.id.tv_daily_item_title);
        mIvImage = itemView.findViewById(R.id.iv_daily_item_image);
    }


}
