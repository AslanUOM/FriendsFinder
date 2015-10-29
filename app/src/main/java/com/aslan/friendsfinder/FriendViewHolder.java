package com.aslan.friendsfinder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aslan.friendsfinder.model.User;

/**
 * Created by gobinath on 10/23/15.
 */
public class FriendViewHolder extends RecyclerView.ViewHolder {
    // UI components
    private TextView txtName;
    private TextView txtStatus;

    // User object assigned to this item
    private User user;

    public FriendViewHolder(View itemView) {
        super(itemView);

        // Find the UI components
        this.txtName = (TextView) itemView.findViewById(R.id.txtName);
        this.txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
    }

    /**
     * Set a friend to the view holder.
     *
     * @param friend the friend
     */
    public void setFriend(User friend) {
        this.user = friend;
        txtName.setText(friend.getName());
        txtStatus.setText(friend.getStatus());
    }
}
