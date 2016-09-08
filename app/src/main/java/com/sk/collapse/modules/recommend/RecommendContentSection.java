package com.sk.collapse.modules.recommend;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sk.collapse.activity.R;
import com.sk.collapse.customview.RotateImageView;
import com.sk.collapse.model.BodyInfo;
import com.sk.collapse.model.RecommendInfo;
import com.sk.collapse.model.ResultInfo;
import com.sk.collapse.widget.StateLessSection;

/**
 * Created by sk on 16-9-6.
 */
public class RecommendContentSection extends StateLessSection {




    private String mRecommendType;
    private ResultInfo mRecommenResultInfo;
    private Context mContext;
    private int mTypeIcon;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;




    public RecommendContentSection(Context context, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, ResultInfo resultInfo, String type, int iconid) {
        super(R.layout.recommend_content_head_layout, R.layout.recommend_content_item_layout, R.layout.recommend_content_foot_layout);

        this.mRecommendType = type;
        this.mRecommenResultInfo = resultInfo;
        this.mContext = context;
        this.mTypeIcon = iconid;

        this.mAdapter = adapter;
    }


    public Object getItem(int position) {
        if(mRecommenResultInfo == null || mRecommenResultInfo.getBody() == null
                || mRecommenResultInfo.getBody().size() <= position || position < 0)
            return null;

        return mRecommenResultInfo.getBody().get(position);
    }



    @Override
    public int getContentItemTotalCount() {
        if(mRecommenResultInfo == null || mRecommenResultInfo.getBody() == null)
            return 0;

        return mRecommenResultInfo.getBody().size();
    }


    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        BodyInfo bodyInfo = (BodyInfo)getItem(position);
        if(bodyInfo == null) {
            return;
        }

