package com.sk.collapse.widget;

/**
 * Created by sk on 16-9-5.
 */
public abstract class StateLessSection extends Section {


    public StateLessSection(int itemResourceID) {
        super();
        setItemResourceID(itemResourceID);
    }


    public StateLessSection(int headResourceID, int itemResourceID) {

        this(itemResourceID);
        setHeadResourceID(headResourceID);
        setHaveHead(true);
    }


    public StateLessSection(int headResourceID, int itemResourceID, int footResourceID) {

        this(headResourceID, itemResourceID);
        setFootResourceID(footResourceID);
        setHaveFoot(true);
    }

    abstract public Object getItem(int position);
}
