package com.shakespace.dailyreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.bean.ZhihuStory;

import java.util.List;

/**
 * Created by shakespace on 2018/3/26.
 */

public class ZhihuDailyAdapter extends RecyclerView.Adapter<ZhihuDailyAdapter.ZhihuDailyHolder> {

    private Context mContext;
    private List<ZhihuStory> mList;

    public ZhihuDailyAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ZhihuDailyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_recycler_item,parent,false);
        ZhihuDailyHolder holder = new ZhihuDailyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ZhihuDailyHolder holder, int position) {
        ZhihuStory story = mList.get(position);
        holder.mTvTitle.setText(story.getTitle());
        Glide.with(mContext)
                .load(story.getImages().get(0))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)    // 缓存策略
                .centerCrop()
                .into(holder.mIvCover);
    }

    @Override
    public int getItemCount() {
        return  mList.isEmpty() ? 0 : mList.size();
    }

    public class ZhihuDailyHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        ImageView mIvCover;

        public ZhihuDailyHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvCover = itemView.findViewById(R.id.iv_cover);
        }
    }
}
