package com.example.frimo;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.adapter.FragmentAdapter;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import me.relex.circleindicator.CircleIndicator3;


public class MainActivity extends FragmentActivity {

    // Slide fragement
    private ViewPager2 mPager;
    private FragmentAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    // Tool bar
    private ImageView ic_menu;
    private PowerMenu changeModeMenu;
    private TextView textview_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager2
        mPager = findViewById(R.id.viewpager);

        // Adapter
        pagerAdapter = new FragmentAdapter(this, Mode.FRIEND);
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
        ic_menu = findViewById(R.id.ic_menu);
        textview_toolbar = findViewById(R.id.textview_toolbar);
        changeModeMenu = new PowerMenu.Builder(this)
                .addItem(new PowerMenuItem("Friend Mode", true))
                .addItem(new PowerMenuItem("Secret Mode", false))
                .addItem(new PowerMenuItem("Gallery Mode", false))
                .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT) // popup시 menu가 뜨는 위치
                .setTextColor(Color.BLACK)
                .setTextGravity(Gravity.CENTER) // 글자 위치
                .setSelectedMenuColor(Color.BLACK)
                .setSelectedTextColor(Color.WHITE)
                .setOnMenuItemClickListener(onHamburgerItemClickListener)
                .build();

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
                            ic_menu.setVisibility(View.VISIBLE);
                            textview_toolbar.setText(R.string.little_me_diary);
                            break;

                        case 1:
                            ic_menu.setVisibility(View.GONE);
                            textview_toolbar.setText(R.string.everytime_frimo);
                            break;

                        case 2:
                            ic_menu.setVisibility(View.GONE);
                            textview_toolbar.setText(R.string.friendly_community);
                            break;

                        case 3:
                            ic_menu.setVisibility(View.GONE);
                            textview_toolbar.setText(R.string.trend_report);
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
        if (changeModeMenu.isShowing()) {
            changeModeMenu.dismiss();
            return;
        }
        changeModeMenu.showAsDropDown(view);
    }

    // hamburgermenu가 띄워져 있는 상태에서 뒤로가기 버튼이 눌렀을 때 끄기
    @Override
    public void onBackPressed() {
        if (changeModeMenu.isShowing()) {
            changeModeMenu.dismiss();
        }
    }

    // hamburgermenu item click listener
    private final OnMenuItemClickListener<PowerMenuItem> onHamburgerItemClickListener =
            new OnMenuItemClickListener<PowerMenuItem>() {
                @Override
                public void onItemClick(int position, PowerMenuItem item) {

                    Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show(); // toast message
                    changeModeMenu.setSelectedPosition(position); // menu에 선택된 항목으로 설정

                    // Hamburgermenu에서 Mode 클릭 시 fragment 전환 및 text 변경
                    switch (position) {
                        case 0:
                            textview_toolbar.setText(R.string.friend_mode);
                            pagerAdapter.setMode(Mode.FRIEND);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 1:
                            textview_toolbar.setText(R.string.secret_mode);
                            pagerAdapter.setMode(Mode.SECRET);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 2:
                            textview_toolbar.setText(R.string.gallery_mode);
                            pagerAdapter.setMode(Mode.GALLERY);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            };

}
