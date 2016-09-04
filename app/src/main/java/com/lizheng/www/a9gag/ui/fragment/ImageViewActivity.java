package com.lizheng.www.a9gag.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lizheng.www.a9gag.R;
import com.lizheng.www.a9gag.data.RequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10648 on 2016/9/4 0004.
 */
public class ImageViewActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);
        ButterKnife.bind(this);
        RequestManager.mRequestQueue.add( new ImageRequest(getIntent().getStringExtra("Image_Url"),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.icon);
            }
        }));
    }
}
