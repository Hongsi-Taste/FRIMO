package com.example.frimo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.frimo.Mode
import com.example.frimo.fragment.LittleMeDiaryFriendMode
import com.example.frimo.fragment.LittleMeDiarySecretMode
import com.example.frimo.fragment.LittleMeDiaryGalleryMode
import com.example.frimo.fragment.EverytimeFRIMO
import com.example.frimo.fragment.FriendlyCommunity
import com.example.frimo.fragment.TrendReport

class MyAdapter(fa: FragmentActivity?, mode: Mode?) : FragmentStateAdapter(fa!!) {
    private var mode: Mode? = null
    fun setMode(mode: Mode?) {
        this.mode = mode
    }

    // 0번째 fragment를 재생성하기 위해 각각 아이디가 달라야 한다
    override fun getItemId(position: Int): Long {
        return if (position == 0) {
            when (mode) {
                Mode.FRIEND -> 11
                Mode.SECRET -> 12
                else -> 13
            }
        } else {
            super.getItemId(position)
        }
    }

    // 현재 위치에 맞는 fragment 반환
    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            when (mode) {
                Mode.FRIEND -> return LittleMeDiaryFriendMode()
                Mode.SECRET -> return LittleMeDiarySecretMode()
                Mode.GALLERY -> return LittleMeDiaryGalleryMode()
            }
            LittleMeDiaryFriendMode()
        } else if (position == 1) EverytimeFRIMO() else if (position == 2) FriendlyCommunity() else TrendReport()
    }

    override fun getItemCount(): Int {
        return 4
    }

    // constructor
    init {
        setMode(mode)
    }
}
