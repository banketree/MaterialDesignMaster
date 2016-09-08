package com.sk.collapse.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sk on 16-9-5.
 */
public abstract class Section {

    private boolean isVisible;
    private boolean isHaveHead;
    private boolean isHaveFoot;

    private int mHeadResourceID;
    private int mFootResourceID;
    private int mItemResourceID;



    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isHaveHead() {
        return isHaveHead;
    }

    public void setHaveHead(boolean haveHead) {
        isHaveHead = haveHead;
    }

    public boolean isHaveFoot() {
        return isHaveFoot;
    }

    public void setHaveFoot(boolean haveFoot) {
        isHaveFoot = haveFoot;
    }

    public int getHeadResourceID() {
        return mHeadResourceID;
    }

    public void setHeadResourceID(int mHeadResourceID) {
        this.mHeadResourceID = mHeadResourceID;
    }

    public int getFootResourceID() {
        return mFootResourceID;
    }

    public void setFootResourceID(int mFootResourceID) {
        this.mFootResourceID = mFootResourceID;
    }

    public int getItemResourceID() {
        return mItemResourceID;
    }

    public void setItemResourceID(int mItemResourceID) {
        this.mItemResourceID = mItemResourceID;
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int recylepos) {

    }
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int recylepos) {

    }
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);


    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SectionRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public RecyclerView.ViewHolder getFooterViewHolder(View view) {

        return new SectionRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public abstract RecyclerView.ViewHolder getBindViewHolder(View view);

    public abstract int getContentItemTotalCount();


    public int getSectionItemTotal() {

        return getContentItemTotalCount() + (isHaveHead() ? 1 : 0) + (isHaveFoot() ? 1 : 0);
    }
}
