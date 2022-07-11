package com.example.frimo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.frimo.fragment.Everytime_FRIMO;
import com.example.frimo.fragment.Friendly_Community;
import com.example.frimo.fragment.Little_Me_Diary_Friend_Mode;
import com.example.frimo.fragment.Trend_Report;

public class MyAdapter extends FragmentStateAdapter {

    public int mCount; // 생성할 fragment 갯수

    // constructor
    public MyAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    // 현재 위치에 맞는 fragment 반환
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0) return new Little_Me_Diary_Friend_Mode();
        else if(position==1) return new Everytime_FRIMO();
        else if(position==2) return new Friendly_Community();
        else return new Trend_Report();

    }

    @Override
    public int getItemCount() {
        return 4;
    }

}