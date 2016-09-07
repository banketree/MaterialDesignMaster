package com.sk.collapse.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by sk on 16-9-7.
 */
public class DensityUtils {


    public static int dip2px(Context context, float dp) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float density = dm.density;
        return (int)(density * dp + 0.5f);

    }

    public static float px2dip(Context context, int px) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float density = dm.density;
        return (float)(px / density + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float scaleDensity = dm.scaledDensity;
        return (int)(sp * scaleDensity + 0.5f);
    }

    public static float px2sp(Context context, int px) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float scaleDensity = dm.scaledDensity;

        return (float) (px / scaleDensity + 0.5f);
    }
}
