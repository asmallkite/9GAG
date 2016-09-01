package com.lizheng.www.a9gag.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lizheng.www.a9gag.R;
import com.lizheng.www.a9gag.model.Feed;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 10648 on 2016/8/31 0031.
 */
public class FeedsAdapter extends BaseAbstractRecycleCursorAdapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;


    public FeedsAdapter(Context context) {
        super(context, null);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {

        FeedViewHolder feedViewHolder = (FeedViewHolder) holder;
        if (feedViewHolder != null) {
            feedViewHolder.imageRequest.cancelRequest();
        }

        Feed feed = Feed.fromCursor(cursor);

//        feedViewHolder.tvCaption.setText(feed.caption);
//        feedViewHolder.imageRequest

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.listitem_feed, parent, false);
        return new FeedViewHolder(view, this);

    }



    public static class FeedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_caption)
        TextView tvCaption;
        @BindView(R.id.iv_normal)
        ImageView ivNormal;

        FeedsAdapter mAdapter;

        public ImageLoader.ImageContainer imageRequest;


        public FeedViewHolder(View itemView, FeedsAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.tv_caption, R.id.iv_normal})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_caption:
                    break;
                case R.id.iv_normal:
                    break;
            }
        }
    }
}
