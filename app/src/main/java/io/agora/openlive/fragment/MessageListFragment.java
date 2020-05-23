package io.agora.openlive.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.adapter.MessageAdapter;
import io.agora.openlive.bean.Message;
import io.agora.openlive.bean.RoomBean;
import io.agora.openlive.event.RoomListUpdateEvent;
import io.agora.openlive.utils.PrefManager;

public class MessageListFragment extends LazyLoadFragment {
    private static final String TAG = "MessageListFragment";

    RecyclerView messageListView;
    MessageAdapter adapter;
    List<Message> data;

    @Override
    protected int setContentView() {
        return R.layout.fragment_message_list;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {
        messageListView = findViewById(R.id.message_list);
        adapter = new MessageAdapter(null, getContext());
        messageListView.setAdapter(adapter);
        messageListView.setNestedScrollingEnabled(false);
        messageListView.setLayoutManager(new LinearLayoutManager(getContext()));

        initData();

        initEvent();
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

            }
        });
    }

    private void initData() {
        updateMessageData();
    }

    private void updateMessageData() {
        // List<Message> updateData = PrefManager.getMessageList(getContext());
        List<Message> mockData = new ArrayList<>();
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        mockData.add(new Message("实验室401", "2020.12.12 13:22:45", "未处理", "危急"));
        data = mockData;
        adapter.setNewInstance(mockData);
    }

    @Subscribe
    public void updateRoomListEvent(RoomListUpdateEvent event) {

    }

}