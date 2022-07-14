package com.example.frimo.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.frimo.R

class LittleMeDiaryGalleryMode : Fragment() {
    // Fragment를 만들고 레이아웃이랑 연결
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.little_me_diary_gallery_mode, container, false
        ) as ViewGroup
    }
}