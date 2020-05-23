package io.agora.openlive.fragment;


import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;

import io.agora.openlive.R;
import io.agora.openlive.event.RoomListUpdateEvent;


public class UserMessageFragment extends LazyLoadFragment {
    private static final String TAG = "UserMessageFragment";


    @Override
    protected int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {

    }

    @Subscribe
    public void updateRoomListEvent(RoomListUpdateEvent event) {

    }

}
