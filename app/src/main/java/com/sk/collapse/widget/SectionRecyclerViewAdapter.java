package com.sk.collapse.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sk on 16-9-5.
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int VIEW_TYPE_HEADER = 0;
    public final static int VIEW_TYPE_ITEM = 1;
    public final static int VIEW_TYPE_FOOTER = 2;

    private final static int VIEW_TYPE_QTY = 3;

    private int mViewType = 0;

    private LinkedHashMap<String, Section> mSections;
    private Map<String, Integer> mSectionViewType;
    private Context mContext;

    public SectionRecyclerViewAdapter(Context context) {

        this.mContext = context;

        mSections = new LinkedHashMap<>();
        mSectionViewType = new HashMap<>();

    }

    public void addSection(String tag, Section section) {

        mSections.put(tag, section);
        mSectionViewType.put(tag, mViewType);
        mViewType+=VIEW_TYPE_QTY;
    }

    public void addSection(Section section) {
        String tag = UUID.randomUUID().toString();
        addSection(tag, section);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;

        for(Map.Entry<String, Integer> entry : mSectionViewType.entrySet()) {
            int tmpType = entry.getValue();
            if(viewType >= tmpType && viewType < tmpType + VIEW_TYPE_QTY) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                Section section = mSections.get(entry.getKey());
                View view = null;
                int resid = 0;
                int type = viewType - entry.getValue();

                switch (type) {
                    case VIEW_TYPE_HEADER:
                        resid = section.getHeadResourceID();
                        if(resid == 0) {
                            throw new NullPointerException("Missing 'header' resource id");
                        }
                        view = (View)inflater.inflate(resid, parent, false);
                        holder = section.getHeaderViewHolder(view);
                        break;

                    case VIEW_TYPE_FOOTER:
                        resid = section.getFootResourceID();
                        if(resid == 0) {
                            throw new NullPointerException("Missing 'footer' resource id");
                        }
                        view = (View)inflater.inflate(resid, parent, false);
                        holder = section.getFooterViewHolder(view);
                        break;

                    case VIEW_TYPE_ITEM:
                        resid = section.getItemResourceID();
                        if(resid == 0) {
                            throw new NullPointerException("Missing 'item' resource id");
                        }
                        view = (View)inflater.inflate(section.getItemResourceID(), parent, false);
                        holder = section.getBindViewHolder(view);
                        break;

                }
            }
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int curPos  = 0;
        for(Map.Entry<String, Section> entry : mSections.entrySet()) {
            Section section = entry.getValue();
            int type = mSectionViewType.get(entry.getKey());
            int sectionTotal = section.getSectionItemTotal();

            if(position >= curPos && position < (curPos + sectionTotal)) {
                if(section.isHaveHead() && position == curPos) {

                    getSectionForPosition(position).onBindHeaderViewHolder(holder, position);
                    return;
                }

                if(section.isHaveFoot() && position == curPos + sectionTotal - 1) {
                    getSectionForPosition(position).onBindFooterViewHolder(holder, position);
                    return;
                }

                getSectionForPosition(position).onBindItemViewHolder(holder, getSectionPosition(position));
                return;
            }

            curPos += sectionTotal;
        }

        throw  new IndexOutOfBoundsException("onBindViewHolder->Invalid position");
    }

    @Override
    public int getItemCount() {

        int total = 0;
        for(Map.Entry<String, Section> entry : mSections.entrySet()) {
            Section section = entry.getValue();
            total += section.getSectionItemTotal();
        }

        return total;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);

        int curPos = 0;
        for(Map.Entry<String, Section> entry : mSections.entrySet()) {
            Section section = entry.getValue();

            int sectionTotal = section.getSectionItemTotal();

            if(position >= curPos && position < (curPos + sectionTotal)) {
                int viewType = mSectionViewType.get(entry.getKey());

                if(section.isHaveHead()) {
                    if(position == curPos) {
                        return viewType;
                    }
                }

                if(section.isHaveFoot()) {
                    if(position == (curPos + sectionTotal - 1)) {
                        return viewType + 2;
                    }
                }

                return viewType + 1;
            }
            curPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionItemViewType(int position) {
        return getItemViewType(position) % VIEW_TYPE_QTY;
    }


    public Section getSectionForPosition(int position) {

        int curPos = 0;
        for(Map.Entry<String, Section> entry : mSections.entrySet()) {
            Section section = entry.getValue();
            if(section == null)
                continue;
            int sectionTotal = section.getSectionItemTotal();

            if (position >= curPos && (position < curPos + sectionTotal))
            {
                return section;
            }

            curPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException();
    }

    public int getSectionPosition(int position)
    {

        int currentPos = 0;

        for (Map.Entry<String,Section> entry : mSections.entrySet())
        {
            Section section = entry.getValue();


            int sectionTotal = section.getSectionItemTotal();

            // check if position is in this section
            if (position >= currentPos && position < (currentPos + sectionTotal))
            {
                return position - currentPos - (section.isHaveHead() ? 1 : 0);
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    public Section getSection(String tag) {
        return mSections.get(tag);
    }

    public void removeSection(String tag) {
        mSections.remove(tag);
    }

    public void removeAllSection() {
        mSections.clear();
        mViewType = 0;
    }

    public void removeAllSectionViewType() {
        mSectionViewType.clear();
    }


    public static class EmptyViewHolder extends RecyclerView.ViewHolder{
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
