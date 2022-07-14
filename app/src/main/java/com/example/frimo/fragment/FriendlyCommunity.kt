package com.example.frimo.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.frimo.R
import com.example.frimo.data.User


class FriendlyCommunity : Fragment() {
    val datas = mutableListOf<User>()

    // Fragment를 만들고 레이아웃이랑 연결
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initData() // 잘 나오는지 보려고
        return inflater.inflate(R.layout.friendly_community, container, false) as ViewGroup
    }

    private fun initData() {
        datas.apply {
            add(User(image = R.drawable.img_user, name = "mary", desc = "asdfasdfasdf"))
            add(User(image = R.drawable.img_user, name = "jenny", desc = "asdfasdfasdf"))
            add(User(image = R.drawable.img_user, name = "jhon", desc = "asdfasdfasdf"))
            add(User(image = R.drawable.img_user, name = "ruby", desc = "asdfasdfasdf"))
            add(User(image = R.drawable.img_user, name = "yuna", desc = "asdfasdfasdf"))
        }


    }
}