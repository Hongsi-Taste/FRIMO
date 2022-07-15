package com.example.frimo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;
import com.example.frimo.User;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.ArrowPositionRules;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.BalloonSizeSpec;
import com.skydoves.balloon.IconGravity;

import java.util.ArrayList;

public class FriendlyCommunityAdapter extends RecyclerView.Adapter<FriendlyCommunityAdapter.ViewHolder> {
    private ArrayList<User> users;

    // User
    private Balloon userBalloon;

    // Constructor
    public FriendlyCommunityAdapter(ArrayList<User> dataSet) {
        users = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textview_name;
        private final TextView textview_desc;
        private final ImageView image;

        // User Balloon
        private Balloon userBalloon;

        public ViewHolder(View view) {
            super(view);

            // findViewById
            textview_name = (TextView) view.findViewById(R.id.sample0_name);
            textview_desc = (TextView) view.findViewById(R.id.sample0_description);
            image = (ImageView) view.findViewById(R.id.sample0_img);

            // User balloon
            userBalloon = new Balloon
                    .Builder(view.getContext())
                    .setText("Want to see where he is living?") // 보여줄 Text
                    .setTextSize(15) // Text size
                    .setPadding(10) // Text와 테두리 사이의 간격 설정
                    .setIconDrawableResource(R.drawable.ic_gps) // GPS Icon
                    .setIconGravity(IconGravity.START) // Icon 위치 설정
                    .setDismissWhenClicked(false) // userBalloon이 뜨고 Balloon을 클릭 시 dismiss 할지 여부
                    .setBalloonAnimation(BalloonAnimation.CIRCULAR) // Balloon animation
                    .setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.black)) // Background color 설정
                    .build();

            // View click listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userBalloon.showAlignBottom(view); // user balloon 보여주기

                    // Todo: GPS Icon 눌렀을 때 지도 보여주기
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.friendly_community_user, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textview_name.setText(users.get(position).getName());
        viewHolder.textview_desc.setText(users.get(position).getDesc());
        viewHolder.image.setImageResource(R.drawable.img_user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
