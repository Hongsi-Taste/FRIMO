package com.example.frimo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.frimo.Mode;
import com.example.frimo.fragment.EverytimeFRIMO;
import com.example.frimo.fragment.FriendlyCommunity;
import com.example.frimo.fragment.LittleMeDiaryFriendMode;
import com.example.frimo.fragment.LittleMeDiaryGalleryMode;
import com.example.frimo.fragment.LittleMeDiarySecretMode;
import com.example.frimo.fragment.TrendReport;

public class MyAdapter extends FragmentStateAdapter {

    private Mode mode;

    // constructor
    public MyAdapter(FragmentActivity fa, Mode mode) {
        super(fa);
        setMode(mode);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    // 0번째 fragment를 재생성하기 위해 각각 아이디가 달라야 한다
    @Override
    public long getItemId(int position) {
        if (position == 0) {
            switch (mode) {
                case FRIEND:
                    return 11;
                case SECRET:
                    return 12;
                default:
                    return 13;
            }
        } else {
            return super.getItemId(position);
        }
    }

    // 현재 위치에 맞는 fragment 반환
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            switch (mode) {
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
        else return new TrendReport();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}