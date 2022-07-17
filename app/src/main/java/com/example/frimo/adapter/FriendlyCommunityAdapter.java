package com.example.frimo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;
import com.example.frimo.domain.User;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.IconGravity;
import com.skydoves.balloon.OnBalloonClickListener;

import java.util.ArrayList;

public class FriendlyCommunityAdapter extends RecyclerView.Adapter<FriendlyCommunityAdapter.ViewHolder> {

    // User data
    private ArrayList<User> users;

    // Constructor
    public FriendlyCommunityAdapter(ArrayList<User> dataSet) {
        users = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textview_name;
        private final TextView textview_desc;
        private final ImageView image;


        public ViewHolder(View view) {

            super(view);

            // findViewById
            textview_name = (TextView) view.findViewById(R.id.sample0_name);
            textview_desc = (TextView) view.findViewById(R.id.sample0_description);
            image = (ImageView) view.findViewById(R.id.sample0_img);

            // User balloon
            Balloon userBalloon = new Balloon
                    .Builder(view.getContext())
                    .setText("Click to see where this user lives") // 보여줄 Text
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

                    // show userBalloon
                    userBalloon.showAlignBottom(view);

                    // userBalloon click listener
                    userBalloon.setOnBalloonClickListener(new OnBalloonClickListener() {
                        @Override
                        public void onBalloonClick(@NonNull View view) {

                            // Todo: 어디에 사는지 보여주기 (지도)
                            Toast.makeText(view.getContext(), "Balloon clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
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
