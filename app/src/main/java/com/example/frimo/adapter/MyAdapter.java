package com.example.frimo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.frimo.fragment.EverytimeFRIMO;
import com.example.frimo.fragment.FriendlyCommunity;
import com.example.frimo.fragment.LittleMeDiaryFriendMode;
import com.example.frimo.fragment.TrendReport;

public class MyAdapter extends FragmentStateAdapter {

    // constructor
    public MyAdapter(FragmentActivity fa) {
        super(fa);
    }

    // 현재 위치에 맞는 fragment 반환
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0) return new LittleMeDiaryFriendMode();
        else if(position==1) return new EverytimeFRIMO();
        else if(position==2) return new FriendlyCommunity();
        else return new TrendReport();

    }

    @Override
    public int getItemCount() {
        return 4;
    }

}