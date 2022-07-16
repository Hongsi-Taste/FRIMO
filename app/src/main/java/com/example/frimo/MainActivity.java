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


public class MainActivity extends FragmentActivity {

    // Slide fragement
    private ViewPager2 mPager;
    private FragmentAdapter pagerAdapter;
    private int num_page = 4;

    // Tool bar
    private ImageView icon;
    private PowerMenu changeModeMenu;
    private PowerMenu trendReportMenu;
    private TextView textview_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariable();

        // Slide 하여 fragment를 바꿈
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            // This method will be invoked when the current page is scrolled, either as part of a programmatically initiated smooth scroll or a user initiated touch scroll.
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);

                    // Fragment가 변경됨에 따라 tool bar 변경 (text, icon)
                    switch (position) {
                        case 0:
                            icon.setVisibility(View.VISIBLE);
                            icon.setImageResource(R.drawable.ic_list);
                            icon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    changeModeMenu.showAsDropDown(view);
                                }
                            });

                            textview_toolbar.setText(R.string.little_me_diary);
                            break;

                        case 1:
                            icon.setVisibility(View.GONE);
                            textview_toolbar.setText(R.string.everytime_frimo);
                            break;

                        case 2:
                            icon.setVisibility(View.GONE);
                            textview_toolbar.setText(R.string.friendly_community);
                            break;

                        case 3:
                            icon.setVisibility(View.VISIBLE);
                            icon.setImageResource(R.drawable.ic_setting);
                            icon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    trendReportMenu.showAsDropDown(view);
                                }
                            });

                            textview_toolbar.setText(R.string.trend_report);
                            break;
                    }
                }
            }

        });


    }

    // Menu가 띄워져 있는 상태에서 뒤로가기 버튼이 눌렸을 경우
    @Override
    public void onBackPressed() {
        if (changeModeMenu.isShowing()) {
            changeModeMenu.dismiss();
        }

        if (trendReportMenu.isShowing()) {
            trendReportMenu.dismiss();
        }
    }

    // change mode menu item click listener
    private final OnMenuItemClickListener<PowerMenuItem> changeModeMenuItemClickListener =
            new OnMenuItemClickListener<PowerMenuItem>() {
                @Override
                public void onItemClick(int position, PowerMenuItem item) {

                    Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    changeModeMenu.setSelectedPosition(position); // 선택한 항목으로 menu 설정

                    // Click된 항목에 따라 fragment 전환 및 tool bar text 변경
                    switch (position) {
                        case 0:
                            textview_toolbar.setText(R.string.friend_mode);
                            pagerAdapter.setLittleMeDiaryMode(LittleMeDiaryMode.FRIEND);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 1:
                            textview_toolbar.setText(R.string.secret_mode);
                            pagerAdapter.setLittleMeDiaryMode(LittleMeDiaryMode.SECRET);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 2:
                            textview_toolbar.setText(R.string.gallery_mode);
                            pagerAdapter.setLittleMeDiaryMode(LittleMeDiaryMode.GALLERY);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            };

    // Trend report item click listener
    private final OnMenuItemClickListener<PowerMenuItem> trendReportClickListener =
            new OnMenuItemClickListener<PowerMenuItem>() {
                @Override
                public void onItemClick(int position, PowerMenuItem item) {

                    Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    trendReportMenu.setSelectedPosition(position); // 선택한 항목으로 menu 설정

                    // Click된 항목에 따라 fragment 전환 및 tool bar text 변경
                    switch (position) {
                        case 0:
                            textview_toolbar.setText(R.string.trend_report);
                            pagerAdapter.setTrendReportMode(TrendReportMode.REPORT);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                        case 1:
                            textview_toolbar.setText("Change Interest");
                            pagerAdapter.setTrendReportMode(TrendReportMode.INTEREST);
                            pagerAdapter.notifyDataSetChanged();
                            break;

                    }
                }
            };

    private void setVariable() {

        // ViewPager2
        mPager = findViewById(R.id.viewpager);

        // Adapter
        pagerAdapter = new FragmentAdapter(this, LittleMeDiaryMode.FRIEND, TrendReportMode.REPORT);
        mPager.setAdapter(pagerAdapter);

        // ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(0); // 현재 위치는 little me diary
        mPager.setOffscreenPageLimit(3);

        // Tool bar
        icon = findViewById(R.id.ic_menu);
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
                .setOnMenuItemClickListener(changeModeMenuItemClickListener)
                .build();

        trendReportMenu = new PowerMenu.Builder(this)
                .addItem(new PowerMenuItem("Trend Report", true))
                .addItem(new PowerMenuItem("Change Interest", false))
                .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT) // popup시 menu가 뜨는 위치
                .setTextColor(Color.BLACK)
                .setTextGravity(Gravity.CENTER) // 글자 위치
                .setSelectedMenuColor(Color.BLACK)
                .setSelectedTextColor(Color.WHITE)
                .setOnMenuItemClickListener(trendReportClickListener)
                .build();

    }
}
