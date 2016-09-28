package com.sk.collapse.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sk.collapse.customview.BazierSpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-9-27.
 */

public class Tab3Fragment extends BaseFragment {


    private PersonAdapter mAdapter;
    private List<Person> mList;
    private LinearLayoutManager mLayoutManager;

    public Tab3Fragment() {
        mList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            Person item = new Person();
            item.name = "zhifubao" + (i+1);
            item.sign = "no zuo no die.";
            item.msgs = (i+13) + "";
            mList.add(item);
        }

    }

    public static Tab3Fragment newInstance() {
        Tab3Fragment fragment = new Tab3Fragment();

        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.tab3_layout, container,false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new PersonAdapter((Context) getActivity(), mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerDecoration());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private class Person{
        public String id;
        public String name;
        public String msgs;
        public String sign;
    }
    private class PersonViewHolder extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mSign;
        private BazierSpringView mMsg;
        public PersonViewHolder(View itemView) {
            super(itemView);
            mName =(TextView)itemView.findViewById(R.id.id_name);
            mSign = (TextView)itemView.findViewById(R.id.id_sign);
            mMsg = (BazierSpringView)itemView.findViewById(R.id.id_msg);
        }
    }
    private class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {
        private Context mContext;
        private List<Person> mList;
        public PersonAdapter(Context context, List<Person> list) {
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            View view = inflater.inflate(R.layout.person_item_layout, parent, false);

            return new PersonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonViewHolder holder, int position) {
            Person item =(Person)mList.get(position);
            if(item != null) {
                holder.mName.setText(item.name);
                holder.mSign.setText(item.sign);

            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private class DividerDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;
        private float mDividerHeight;

        private final static int DEFAULT_LINE_COLOR = Color.GRAY;
        private int mDividerColor=DEFAULT_LINE_COLOR;
        public DividerDecoration() {
            super();
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(DEFAULT_LINE_COLOR);
        }

        public void setDividerColor(int color) {
            mDividerColor = color;
        }
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for(int i=0;i<parent.getChildCount();i++) {
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams prams = (RecyclerView.LayoutParams)view.getLayoutParams();
                int top = view.getBottom() + prams.bottomMargin;
                int bottom = top + (int)mDividerHeight;

                c.drawLine(left, top, right, bottom, mPaint);
            }

        }
    }
}
