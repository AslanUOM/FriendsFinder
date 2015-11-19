package com.aslan.friendsfinder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aslan.friendsfinder.utility.Constants;
import com.aslan.friendsfinder.utility.IntentCreator;

public class MsgPassingActivity extends AppCompatActivity {
    boolean mIsBinded;
    Messenger mMessenger;
    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // TODO Auto-generated method stub
            mIsBinded = false;
            mServiceConnection = null;
        }

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            // TODO Auto-generated method stub
            mIsBinded = true;
            mMessenger = new Messenger(arg1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_passing);
        Intent mIntent = new Intent();
        mIntent.setAction(Constants.CONTRA_PLUGIN_ACTION_NAME);
        mIntent = IntentCreator.createExplicitFromImplicitIntent(getApplicationContext(), mIntent); //solution for failure above android 5.0
        bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
        Button mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Message msg = Message.obtain(null, Constants.MessagePassingCommands.START_LOCATION_TRACKING, 0, 0);
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Message msg = Message.obtain(null, Constants.MessagePassingCommands.STOP_LOCATION_TRACKING, 0, 0);
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Message msg = Message.obtain(null, Constants.MessagePassingCommands.GET_ALL_CONTACTS, 0, 0);
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Button mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Message msg = Message.obtain(null, Constants.MessagePassingCommands.EXPORT_LOCATION_DATA_TO_SD_CARD, 0, 0);
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}