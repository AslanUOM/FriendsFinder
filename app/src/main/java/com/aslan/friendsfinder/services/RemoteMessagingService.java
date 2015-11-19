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

public class RemoteMessagingService extends Service {
    private Messenger receiver;
    private Messenger sender;
    private boolean senderIsBinded;
    private ServiceConnection messengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("RemoteService", "onServiceConnected");
            Log.d("RemoteService", name.toString());
            Log.d("RemoteService", service.toString());
            sender = new Messenger(service);
            senderIsBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("RemoteService", "onServiceDisconnected");
            Log.d("RemoteService", name.toString());
            sender = null;
            senderIsBinded = false;
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        receiver = new Messenger(new IncomingMessageHandler());
        return receiver.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent mIntent = new Intent();
        mIntent.setAction(Constants.CONTRA_PLUGIN_ACTION_NAME); //TODO change the actual action name logical
        mIntent = IntentCreator.createExplicitFromImplicitIntent(getApplicationContext(), mIntent); //solution for failure above android 5.0
        bindService(mIntent, messengerServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            switch (bundle.getString(Constants.BUNDLE_TYPE)) {
                case Constants.Type.NEARBY_FRIENDS:
                    getNearbyFriends();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void getNearbyFriends() {
//        Intent mIntent = new Intent();
//        mIntent.setAction(Constants.CONTRA_PLUGIN_ACTION_NAME); //TODO change the actual action name logical
//        mIntent = IntentCreator.createExplicitFromImplicitIntent(getApplicationContext(), mIntent); //solution for failure above android 5.0
//        bindService(mIntent, messengerServiceConnection, BIND_AUTO_CREATE);

        Message msg = Message.obtain(null, Constants.MessagePassingCommands.GET_NEARBY_FRIENDS, 0, 0);
        msg.replyTo = receiver;
        try {
            if (senderIsBinded) {
                sender.send(msg);
            } else {
                Log.d("remote", "sender is not bind");
//                bindService(mIntent, messengerServiceConnection, BIND_AUTO_CREATE);
//                sender.send(msg);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class IncomingMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MessagePassingCommands.NEARBY_FRIENDS_RECEIVED:
                    Bundle bundle = (Bundle) msg.obj;
                    Utility.saveNearbyFriends(getApplicationContext(), bundle.getString(Constants.Type.NEARBY_FRIENDS));
                    Toast.makeText(getApplicationContext(), bundle.getString(Constants.Type.NEARBY_FRIENDS) + " is nearby now", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}