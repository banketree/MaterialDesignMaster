package com.sk.collapse.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sk.opencv.OpenCVHelper;

/**
 * Created by sk on 16-9-14.
 */
public class Tab2Fragment extends Fragment {

    private ImageView mGrayImage;

    public Tab2Fragment() {

    }

    public static Tab2Fragment newInstance() {

        Tab2Fragment fragment = new Tab2Fragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = (View) inflater.inflate(R.layout.tab2_layout, container, false);

        view.findViewById(R.id.btn_gray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = getBitmap();
                        int w = bitmap.getWidth(), h = bitmap.getHeight();
                        int[] pix = new int[w * h];
                        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
                        int [] resultPixes= OpenCVHelper.gray(pix,w,h);
                        Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
                        result.setPixels(resultPixes, 0, w, 0, 0,w, h);
                        mGrayImage.setImageBitmap(result);
                    }
                });
            }
        });

        mGrayImage = (ImageView) view.findViewById(R.id.iv_gray);

        return view;
    }

    private Bitmap getBitmap() {
        Drawable db = mGrayImage.getDrawable();
        if(db == null)
            return null;
        Bitmap bp = null;
        BitmapDrawable bd = (BitmapDrawable) db;
        bp = bd.getBitmap();

        return bp;
    }
}
