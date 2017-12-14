package com.example.shivani.reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{
    
    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;

    private int[] mImageResources = {
            R.drawable.abc1,
            R.drawable.abc2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // To make activity full screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        intro_images = (ViewPager)findViewById(R.id.pager_introduction);
        btnNext = (ImageButton)findViewById(R.id.btn_next);
        btnFinish = (ImageButton) findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        mAdapter = new ViewPagerAdapter(MainActivity.this, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
    }
    
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_dot));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount)
                        ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_finish:
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selected_dot));

        if (position + 1 == dotsCount) {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
