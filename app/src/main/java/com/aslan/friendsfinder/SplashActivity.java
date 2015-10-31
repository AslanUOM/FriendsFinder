package com.aslan.friendsfinder;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Show the splash screen

        String pluginPKG = "com.aslan.contra";
        boolean isPluginInstalled = false;
        PackageManager packageManager = getPackageManager();
//                try {
        List<ApplicationInfo> infos = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : infos) {
            if (info.packageName == pluginPKG) {
                isPluginInstalled = true;
                break;
            }
        }
//                    ApplicationInfo info = packageManager.getApplicationInfo("com.aslan.contra", 0);
        //-- application exists
        if (isPluginInstalled) {
            Class target = MainActivity.class;
            Intent intent = new Intent(getApplicationContext(), target);
            // Wait for a while
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Sleep is interrupted", e);
            }
            startActivity(intent);
        } else {
            Log.e(TAG, "ConTra plugin missing");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.aslan.contra"));
//            intent.setData(Uri.parse("market://details?id=com.aslan.contra"));
            startActivity(intent);
        }
//                } catch (PackageManager.NameNotFoundException e) {
//                    //-- application doesn't exist
//                    Log.e(TAG, "ConTra plugin missing", e);
//                    Toast.makeText(SplashActivity.this, "Install the ConTra plugin first", Toast.LENGTH_LONG).show();
//                }

    }
}
