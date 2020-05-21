package io.agora.openlive.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

import io.agora.openlive.Constants;
import io.agora.openlive.bean.RoomBean;


public class PrefManager {
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String get(Context context, String key) {
        return getPreferences(context).getString(key, "");
    }

    public static void put(Context context, String key, List<RoomBean> value) {
        put(context, key, new Gson().toJson(value));
    }

    public static void put(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }
}
