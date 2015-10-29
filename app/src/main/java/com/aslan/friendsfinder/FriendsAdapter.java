package com.aslan.friendsfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aslan.friendsfinder.model.User;

/**
 * Created by gobinath on 10/23/15.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendViewHolder> {
    // Array of friends
    private User[] friends;

    public FriendsAdapter(User[] friends) {
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.layout_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        // Get the friend
        User friend = friends[position];
        // Display the friend
        holder.setFriend(friend);
    }

    @Override
    public int getItemCount() {
        return friends.length;
    }
}
