package com.sk.collapse.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sk.collapse.customview.FlowLayout;
import com.sk.collapse.customview.TagTextView;
import com.sk.opencv.OpenCVHelper;

/**
 * Created by sk on 16-9-14.
 */
public class Tab2Fragment extends Fragment {

    private ImageView mGrayImage;
    private final static String []tags = {"abc", "1234", "Good", "Congraulatiron", "Chinese", "Movie", "Chinese GongFu"
                        ,"English", "KFC", "aaaaaaaaaaaaaaaaaaa", "55", "99999999999999999999999"};

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


        FlowLayout flowLayout = (FlowLayout)view.findViewById(R.id.id_flowlayout);
        flowLayout.removeAllViews();
        flowLayout.setMargins(15, 15, 15, 0);
        for(int i=0;i<tags.length;i++) {
            TagTextView tv = new TagTextView(getActivity());
            tv.setText(tags[i]);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            tv.setLayoutParams(lp);
            tv.setBackgroundResource(R.drawable.tag_item_bg_selector);
            tv.setTextColor(Color.GRAY);
            tv.setTextSize(16);
            tv.setIsTagChoose(i == 0);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //tv.setPadding(15, 10, 15, 10);

            flowLayout.addView(tv);
        }

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
