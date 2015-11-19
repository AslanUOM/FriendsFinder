package com.aslan.friendsfinder.utility;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

/**
 * Created by Vishnuvathsasarma on 31-Oct-15.
 */
public class PluginChecker {
    private static String pluginPKG = "com.aslan.contra";

    public static boolean isPluginInstalled(Context ctx, String TAG) {
        ContextWrapper ctxWrapper = new ContextWrapper(ctx);
        PackageManager packageManager = ctxWrapper.getPackageManager();
        //                try {
        List<ApplicationInfo> infos = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : infos) {
            if (info.packageName.equalsIgnoreCase(pluginPKG)) {
                Log.d(TAG, info.packageName);
                Log.d(TAG, pluginPKG);
                return true;
            }
        }
        return false;
    }
}
