package com.sk.collapse.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by sk on 16-9-8.
 */
public class RotateImageView extends ImageView {

    private boolean isAnimStarting = false;

    public RotateImageView(Context context) {
        this(context, null);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

    }

    public void startAnim() {

        if(!isAnimStarting) {
            RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setDuration(1000);
            ra.setRepeatCount(Animation.INFINITE);
            ra.setRepeatMode(Animation.RESTART);
            ra.setInterpolator(new LinearInterpolator());
            this.startAnimation(ra);

            isAnimStarting = true;
        }

    }

    public void stopAnim() {

        this.clearAnimation();

        isAnimStarting = false;
    }


}
