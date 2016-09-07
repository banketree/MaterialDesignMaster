package com.sk.collapse.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sk on 16-9-7.
 */
public class DesignBottomActivity extends BaseAppCompatActivity {


    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;

    private TextView bottom_sheet_tv;
    private ImageView bottom_sheet_iv;
    private RelativeLayout design_bottom_sheet;
    private BottomSheetBehavior bottom_behavior;
    private Toolbar toolbar, design_bottom_sheet_bar;

    private boolean isSetBottomSheetHeight;

    private List<String> mList;
    private MyGridRecycleAdapter mAdapter;

    private String imgs[] = {
            "http://i0.hdslb.com/bfs/live/954a1fa0fabe3d8c4662a4f0a9db36eda0a2f611.jpg",
            "http://i0.hdslb.com/bfs/live/c9c455a92abdf518058e7b0288b60200f82437d0.jpg",
            "http://i0.hdslb.com/bfs/archive/e2f90c55c77d884dbf31d7252e2d69b57afb5e37.jpg_320x200.jpg"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_bottom_layout);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.bottom_sheet_demo_coordinatorlayout);
//        design_bottom_sheet_bar = (RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);
        bottom_sheet_tv = (TextView) findViewById(R.id.bottom_sheet_tv);
        bottom_sheet_iv = (ImageView) findViewById(R.id.bottom_sheet_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.bottom_sheet_demo_recycler);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.bottom_sheet_demo_swipe_refresh);

        design_bottom_sheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottom_behavior = BottomSheetBehavior.from(design_bottom_sheet);


//        design_bottom_sheet_bar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottom_behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            }
//        });
        bottom_behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState!=BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.VISIBLE){
                    bottom_sheet_tv.setVisibility(View.GONE);
                    bottom_sheet_iv.setVisibility(View.VISIBLE);
                }else if(newState==BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.GONE){
                    bottom_sheet_tv.setVisibility(View.VISIBLE);
                    bottom_sheet_iv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if(bottomSheet.getTop()<2*design_bottom_sheet_bar.getHeight()){
                    design_bottom_sheet_bar.setVisibility(View.VISIBLE);
                    design_bottom_sheet_bar.setAlpha(slideOffset);
                    design_bottom_sheet_bar.setTranslationY(bottomSheet.getTop()-2*design_bottom_sheet_bar.getHeight());
                }
                else{
                    design_bottom_sheet_bar.setVisibility(View.INVISIBLE);
                }
            }
        });

        //recyclerview init
        GridLayoutManager gm = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gm);
        mList = new ArrayList<>();
        for(int i=0;i<15;i++) {
            mList.add(new String(imgs[i%imgs.length]));
        }
        mAdapter = new MyGridRecycleAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        //
        toolbar = (Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        design_bottom_sheet_bar = toolbar;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(!isSetBottomSheetHeight){
            CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
            linearParams.height = mCoordinatorLayout.getHeight() - design_bottom_sheet_bar.getHeight();
            design_bottom_sheet.setLayoutParams(linearParams);
            isSetBottomSheetHeight = true;
        }


    }

    private class MyGridViewHolder extends RecyclerView.ViewHolder {
        private ImageView topic_image;


        public MyGridViewHolder(View itemView) {
            super(itemView);

            topic_image = (ImageView) itemView.findViewById(R.id.topic_image);

        }
    }
    private class  MyGridRecycleAdapter extends RecyclerView.Adapter<MyGridViewHolder> {

        private Context context;
        public MyGridRecycleAdapter(Context context) {
            this.context = context;
        }
        @Override
        public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = (View) LayoutInflater.from(context).inflate(R.layout.design_bottom_item_layout
                                    ,parent, false);
            return new MyGridViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyGridViewHolder holder, int position) {

            Glide.with(context).load(Uri.parse(mList.get(position)))
                    .placeholder(R.drawable.bili_default_image_tv)
                    .centerCrop()
                    .into(holder.topic_image);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
