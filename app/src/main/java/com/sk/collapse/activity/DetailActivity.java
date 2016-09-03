package com.sk.collapse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by sk on 16-9-2.
 */
public class DetailActivity extends BaseAppCompatActivity {

    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCollapse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);


        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(true);
            ab.setDisplayUseLogoEnabled(true);
        }

        mCollapse = (CollapsingToolbarLayout) findViewById(R.id.id_collapse);
//        mCollapse.setExpandedTitleColor(Color.WHITE);
//        mCollapse.setCollapsedTitleTextColor(Color.WHITE);
//        mCollapse.setExpandedTitleMarginBottom(30);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
