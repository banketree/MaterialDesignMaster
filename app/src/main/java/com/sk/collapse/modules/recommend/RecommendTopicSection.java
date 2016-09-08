package com.sk.collapse.modules.recommend;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sk.collapse.activity.R;
import com.sk.collapse.model.BodyInfo;
import com.sk.collapse.model.ResultInfo;
import com.sk.collapse.widget.StateLessSection;

/**
 * Created by sk on 16-9-6.
 */
public class RecommendTopicSection extends StateLessSection {


    private ResultInfo mResultInfo;
    private Context mContext;

    public RecommendTopicSection(Context context, ResultInfo resultInfo) {
        super(R.layout.recommend_topic_layout, R.layout.recommend_empty_layout);

        this.mContext = context;
        this.mResultInfo = resultInfo;
    }





    public Object getItem(int position) {
        if(mResultInfo == null || mResultInfo.getBody() == null
                || mResultInfo.getBody().size() <= position || position < 0)
            return null;

        return mResultInfo.getBody().get(position);
    }

    @Override
    public int getContentItemTotalCount() {
        return 1;
    }



    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public RecyclerView.ViewHolder getBindViewHolder(View view) {
        return new ItemViewHolder(view);
    }



    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int recylepos) {

        HeadViewHolder headViewHolder = (HeadViewHolder) holder;
        BodyInfo bodyInfo = (BodyInfo) getItem(0);
        if(bodyInfo == null)
            return;

        Glide.with(mContext).load(Uri.parse(bodyInfo.getCover()))
                .placeholder(R.drawable.bili_default_image_tv)
                .centerCrop()
                .into(headViewHolder.topic_image);

        headViewHolder.topic_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        headViewHolder.topic_title.setVisibility(View.GONE);

        headViewHolder.item_type_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeadViewHolder(view);
    }




    private static class HeadViewHolder extends RecyclerView.ViewHolder {
        private TextView item_type_more;
        private ImageView topic_image;
        private TextView topic_title;

        public HeadViewHolder(View itemView) {
            super(itemView);

            item_type_more = (TextView) itemView.findViewById(R.id.item_type_more);
            topic_image = (ImageView) itemView.findViewById(R.id.topic_image);
            topic_title = (TextView) itemView.findViewById(R.id.topic_title);
        }
    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);

        }
    }
}
