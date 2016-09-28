package com.sk.collapse.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sk.collapse.activity.R;

/**
 * Created by sk on 16-9-28.
 */

public class TagTextView extends TextView {


    private static final int []sel_state = {R.attr.state_isTagChoose};

    private boolean mIsTagChoose = false;

    public TagTextView(Context context) {
        super(context);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        int []drawableState = super.onCreateDrawableState(extraSpace + 1);
        if(mIsTagChoose) {
            mergeDrawableStates(drawableState, sel_state);
        }

        return drawableState;
    }

    public boolean GetIsTagChoose() {
        return mIsTagChoose;
    }

    public void setIsTagChoose(boolean mIsTagChoose) {
        this.mIsTagChoose = mIsTagChoose;
    }
}
