package com.lizheng.www.a9gag;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lizheng.www.a9gag.adapter.FeedsAdapter;
import com.lizheng.www.a9gag.api.GagApi;
import com.lizheng.www.a9gag.dao.FeedsDataHelper;
import com.lizheng.www.a9gag.data.GsonRequest;
import com.lizheng.www.a9gag.model.Category;
import com.lizheng.www.a9gag.model.Feed;
import com.lizheng.www.a9gag.ui.fragment.BaseFragment;
import com.lizheng.www.a9gag.util.TaskUtils;
import com.lizheng.www.a9gag.util.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String EXTRA_CATEGORY = "extra_category";
    @BindView(R.id.grid_view)
    RecyclerView gridView;

    private Category mCategory;
    private FeedsDataHelper mDataHelper;
    private FeedsAdapter mAdapter;

    private String mPage = "0";



    public FeedsFragment() {
        // Required empty public constructor
    }

    /**
     * * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category
     * @return A new instance of fragment FeedsFragment.
     */
    public static FeedsFragment newInstance(Category category) {
        FeedsFragment fragment = new FeedsFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_CATEGORY, category.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mCategory = Category.valueOf(getArguments().getString(EXTRA_CATEGORY));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_feeds, container, false);

        mDataHelper = new FeedsDataHelper(APP.getsContext(), mCategory);

        ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FeedsAdapter(getActivity());
        gridView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @OnClick(R.id.grid_view)
    public void onClick() {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mDataHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        if (data != null && data.getCount() == 0) {
            loadFirst();
        }

    }

    private void loadFirst() {
        mPage = "0";
        loadData(mPage);
    }

    private void loadData(String next) {
        //TODO:关于刷新的 相关代码

        executeRequest(new GsonRequest(String.format(GagApi.LIST, mCategory.name(), next),
                Feed.FeedRequestData.class, responseListener(), errorListener()));
    }

    private  Response.Listener<Feed.FeedRequestData> responseListener() {
        final boolean isRefreshFromTop = ("0".equals(mPage));
        return new Response.Listener<Feed.FeedRequestData>() {

            @Override
            public void onResponse(final Feed.FeedRequestData response) {
                TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... params) {
                        if (isRefreshFromTop) {
                            mDataHelper.deleteAll();
                        }
                        mPage = response.getPage();
                        ArrayList<Feed> feeds = response.data;
                        mDataHelper.bulkInsert(feeds);
                        return null;
                    }

//                    @Override
//                    protected void onPostExecute(Object o) {
//                        super.onPostExecute(o);
//                        if (isRefreshFromTop) {
//                            setRefreshing(false);
//                        } else {
//                            gridView.setState(LoadingFooter.State.Idle);
//                        }
//                    }
                });
            }
        };
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showShort("R.string.loading_failed");
//                setRefreshing(false);
//                gridView.setState(LoadingFooter.State.Idle, 3000);
            }
        };
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }
}
