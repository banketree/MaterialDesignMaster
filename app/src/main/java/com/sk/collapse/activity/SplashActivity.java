package com.sk.collapse.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by sk on 16-9-8.
 */
public class SplashActivity extends BaseAppCompatActivity {

    private ImageView iv_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        iv_avatar = (ImageView) findViewById(R.id.iv_avatar);

        onSplash();
    }

    private void onSplash() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv_avatar, "scaleX", 1, 1.2f);
        ObjectAnimator ob = ObjectAnimator.ofFloat(iv_avatar, "scaleY", 1, 1.2f);
        AnimatorSet as = new AnimatorSet();
        as.setDuration(1500);
        as.setInterpolator(new AccelerateDecelerateInterpolator());
        as.playTogether(oa, ob);
        as.setStartDelay(300);
        as.start();

        as.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
