package com.aslan.friendsfinder.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vishnuvathsasarma on 07-Nov-15.
 */
public class Utility {
    private static String nearbyFriends;

    public static SharedPreferences getSharedPreference(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("com.aslan.contra", Context.MODE_PRIVATE);
        return preferences;
    }

    public static String getNearbyFriends(Context ctx) {
        if (nearbyFriends == null) {
            SharedPreferences preferences = getSharedPreference(ctx);
            nearbyFriends = preferences.getString(Constants.NEARBY_FRIENDS, null);
        }
        return nearbyFriends;
    }

    public static void saveNearbyFriends(Context ctx, String nearby) {
        SharedPreferences preferences = getSharedPreference(ctx);
        nearbyFriends = nearby;
        preferences.edit().putString(Constants.NEARBY_FRIENDS, nearbyFriends).commit();
    }
}
