package com.aslan.friendsfinder.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import com.aslan.friendsfinder.utility.Constants;
import com.aslan.friendsfinder.utility.Utility;

public class RemoteService extends Service {
    Messenger mMessenger = new Messenger(new RemoteServiceHandler());

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return mMessenger.getBinder();
    }

    private class RemoteServiceHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MessagePassingCommands.NEARBY_FRIENDS_RECEIVED:
                    Bundle bundle = (Bundle) msg.obj;
                    Utility.saveNearbyFriends(getApplicationContext(), bundle.getString(Constants.NEARBY_FRIENDS));
                    Toast.makeText(getApplicationContext(), bundle.getString(Constants.NEARBY_FRIENDS) + " is nearby now", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}