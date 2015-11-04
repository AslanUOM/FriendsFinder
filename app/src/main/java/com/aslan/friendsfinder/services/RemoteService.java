package com.aslan.friendsfinder.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class RemoteService extends Service {
    private static final int SAY_BYE = 0;
    private static final int SAY_SEE_YOU = 1;
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
                case SAY_BYE:
                    Toast.makeText(getApplicationContext(), "Bye from PLUGIN @ APP", Toast.LENGTH_LONG).show();
                    break;
                case SAY_SEE_YOU:
                    Toast.makeText(getApplicationContext(), "See you from PLUGIN @ APP", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}