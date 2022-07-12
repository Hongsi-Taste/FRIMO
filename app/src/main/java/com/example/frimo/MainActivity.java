package com.example.frimo;

import androidx.fragment.app.FragmentActivity;
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

    // Slide fragement
    private ViewPager2 mPager;
    private MyAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    // Tool bar
    private ImageView menu;
    private PowerMenu hamburgerMenu;
    private TextView toolbar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager2
        mPager = findViewById(R.id.viewpager);

        // Adapter
        pagerAdapter = new MyAdapter(this, Mode.FRIEND);
        mPager.setAdapter(pagerAdapter);

        // Indicator (아래 동그란 거 4개)
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);

        // ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(0); // 현재 위치는 little me diary
        mPager.setOffscreenPageLimit(3);

        // Tool bar
        menu = findViewById(R.id.menu);
        toolbar_text = findViewById(R.id.toolbar_text);
        hamburgerMenu =
                PowerMenuUtils.getHamburgerPowerMenu(
                        this, this, onHamburgerItemClickListener, onHamburgerMenuDismissedListener);

        // Slide 하여 fragment를 바꿈
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            // This method will be invoked when the current page is scrolled, either as part of a programmatically initiated smooth scroll or a user initiated touch scroll.
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);

                    // Fragment가 변경됨에 따라 tool bar의 text 변경
                    // Little me diary 제외하고 menu 가리기
                    switch (position) {
                        case 0:
                            menu.setVisibility(View.VISIBLE);
                            toolbar_text.setText(R.string.little_me_diary);
                            break;

                        case 1:
                            menu.setVisibility(View.GONE);
                            toolbar_text.setText(R.string.everytime_frimo);
                            break;

                        case 2:
                            menu.setVisibility(View.GONE);
                            toolbar_text.setText(R.string.friendly_community);
                            break;

                        case 3:
                            menu.setVisibility(View.GONE);
                            toolbar_text.setText(R.string.trend_report);
                            break;
                    }
                }
            }

            // Fragment가 변경됨에 따라 indicator 수정
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

    // hamburgermenu가 띄워져 있는 상태에서 뒤로가기 버튼이 눌렀을 때 끄기
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

                    // Hamburgermenu에서 Mode 클릭 시 fragment 전환 및 text 변경
                    switch (position) {
                        case 0:
                            toolbar_text.setText(R.string.friend_mode);
                            pagerAdapter.setMode(Mode.FRIEND);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 1:
                            toolbar_text.setText(R.string.secret_mode);
                            pagerAdapter.setMode(Mode.SECRET);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 2:
                            toolbar_text.setText(R.string.gallery_mode);
                            pagerAdapter.setMode(Mode.GALLERY);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            };

    // hamburgermenu dismiss listener
    private final OnDismissedListener onHamburgerMenuDismissedListener =
            () -> Log.d("Test", "onDismissed hamburger menu");


}