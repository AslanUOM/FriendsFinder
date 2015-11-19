package com.aslan.friendsfinder.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.aslan.friendsfinder.utility.Constants;
import com.aslan.friendsfinder.utility.IntentCreator;
import com.aslan.friendsfinder.utility.Utility;

public class RemoteMessagingService extends Service implements ServiceConnection {
    private static final String TAG = "RemoteMessagingService";

    private Messenger receiver;
    private Messenger sender;
    private Intent intent;


    @Override
    public IBinder onBind(Intent arg0) {
        receiver = new Messenger(new IncomingMessageHandler());
        return receiver.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            this.intent = intent;

            Intent bindingIntent = new Intent();
            bindingIntent.setAction(Constants.CONTRA_PLUGIN_ACTION_NAME); //TODO change the actual action name logical
            bindingIntent = IntentCreator.createExplicitFromImplicitIntent(getApplicationContext(), bindingIntent); //solution for failure above android 5.0
            bindService(bindingIntent, this, BIND_AUTO_CREATE);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getNearbyFriends() {
        Log.i(TAG, "Sending message");
        Message msg = Message.obtain(null, Constants.MessagePassingCommands.GET_NEARBY_FRIENDS);
        msg.replyTo = new Messenger(new IncomingMessageHandler());
        try {
            sender.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(TAG, "Service is connected.");
        sender = new Messenger(service);

        // Extract the information
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            switch (bundle.getString(Constants.BUNDLE_TYPE)) {
                case Constants.Type.NEARBY_FRIENDS:
                    getNearbyFriends();
                    break;
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG, "Service is disconnected.");
        sender = null;
    }


    private class IncomingMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, msg.toString());
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MessagePassingCommands.NEARBY_FRIENDS_RECEIVED:
                    Bundle bundle = msg.getData();
                    Utility.saveNearbyFriends(getApplicationContext(), bundle.getString(Constants.Type.NEARBY_FRIENDS));
                    Toast.makeText(getApplicationContext(), bundle.getString(Constants.Type.NEARBY_FRIENDS) + " is nearby now", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}