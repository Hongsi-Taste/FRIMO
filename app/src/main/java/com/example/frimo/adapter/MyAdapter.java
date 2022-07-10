package com.example.frimo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.frimo.fragment.Everytime_FRIMO;
import com.example.frimo.fragment.Friendly_Community;
import com.example.frimo.fragment.Little_Me_Diary;
import com.example.frimo.fragment.Trend_Report;

public class MyAdapter extends FragmentStateAdapter {

    public int mCount; // 생성할 fragment 갯수

    public MyAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    // fragment 생성
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Little_Me_Diary();
        else if(index==1) return new Everytime_FRIMO();
        else if(index==2) return new Friendly_Community();
        else return new Trend_Report();

    }

    // 프래그먼트를 무한으로 슬라이딩하기위해 getItemCount()는 2000으로 임의로 설정. (2000번의 슬라이딩)
    @Override
    public int getItemCount() {
        return 2000;
    }

    // position에서 프래그먼트 갯수로 나눈 나머지값이 진짜 position (슬라이드 4개이므로 0, 1, 2, 3이 반복)
    public int getRealPosition(int position) { return position % mCount; }

}