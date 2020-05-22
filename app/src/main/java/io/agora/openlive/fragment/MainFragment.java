package io.agora.openlive.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.agora.openlive.AgoraApplication;
import io.agora.openlive.R;
import io.agora.openlive.activities.AddLiveActivity;
import io.agora.openlive.activities.LiveActivity;
import io.agora.openlive.adapter.ModuleAdapter;
import io.agora.openlive.bean.RoomBean;
import io.agora.openlive.event.RoomListUpdateEvent;
import io.agora.openlive.utils.PrefManager;
import io.agora.rtc.Constants;

import static io.agora.openlive.constant.AppConstant.ROOM_LIST_KEY;
import static io.agora.openlive.utils.PrefManager.ROOM_LIST;

/**
 * Created by wanhao on 2018/2/23.
 */

public class MainFragment extends LazyLoadFragment {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ModuleAdapter adapter;
    List<RoomBean> data;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {
        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.add_live);
        adapter = new ModuleAdapter(null, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String room = data.get(position).code;
                AgoraApplication.application.engineConfig().setChannelName(room);
                Intent intent = new Intent(getContext(), LiveActivity.class);
                intent.putExtra(io.agora.openlive.Constants.KEY_CLIENT_ROLE, Constants.CLIENT_ROLE_AUDIENCE);
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                final String[] items = {"置顶", "屏蔽", "删除"};
                AlertDialog.Builder listDialog =
                        new AlertDialog.Builder(getContext());
                listDialog.setTitle("实验室");
                listDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                        }
                    }
                });
                listDialog.show();
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddLiveActivity.class));
            }
        });

        updateNewData();
    }

    @Subscribe
    public void updateRoomListEvent(RoomListUpdateEvent event) {
        updateNewData();
    }

    private void updateNewData() {
        List<RoomBean> updateData = PrefManager.getRoomList(getContext());
        List<RoomBean> mockData = new ArrayList<>();
        mockData.add(new RoomBean("实验室401", 0, "wanhao"));
        mockData.add(new RoomBean("实验室402", 0, "wanhao"));
        mockData.add(new RoomBean("实验室403", 0, "wanhao"));
        mockData.add(new RoomBean("实验室404", 0, "wanhao"));
        mockData.add(new RoomBean("实验室405", 0, "wanhao"));
        mockData.add(new RoomBean("实验室406", 0, "wanhao"));
        mockData.add(new RoomBean("实验室407", 0, "wanhao"));
        mockData.add(new RoomBean("实验室408", 0, "wanhao"));
        mockData.add(new RoomBean("实验室409", 0, "wanhao"));
        mockData.add(new RoomBean("实验室410", 0, "wanhao"));
        mockData.add(new RoomBean("实验室411", 0, "wanhao"));
        data = updateData;
        adapter.setNewInstance(updateData);
    }

}
