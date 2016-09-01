package com.lizheng.www.a9gag;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lizheng.www.a9gag.dao.FeedsDataHelper;
import com.lizheng.www.a9gag.model.Category;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends Fragment {

    public static final String EXTRA_CATEGORY = "extra_category";
    @BindView(R.id.grid_view)
    RecyclerView gridView;

    private Category mCategory;
    private FeedsDataHelper mDataHelper;


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
    }

    @OnClick(R.id.grid_view)
    public void onClick() {
    }
}
