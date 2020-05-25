package io.agora.openlive.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;

import io.agora.openlive.R;
import io.agora.openlive.activities.DataActivity;
import io.agora.openlive.activities.HistoryActivity;
import io.agora.openlive.activities.LoginActivity;
import io.agora.openlive.activities.SettingsActivity;
import io.agora.openlive.event.MessageListUpdateEvent;
import io.agora.openlive.event.RoomListUpdateEvent;


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
    }

    @Subscribe
    public void updateMessageListEvent(MessageListUpdateEvent event) {

    }

}
