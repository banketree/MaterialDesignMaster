package com.sk.collapse.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sk.collapse.model.BaseBanner;
import com.sk.collapse.model.BodyInfo;
import com.sk.collapse.model.RecommendActivitySection;
import com.sk.collapse.model.RecommendBannerSection;
import com.sk.collapse.model.RecommendContentSection;
import com.sk.collapse.model.RecommendInfo;
import com.sk.collapse.model.RecommendTopicSection;
import com.sk.collapse.model.ResultInfo;
import com.sk.collapse.network.NetworkClient;
import com.sk.collapse.network.NetworkModel;
import com.sk.collapse.network.onHttpResponse;
import com.sk.collapse.widget.SectionRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab0Fragment extends Fragment {

    private final static int RECOMMEND_SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout mRefreshLayout;

    private RecommendInfo mRecommendInfo;
    private List<ResultInfo> mList;
    private List<BaseBanner> mBannerList;

    private SectionRecyclerViewAdapter mAdapter;



    private int[] icons = new int[]{
            R.drawable.ic_header_hot,
            R.drawable.ic_head_live,
            R.drawable.ic_category_t13,
            R.drawable.ic_category_t1,
            R.drawable.ic_category_t3,
            R.drawable.ic_category_t129,
            R.drawable.ic_category_t4,
            R.drawable.ic_category_t119,
            R.drawable.ic_category_t36,
            R.drawable.ic_header_activity_center,
            R.drawable.ic_category_t160,
            R.drawable.ic_category_t155,
            R.drawable.ic_category_t5,
            R.drawable.ic_category_t11,
            R.drawable.ic_category_t23
    };


    public Tab0Fragment() {
        // Required empty public constructor

        mList = new ArrayList<>();
        mBannerList = new ArrayList<>();

    }


    public static  Tab0Fragment newInstance() {
        return new Tab0Fragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (View)inflater.inflate(R.layout.tab0_layout, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.id_recyclerview);
        mRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.id_refreshlayout);


        //init refresh
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setBackgroundColor(Color.WHITE);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecommendData();
            }
        });

        //init recyclerview
        mLayoutManager = new GridLayoutManager(getActivity(), RECOMMEND_SPAN_COUNT);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (mAdapter.getSectionItemViewType(position)) {
                    case SectionRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;

                    case SectionRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return 2;

                    default:
                        return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SectionRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    private void showProgressBar() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);

                loadRecommendData();

            }
        });

    }

    private void stopProgressBar() {
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showProgressBar();
    }

    private void loadRecommendData() {
        NetworkClient.getInstance(getActivity()).get(NetworkModel.BASE_URL, new onHttpResponse(getActivity(), null) {
            @Override
            public void onResult(int code, Object result, Object data) {
                stopProgressBar();

                if(code == 0) {
                    String ret = (String) result;
                    Gson gson = new Gson();
                    Type type = new com.google.gson.reflect.TypeToken<RecommendInfo>(){}.getType();
                    RecommendInfo recommendInfo = gson.fromJson(ret, type);

                    mBannerList.clear();
                    mList.clear();

                    mAdapter.removeAllSection();
                    mAdapter.removeAllSectionViewType();

                    if(recommendInfo != null && recommendInfo.getCode() == 0) {
                        mRecommendInfo = recommendInfo;
                        List<ResultInfo> rlist = recommendInfo.getResult();
                        for(int i=0;rlist != null && i<rlist.size();i++) {
                            ResultInfo rinfo = rlist.get(i);
                            if(rinfo == null)
                                continue;

                            if(TextUtils.isEmpty(rinfo.getType())) {
                                mList.add(rinfo);
                            } else if(rinfo.getType().equals(RecommendInfo.RECOMMEND_TYPE_WEBLINK)) {
                                BodyInfo bodyInfo = (rinfo.getBody() != null && rinfo.getBody().size() > 0)
                                        ? rinfo.getBody().get(0) : null;
                                if(bodyInfo != null) {
                                    mBannerList.add(new BaseBanner(bodyInfo.getTitle(), bodyInfo.getCover(), bodyInfo.getParam()));
                                }
                            }
                            else if(rinfo.getType().equals(RecommendInfo.RECOMMEND_TYPE_ACTIVITY)){
                                mList.add(rinfo);
                            }
                            else {
                                mList.add(rinfo);
                            }
                        }
                    }

                    //banner
                    mAdapter.addSection(new RecommendBannerSection(mBannerList));

                    //mlist
                    for(int i = 0;i < mList.size();i++) {
                        ResultInfo resultInfo = mList.get(i);
                        if(resultInfo == null)
                            continue;

                        if(TextUtils.isEmpty(resultInfo.getType())) {

                            mAdapter.addSection(new RecommendTopicSection(getActivity(), resultInfo));

                        } else if (RecommendInfo.RECOMMEND_TYPE_ACTIVITY.equals(resultInfo.getType())){

                            mAdapter.addSection(new RecommendActivitySection(getActivity(), resultInfo));

                        } else {

                            mAdapter.addSection(new RecommendContentSection(getActivity(), resultInfo, resultInfo.getType(), icons[i % icons.length]));
                        }
                    }

                    mAdapter.notifyDataSetChanged();
                }else {
                    Snackbar.make(mRecyclerView, R.string.network_error, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
