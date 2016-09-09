package com.sk.collapse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by sk on 16-9-2.
 */
public class Tab1Fragment extends Fragment {


    private ExplosionField mExplosionFiled = null;

    public Tab1Fragment() {


    }
    public static Tab1Fragment newInstance() {
        Tab1Fragment fragment = new Tab1Fragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = (View)inflater.inflate(R.layout.tab1_layout, container, false);

        Button detail = (Button) root.findViewById(R.id.btn_detail);
        detail.setText(R.string.to_detail_activity);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });

        Button tint = (Button) root.findViewById(R.id.btn_tint);
        tint.setText(R.string.to_tint_activity);
        tint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TintActivity.class));
            }
        });

        Button bottom = (Button) root.findViewById(R.id.btn_bottom);
        bottom.setText(R.string.to_bottom_activity);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BazierActivity.class));
            }
        });


        mExplosionFiled = ExplosionField.attach2Window(getActivity());
        final ImageView iv = (ImageView) root.findViewById(R.id.iv_explosionfield);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionFiled.explode(v);

//                Drawable d = iv.getDrawable();
//                BitmapDrawable bd = (BitmapDrawable)d;
//                Bitmap bp = bd.getBitmap();
//                Rect ft = new Rect(iv.getLeft(), iv.getTop(), iv.getRight(), iv.getBottom());
//                mExplosionFiled.explode(bp, ft, 100, 2000);
            }
        });

        return root;
    }

}
