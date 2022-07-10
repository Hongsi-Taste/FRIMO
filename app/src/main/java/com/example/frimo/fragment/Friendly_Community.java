package com.example.frimo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.frimo.R;

public class Friendly_Community extends Fragment {

    // Fragment를 만들고 레이아웃이랑 연결
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.friendly_community, container, false);

        return rootView;
    }

}