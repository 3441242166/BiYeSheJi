package io.agora.openlive.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;

import io.agora.openlive.R;
import io.agora.openlive.activities.DataActivity;
import io.agora.openlive.activities.HistoryActivity;
import io.agora.openlive.activities.LoginActivity;
import io.agora.openlive.activities.MessageDetailActivity;
import io.agora.openlive.activities.SettingsActivity;
import io.agora.openlive.bean.Message;
import io.agora.openlive.event.MessageListUpdateEvent;
import io.agora.openlive.utils.PrefManager;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;


public class UserMessageFragment extends LazyLoadFragment {
    private static final String TAG = "UserMessageFragment";


    @Override
    protected int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {
        findViewById(R.id.history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        findViewById(R.id.data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DataActivity.class));
            }
        });
        findViewById(R.id.debug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotificationMessage();
            }
        });
    }

    @Subscribe
    public void updateMessageListEvent(MessageListUpdateEvent event) {

    }

    private void addNotificationMessage() {
        Message msg = new Message("实验室402", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "P3");
        PrefManager.addMessage(getContext(), msg);
        MessageDetailActivity.MessageUtil.tempMessage = msg;

        NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        PendingIntent intent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MessageDetailActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new Notification.Builder(getContext())
                .setSmallIcon(R.drawable.icon_home)
                .setContentTitle("收到一条报警")
                .setContentText("点击查看")
                .setAutoCancel(true)
                .setContentIntent(intent)
                .build();
        manager.notify(1, notification);
    }
}
