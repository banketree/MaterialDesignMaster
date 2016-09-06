package com.sk.collapse.model;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sk.collapse.activity.R;
import com.sk.collapse.widget.StateLessSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-9-6.
 */
public class RecommendActivitySection extends StateLessSection {

    private Context mContext;
    private ResultInfo mResultInfo;

    private RecommendActivityAdapter mAdapter;


    public RecommendActivitySection(Context context, ResultInfo resultInfo) {
        super(R.layout.recommend_activity_layout, R.layout.recommend_empty_layout);

        this.mContext = context;
        this.mResultInfo = resultInfo;

    }

    @Override
    public Object getItem(int position) {
        if(mResultInfo == null || mResultInfo.getBody() == null)
            return null;
        if(position < 0 || position >= mResultInfo.getBody().size())
            return null;

        return mResultInfo.getBody().get(position);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);

        HeadViewHolder headViewHolder = (HeadViewHolder) holder;


        headViewHolder.topic_type_tv.setText(mResultInfo.getHead().getTitle());
        headViewHolder.topic_type_img.setImageResource(R.drawable.ic_header_activity_center);
        headViewHolder.item_type_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

        headViewHolder.id_recyclerview.setHasFixedSize(false);
        headViewHolder.id_recyclerview.setLayoutManager(lm);
        headViewHolder.id_recyclerview.setNestedScrollingEnabled(false);

        List<BodyInfo> list = (mResultInfo != null) ? (mResultInfo.getBody()) : (new ArrayList<BodyInfo>());
        mAdapter = new RecommendActivityAdapter(mContext, list);
        headViewHolder.id_recyclerview.setAdapter(mAdapter);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeadViewHolder(view);
    }



    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getBindViewHolder(View view) {
        return new ItemViewHolder(view);
    }


    @Override
    public int getContentItemTotalCount() {
        return 1;
    }


    private class HeadViewHolder extends RecyclerView.ViewHolder {

        private TextView item_type_more;
        private RecyclerView id_recyclerview;
        private TextView topic_type_tv;
        private ImageView topic_type_img;

        public HeadViewHolder(View itemView) {
            super(itemView);

            item_type_more = (TextView) itemView.findViewById(R.id.item_type_more);
            id_recyclerview = (RecyclerView) itemView.findViewById(R.id.id_recyclerview);
            topic_type_tv = (TextView) itemView.findViewById(R.id.topic_type_tv);
            topic_type_img = (ImageView) itemView.findViewById(R.id.topic_type_img);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }








    private class ActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView topic_title;
        private ImageView topic_image;

        public ActivityViewHolder(View itemView) {
            super(itemView);

            topic_title = (TextView) itemView.findViewById(R.id.topic_title);
            topic_image = (ImageView) itemView.findViewById(R.id.topic_image);
        }
    }

    private class RecommendActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

        private Context context;
        private List<BodyInfo> list;

        public RecommendActivityAdapter(Context context, List<BodyInfo> list) {

            this.context = context;
            this.list = list;
        }


        @Override
        public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = (View) LayoutInflater.from(this.context).inflate(R.layout.recommend_activity_item_layout, parent, false);

            return new ActivityViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityViewHolder holder, int position) {

            BodyInfo bodyInfo = (BodyInfo)getItem(position);
            if(bodyInfo == null)
                return;

            holder.topic_title.setText(bodyInfo.getTitle());
            Glide.with(this.context).load(Uri.parse(bodyInfo.getCover()))
                    .placeholder(R.drawable.bili_default_image_tv)
                    .centerCrop()
                    .into(holder.topic_image);

        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }
    }
}
