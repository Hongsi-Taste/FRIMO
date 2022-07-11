package com.example.frimo;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.adapter.MyAdapter;
import com.example.frimo.hamburgermenu.PowerMenuUtils;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import me.relex.circleindicator.CircleIndicator3;


public class MainActivity extends FragmentActivity {

    // fragement slide 관련
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    // powermenu 관련
    private ImageView img_hamburgerMenu;
    private PowerMenu hamburgerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView toolbar_text = findViewById(R.id.toolbar_text);

        // ViewPager2
        mPager = findViewById(R.id.viewpager);

        // Adapter
        pagerAdapter = new MyAdapter(this);
        mPager.setAdapter(pagerAdapter);

        // Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);

        // ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(0); // 현재 위치는 little me diary
        mPager.setOffscreenPageLimit(3);

        // hamburger menu
        img_hamburgerMenu = findViewById(R.id.img_hamburgermenu);
        hamburgerMenu =
                PowerMenuUtils.getHamburgerPowerMenu(
                        this, this, onHamburgerItemClickListener, onHamburgerMenuDismissedListener);

        // Slide하여 fragment를 바꿈
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            // This method will be invoked when the current page is scrolled, either as part of a programmatically initiated smooth scroll or a user initiated touch scroll.
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);

                    // 해당하는 fragment에 따라 toolbar의 text 변경
                    // little me diary 제외하고 hamburgermenu 가리기
                    switch (position){
                        case 0:
                            img_hamburgerMenu.setVisibility(View.VISIBLE);
                            toolbar_text.setText("Little Me Diary");
                            break;

                        case 1:
                            img_hamburgerMenu.setVisibility(View.GONE);
                            toolbar_text.setText("Everytime FRIMO");
                            break;

                        case 2:
                            img_hamburgerMenu.setVisibility(View.GONE);
                            toolbar_text.setText("Friendly Community");
                            break;

                        case 3:
                            img_hamburgerMenu.setVisibility(View.GONE);
                            toolbar_text.setText("Trend Report");
                            break;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position);
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

    // hamburgermenu item click listener
    private final OnMenuItemClickListener<PowerMenuItem> onHamburgerItemClickListener =
            new OnMenuItemClickListener<PowerMenuItem>() {
                @Override
                public void onItemClick(int position, PowerMenuItem item) {
                    Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show(); // toast message
                    hamburgerMenu.setSelectedPosition(position); // menu에 선택된 항목으로 설정

                    // Todo: Menu 클릭 시 해당 Mode로 전환 및 toolbar text 변경
                    switch(position){
                        case 0:

                    }


                }
            };

    // hamburgermenu dismiss listener
    private final OnDismissedListener onHamburgerMenuDismissedListener =
            () -> Log.d("Test", "onDismissed hamburger menu");


}