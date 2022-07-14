package com.example.frimo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;
import com.example.frimo.User;

import java.util.ArrayList;

public class FriendlyCommunityAdapter extends RecyclerView.Adapter<FriendlyCommunityAdapter.ViewHolder>{
    private ArrayList<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textview_name;
        private final TextView textview_desc;
        private final ImageView image;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textview_name = (TextView) view.findViewById(R.id.sample0_name);
            textview_desc = (TextView) view.findViewById(R.id.sample0_description);
            image = (ImageView) view.findViewById(R.id.sample0_img);
        }
    }

    // constructor
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet ArrayList<User> containing the data to populate views to be used by RecyclerView.
     */
    public FriendlyCommunityAdapter(ArrayList<User> dataSet) {
        users = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.friendly_community_user, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view with that element
        viewHolder.textview_name.setText(users.get(position).getName());
        viewHolder.textview_desc.setText(users.get(position).getDesc());
        viewHolder.image.setImageResource(R.drawable.img_user);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }

}