        Glide.with(mContext).load(Uri.parse(bodyInfo.getCover()))
                .centerCrop()
                .placeholder(R.drawable.bili_default_image_tv)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemViewHolder.video_preview);

        if(!TextUtils.isEmpty(bodyInfo.getTitle()))
            itemViewHolder.video_title.setText(bodyInfo.getTitle());
        if(RecommendInfo.RECOMMEND_TYPE_LIVE.equals(mRecommendType)) {
            itemViewHolder.layout_video.setVisibility(View.GONE);
            itemViewHolder.layout_live.setVisibility(View.VISIBLE);
            itemViewHolder.item_live_up.setText(bodyInfo.getUp());
            itemViewHolder.item_live_online.setText(bodyInfo.getOnline());
        }else if(RecommendInfo.RECOMMEND_TYPE_BANGUMI_2.equals(mRecommendType)) {
            itemViewHolder.layout_video.setVisibility(View.GONE);
            itemViewHolder.layout_live.setVisibility(View.VISIBLE);
            itemViewHolder.item_live_online.setVisibility(View.GONE);
            itemViewHolder.item_live_up.setText(bodyInfo.getDesc1());
        }else {
            itemViewHolder.layout_video.setVisibility(View.VISIBLE);
            itemViewHolder.layout_live.setVisibility(View.GONE);
            itemViewHolder.video_play_num.setText(bodyInfo.getPlay());
            itemViewHolder.video_review_count.setText(bodyInfo.getDanmaku());

        }
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int recylepos) {

        HeadViewHolder headViewHolder = (HeadViewHolder)holder;
        headViewHolder.item_type_img.setImageResource(mTypeIcon);
        headViewHolder.item_type_tv.setText(mRecommenResultInfo.getHead().getTitle());

        if(RecommendInfo.RECOMMEND_TYPE_RECOMMEND.equals(mRecommendType)) {
            headViewHolder.item_live_all_num.setVisibility(View.GONE);
            headViewHolder.item_type_more.setVisibility(View.GONE);
            headViewHolder.item_type_rank_btn.setVisibility(View.VISIBLE);
            headViewHolder.item_type_rank_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else if(RecommendInfo.RECOMMEND_TYPE_LIVE.equals(mRecommendType)) {
            headViewHolder.item_live_all_num.setVisibility(View.VISIBLE);
            headViewHolder.item_live_all_num.setText(String.format(mContext.getResources().getString(R.string.cur_lives_num), mRecommenResultInfo.getHead().getCount()));
            headViewHolder.item_type_more.setVisibility(View.VISIBLE);
            headViewHolder.item_type_rank_btn.setVisibility(View.GONE);
            headViewHolder.item_type_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            headViewHolder.item_live_all_num.setVisibility(View.GONE);
            headViewHolder.item_type_more.setVisibility(View.VISIBLE);
            headViewHolder.item_type_rank_btn.setVisibility(View.GONE);
            headViewHolder.item_type_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }


    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int recylepos) {

        final FootViewHolder footViewHolder = (FootViewHolder) holder;
        final int pos = recylepos;

        String rtype = mRecommenResultInfo.getType();
        if(TextUtils.isEmpty(rtype)) {

        } else if(RecommendInfo.RECOMMEND_TYPE_RECOMMEND.equals(rtype)) {
            footViewHolder.item_btn_more.setVisibility(View.GONE);
            footViewHolder.item_refresh_layout.setVisibility(View.GONE);
            footViewHolder.item_recommend_refresh_layout.setVisibility(View.VISIBLE);
            footViewHolder.item_bangumi_layout.setVisibility(View.GONE);

            footViewHolder.item_recommend_refresh_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    footViewHolder.item_recommend_refresh.startAnim();
                }
            });
        }else if(RecommendInfo.RECOMMEND_TYPE_BANGUMI_2.equals(rtype)) {
            footViewHolder.item_btn_more.setVisibility(View.GONE);
            footViewHolder.item_refresh_layout.setVisibility(View.GONE);
            footViewHolder.item_recommend_refresh_layout.setVisibility(View.GONE);
            footViewHolder.item_bangumi_layout.setVisibility(View.VISIBLE);
        }else {
            footViewHolder.item_btn_more.setVisibility(View.VISIBLE);
            footViewHolder.item_refresh_layout.setVisibility(View.VISIBLE);
            footViewHolder.item_dynamic.setText(String.format(mContext.getResources().getString(R.string.format_new_msg), mRecommenResultInfo.getHead().getParam()));
            footViewHolder.item_recommend_refresh_layout.setVisibility(View.GONE);
            footViewHolder.item_bangumi_layout.setVisibility(View.GONE);

            footViewHolder.item_btn_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    footViewHolder.item_btn_refresh.startAnim();
                }
            });
        }
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeadViewHolder(view);
    }


    @Override
    public RecyclerView.ViewHolder getBindViewHolder(View view) {
        return new ItemViewHolder(view);
    }


    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new FootViewHolder(view);
    }

    private static class HeadViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_type_img;
        private TextView item_type_tv, item_type_rank_btn, item_live_all_num, item_type_more;

        public HeadViewHolder(View itemView) {
            super(itemView);

            item_type_img = (ImageView) itemView.findViewById(R.id.item_type_img);
            item_type_tv = (TextView) itemView.findViewById(R.id.item_type_tv);
            item_type_rank_btn = (TextView) itemView.findViewById(R.id.item_type_rank_btn);
            item_live_all_num = (TextView) itemView.findViewById(R.id.item_live_all_num);
            item_type_more = (TextView) itemView.findViewById(R.id.item_type_more);
        }
    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {
        private Button item_btn_more;
        private View item_refresh_layout, item_recommend_refresh_layout, item_bangumi_layout;
        private TextView item_dynamic;

        private RotateImageView item_recommend_refresh, item_btn_refresh;


        public FootViewHolder(View itemView) {
            super(itemView);

            item_btn_more = (Button) itemView.findViewById(R.id.item_btn_more);
            item_refresh_layout = (View) itemView.findViewById(R.id.item_refresh_layout);
            item_recommend_refresh_layout = (View) itemView.findViewById(R.id.item_recommend_refresh_layout);
            item_bangumi_layout = (View) itemView.findViewById(R.id.item_bangumi_layout);
            item_dynamic = (TextView) itemView.findViewById(R.id.item_dynamic);

            item_recommend_refresh = (RotateImageView) itemView.findViewById(R.id.item_recommend_refresh);
            item_btn_refresh = (RotateImageView) itemView.findViewById(R.id.item_btn_refresh);
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView video_preview;
        private TextView video_title;

        private View layout_video, layout_live;
        //video
        private TextView video_play_num, video_review_count;
        //live
        private TextView item_live_up, item_live_online;

        public ItemViewHolder(View itemView) {
            super(itemView);

            video_preview = (ImageView) itemView.findViewById(R.id.video_preview);
            video_title = (TextView) itemView.findViewById(R.id.video_title);

            layout_video = (View) itemView.findViewById(R.id.layout_video);
            layout_live = (View) itemView.findViewById(R.id.layout_live);

            video_play_num = (TextView) itemView.findViewById(R.id.video_play_num);
            video_review_count = (TextView) itemView.findViewById(R.id.video_review_count);

            item_live_up = (TextView) itemView.findViewById(R.id.item_live_up);
            item_live_online = (TextView) itemView.findViewById(R.id.item_live_online);

        }
    }
}
