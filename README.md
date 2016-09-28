# MaterialDesignMaster
Test android metro design UI control of CoordinatorLayout, AppBarLayout, CollapsingToolbarLayout, ToolBar, NestedScrollView, CardView, FloatingActionButton, TabLayout.The UI is designed by BILI app.


演示截图如下：

![Alt text](https://github.com/sk569437/MaterialDesignMaster/raw/master/ScreenShot/1.png )
![Alt text](https://github.com/sk569437/MaterialDesignMaster/raw/master/ScreenShot/2.png)
![Alt text](https://github.com/sk569437/MaterialDesignMaster/raw/master/ScreenShot/3.png)
![Alt text](https://github.com/sk569437/MaterialDesignMaster/raw/master/ScreenShot/4.png)
![Alt text](https://github.com/sk569437/MaterialDesignMaster/raw/master/ScreenShot/5.png)



# 2016.9.19 18:38
add opencv for android test by jni. The jni function is using OpenCV library to take picture to Gray.Post a gray interface to Java.

# 2016.9.28 16:45
add custom view of 'FlowLayout' and 'TagTextView'. FlowLayout is flow layout of ViewGroup,conatined some views show by line until to max width,it will goto next line begin. TagTextView is extend from TextView, but add a new custom state by 'state_isChoose', User can set by self or set on drawable file same as other state(state_pressed, state_focus and so on).

FlowLayout use example:
on xml:
```
<com.sk.collapse.customview.FlowLayout
        android:id="@+id/id_flowlayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

add child on code:
```
	FlowLayout flowLayout = (FlowLayout)view.findViewById(R.id.id_flowlayout);
	flowLayout.removeAllViews();
	flowLayout.setMargins(15, 15, 15, 0);
	for(int i=0;i<tags.length;i++) {
	    TagTextView tv = new TagTextView(this);
	    tv.setText(tags[i]);
	    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		    ViewGroup.LayoutParams.WRAP_CONTENT);
	    tv.setLayoutParams(lp);
	    tv.setBackgroundResource(R.drawable.tag_item_bg_selector);
	    tv.setTextColor(Color.GRAY);
	    tv.setTextSize(16);

	    flowLayout.addView(tv);
	}
```

TagTextView use example:
on xml:
```
	<item app:state_isTagChoose="true">
	</item>
```
on code:
```
	TagTextView tagView = new TagTextView(this);
	tagView.setIsChoose(true);
```
