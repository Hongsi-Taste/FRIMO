package com.example.frimo;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.frimo.adapter.MyAdapter;
import com.example.frimo.hamburgermenu.PowerMenuUtils;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends FragmentActivity {

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    private PowerMenu hamburgerMenu;

    private final OnMenuItemClickListener<PowerMenuItem> onHamburgerItemClickListener =
            new OnMenuItemClickListener<PowerMenuItem>() {
                @Override
                public void onItemClick(int position, PowerMenuItem item) {
                    Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    hamburgerMenu.setSelectedPosition(position);
                }
            };

    private final OnDismissedListener onHamburgerMenuDismissedListener =
            () -> Log.d("Test", "onDismissed hamburger menu");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager2
        mPager = findViewById(R.id.viewpager);

        // Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);

        // Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);

        // ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(1000); // 현재 위치를 1000으로 하여 양옆으로 슬라이딩할 수 있게
        mPager.setOffscreenPageLimit(3);

        // hamburger menu
        hamburgerMenu =
                PowerMenuUtils.getHamburgerPowerMenu(
                        this, this, onHamburgerItemClickListener, onHamburgerMenuDismissedListener);

        // Slide하여 fragment를 바꿀 때
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }

        });


    }

    // hamburgermenu
    public void onHamburger(View view) {
        if (hamburgerMenu.isShowing()) {
            hamburgerMenu.dismiss();
            return;
        }
        hamburgerMenu.showAsDropDown(view);
    }

    // when back button pressed, dismiss hamburgermenu
    @Override
    public void onBackPressed() {
        if (hamburgerMenu.isShowing()) {
            hamburgerMenu.dismiss();
        }
    }

}