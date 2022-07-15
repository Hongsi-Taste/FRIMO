package com.example.frimo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;
import com.example.frimo.User;
import com.example.frimo.adapter.FriendlyCommunityAdapter;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

import java.util.ArrayList;

public class FriendlyCommunity extends Fragment {

    // Recyclerview
    private ArrayList<User> users = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendlyCommunityAdapter mAdapter;

    // Profile Ballon
    private ImageView profile;
    private Balloon profileBalloon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
    }

    private void prepareData() {
        users.add(new User(R.drawable.img_user, "User1", "I am user1"));
        users.add(new User(R.drawable.img_user, "User2", "I love coffee"));
        users.add(new User(R.drawable.img_user, "User3", "I love exercise"));
        users.add(new User(R.drawable.img_user, "User4", "I want to go home"));
        users.add(new User(R.drawable.img_user, "User5", "I want to go Europe"));
        users.add(new User(R.drawable.img_user, "User6", "I want to go Europe"));
        users.add(new User(R.drawable.img_user, "User7", "Buy Me coffee"));
        users.add(new User(R.drawable.img_user, "User8", "Help me~~"));
        users.add(new User(R.drawable.img_user, "User9", "Help me god...."));
        users.add(new User(R.drawable.img_user, "User10", "Help me plz"));
        users.add(new User(R.drawable.img_user, "User11", "Help me please"));
        users.add(new User(R.drawable.img_user, "User12", "Match"));
        users.add(new User(R.drawable.img_user, "User13", "Parent?"));
    }

    // Fragment를 만들고 레이아웃이랑 연결
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.friendly_community, container, false);

        //recyclerview
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mAdapter = new FriendlyCommunityAdapter(users);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // Profile
        profileBalloon= new Balloon
                .Builder(requireContext()) // getContext()와 달리 NonNull 값을 받아옴
                .setLayout(R.layout.friendly_community_custom_profile)
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setArrowPosition(0.5f)
                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                .setBalloonAnimation(BalloonAnimation.CIRCULAR)
                .setLifecycleOwner(this)
                .build();

        profile = rootView.findViewById(R.id.circleImageView);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileBalloon.showAlignBottom(profile);
            }
        });

        return rootView;
    }

}