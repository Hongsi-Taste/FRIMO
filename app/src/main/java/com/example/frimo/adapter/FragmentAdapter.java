package com.example.frimo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.frimo.mode.LittleMeDiaryMode;
import com.example.frimo.mode.TrendReportMode;
import com.example.frimo.fragment.EverytimeFRIMO;
import com.example.frimo.fragment.FriendlyCommunity;
import com.example.frimo.fragment.LittleMeDiaryFriendMode;
import com.example.frimo.fragment.LittleMeDiaryGalleryMode;
import com.example.frimo.fragment.LittleMeDiarySecretMode;
import com.example.frimo.fragment.TrendReport;
import com.example.frimo.fragment.TrendReportChangeInterest;

public class FragmentAdapter extends FragmentStateAdapter {

    private LittleMeDiaryMode littleMeDiaryMode;
    private TrendReportMode trendReportMode;

    // constructor
    public FragmentAdapter(FragmentActivity fa, LittleMeDiaryMode diaryMode, TrendReportMode reportMode) {
        super(fa);
        setLittleMeDiaryMode(diaryMode);
        setTrendReportMode(reportMode);
    }

    public void setLittleMeDiaryMode(LittleMeDiaryMode mode) {
        this.littleMeDiaryMode = mode;
    }
    public void setTrendReportMode(TrendReportMode mode) {
        this.trendReportMode = mode;
    }

    // fragment를 재생성하기 위해 아이디 수정
    @Override
    public long getItemId(int position) {
        if (position == 0) {
            switch (littleMeDiaryMode) {
                case FRIEND:
                    return 11;
                case SECRET:
                    return 12;
                default:
                    return 13;
            }
        }
        else if(position == 3){
            switch (trendReportMode) {
                case REPORT:
                    return 31;
                default:
                    return 32;
            }
        }
        else {
            return super.getItemId(position);
        }
    }

    // 현재 위치에 맞는 fragment 반환
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            switch (littleMeDiaryMode) {
                case FRIEND:
                    return new LittleMeDiaryFriendMode();
                case SECRET:
                    return new LittleMeDiarySecretMode();
                case GALLERY:
                    return new LittleMeDiaryGalleryMode();
            }
            return new LittleMeDiaryFriendMode();
        }
        else if(position==1) return new EverytimeFRIMO();
        else if(position==2) return new FriendlyCommunity();
        else {
            switch (trendReportMode){
                case REPORT:
                    return new TrendReport();
                case INTEREST:
                    return new TrendReportChangeInterest();
            }
            return new TrendReport();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}