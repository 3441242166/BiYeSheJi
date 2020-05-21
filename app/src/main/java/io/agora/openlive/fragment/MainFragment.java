package io.agora.openlive.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.activities.AddLiveActivity;
import io.agora.openlive.adapter.ModuleAdapter;
import io.agora.openlive.bean.RoomBean;
import io.agora.openlive.event.RoomEvent;
import io.agora.openlive.utils.PrefManager;

import static io.agora.openlive.constant.AppConstant.ROOM_LIST_KEY;

/**
 * Created by wanhao on 2018/2/23.
 */

public class MainFragment extends LazyLoadFragment {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ModuleAdapter adapter;

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
    public void updateRoomListEvent(RoomEvent event) {
        updateNewData();
    }

    private void updateNewData() {
        String liveJson = PrefManager.get(getContext(), ROOM_LIST_KEY);
        List<RoomBean> data = new Gson().fromJson(liveJson, new TypeToken<List<RoomBean>>() {
        }.getType());
        List<RoomBean> mockData = new ArrayList<>();
        mockData.add(new RoomBean("实验室401", 0));
        mockData.add(new RoomBean("实验室402", 0));
        mockData.add(new RoomBean("实验室403", 0));
        mockData.add(new RoomBean("实验室404", 0));
        mockData.add(new RoomBean("实验室405", 0));
        mockData.add(new RoomBean("实验室406", 0));
        mockData.add(new RoomBean("实验室407", 0));
        mockData.add(new RoomBean("实验室408", 0));
        mockData.add(new RoomBean("实验室409", 0));
        mockData.add(new RoomBean("实验室410", 0));
        mockData.add(new RoomBean("实验室411", 0));
        adapter.setNewInstance(mockData);
    }

}
