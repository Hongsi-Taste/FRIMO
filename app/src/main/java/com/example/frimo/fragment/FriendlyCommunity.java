package com.example.frimo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

    // Profile
    private ImageView profile;
    private Balloon profileBalloon;
    private Button edit;

    // Friend Candidate (친구 추천)
    private Button friendCandidate;

    private void prepareData() {
        // Todo: 서버랑 연결해 User data 받아오기

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

        // RecyclerView에 보여 줄 User 받아오기
        prepareData();

        //Recyclerview
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mAdapter = new FriendlyCommunityAdapter(users);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // Profile balloon
        profileBalloon= new Balloon
                .Builder(requireContext()) // getContext()와 달리 NonNull 값을 받아옴
                .setLayout(R.layout.friendly_community_custom_profile) // popup content 설정
                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black)) // Background color 설정. 여기선 화살표 color 설정
                .setBalloonAnimation(BalloonAnimation.CIRCULAR) // Balloon animation 설정
                .setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
                .setOverlayColorResource(R.color.overlay) // background color of the overlay using a color resource.
                .setOverlayPadding(3f) // profile 화면을 감싸는 동그란 선의 굵기
                .setOverlayPaddingColorResource(R.color.black) // profile 화면 밖에 뜨는 동그란 선의 색
                .build();

        // Profile click event
        profile = rootView.findViewById(R.id.circleImageView);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileBalloon.showAlignBottom(profile);
            }
        });

        // Edit button (profile click 이후 나오는 button)
        edit = profileBalloon.getContentView().findViewById(R.id.button_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileBalloon.dismiss();
                Toast.makeText(requireContext(), "Edit button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Friend Candidate button
        friendCandidate = rootView.findViewById(R.id.btn_friend_candidate);
        friendCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Friend Candidate button clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

}