package com.sk.collapse.modules.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sk.collapse.activity.R;
import com.sk.collapse.customview.BannerView;
import com.sk.collapse.model.BaseBanner;
import com.sk.collapse.widget.StateLessSection;

import java.util.List;

/**
 * Created by sk on 16-9-5.
 */
public class RecommendBannerSection extends StateLessSection {

    private List<BaseBanner> mBanners = null;

    public RecommendBannerSection(List<BaseBanner> banners) {
        super(R.layout.recommend_banner_layout, R.layout.recommend_empty_layout);
        this.mBanners = banners;
    }



    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getBindViewHolder(View view) {
        return new ItemViewHolder(view);
    }




    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new BannerViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int recylepos) {
        BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
        bannerViewHolder.mBannerView.delayTime(5).build(mBanners);
    }



    @Override
    public int getContentItemTotalCount() {
        return 1;
    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder
    {

        public ItemViewHolder(View itemView)
        {

            super(itemView);
        }
    }


    private static class BannerViewHolder extends RecyclerView.ViewHolder {
        private BannerView mBannerView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mBannerView = (BannerView) itemView.findViewById(R.id.id_bannerview);
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
