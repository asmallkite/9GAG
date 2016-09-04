package com.lizheng.www.a9gag.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lizheng.www.a9gag.R;
import com.lizheng.www.a9gag.data.ImageCacheManager;
import com.lizheng.www.a9gag.model.Feed;
import com.lizheng.www.a9gag.util.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 10648 on 2016/8/31 0031.
 */
public class FeedsAdapter extends BaseAbstractRecycleCursorAdapter<RecyclerView.ViewHolder> {
    private static final int[] COLORS = {R.color.holo_blue_light, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_purple_light, R.color.holo_red_light};

    private static final int IMAGE_MAX_HEIGHT = 240;

    private final LayoutInflater mLayoutInflater;

    Drawable mDefaultImageDrawable;

    protected Context sContext;

    FeedViewHolder feedViewHolder;
    @BindView(R.id._rv_list_item)
    LinearLayout RvListItem;


    public FeedsAdapter(Context context) {
        super(context, null);
        mLayoutInflater = LayoutInflater.from(context);
        sContext = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {

        feedViewHolder = (FeedViewHolder) holder;
//        if (feedViewHolder != null) {
//            feedViewHolder.imageRequest.cancelRequest();
//        }

        Feed feed = Feed.fromCursor(cursor);
        mDefaultImageDrawable = new ColorDrawable(ContextCompat.getColor(sContext, COLORS[cursor.getPosition() % COLORS.length]));
        feedViewHolder.imageRequest = ImageCacheManager.loadImage(feed.images.normal, ImageCacheManager
                .getImageListener(feedViewHolder.ivNormal, mDefaultImageDrawable, mDefaultImageDrawable), 0, DensityUtils.dip2px(sContext, IMAGE_MAX_HEIGHT));


        feedViewHolder.tvCaption.setText(feed.caption);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.listitem_feed, parent, false);
        feedViewHolder = new FeedViewHolder(view, this);
        return feedViewHolder;

    }




    public static class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView tvCaption;

        ImageView ivNormal;

        FeedsAdapter mAdapter;

        public ImageLoader.ImageContainer imageRequest = null;


        public FeedViewHolder(View itemView, FeedsAdapter mAdapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mAdapter = mAdapter;
            tvCaption = (TextView) itemView.findViewById(R.id.tv_caption);
            ivNormal = (ImageView) itemView.findViewById(R.id.iv_normal);
        }
        @OnClick(R.id._rv_list_item)
        public void onClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.baidu.com"));
            mAdapter.mContext.startActivity(intent);
        }


    }
}
