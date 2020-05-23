package io.agora.openlive.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.agora.openlive.Constants;
import io.agora.openlive.bean.Message;
import io.agora.openlive.bean.RoomBean;


public class PrefManager {

    public static final String ROOM_LIST = "room_list";
    public static final String MESSAGE_LIST = "message_list";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String get(Context context, String key) {
        return getPreferences(context).getString(key, "");
    }

    public static void put(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void addRoom(Context context, RoomBean bean) {
        List<RoomBean> list = getRoomList(context);
        list.add(bean);
        put(context, ROOM_LIST, new Gson().toJson(list));
    }

    public static List<RoomBean> getRoomList(Context context) {
        String liveJson = PrefManager.get(context, ROOM_LIST);
        List<RoomBean> updateData = new Gson().fromJson(liveJson, new TypeToken<List<RoomBean>>() {
        }.getType());
        return updateData;
    }

    public static void saveRoomList(Context context, List<RoomBean> data) {
        String list = new Gson().toJson(data);
        put(context, ROOM_LIST, list);
    }

    public static List<Message> getMessageList(Context context) {
        String liveJson = PrefManager.get(context, MESSAGE_LIST);
        List<Message> updateData = new Gson().fromJson(liveJson, new TypeToken<List<Message>>() {
        }.getType());
        return updateData;
    }
}
