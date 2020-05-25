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

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.activities.MessageDetailActivity;
import io.agora.openlive.adapter.MessageAdapter;
import io.agora.openlive.bean.Message;
import io.agora.openlive.bean.RoomBean;
import io.agora.openlive.event.MessageListUpdateEvent;
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
                startActivity(new Intent(getContext(), MessageDetailActivity.class));
            }
        });

        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, final int position) {
                final String[] items = {data.get(position).isTop ? "取消置顶" : "置顶", "标记处理", "忽略"};
                AlertDialog.Builder listDialog =
                        new AlertDialog.Builder(getContext());
                listDialog.setTitle("实验室");
                listDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                data.get(position).isTop = !data.get(position).isTop;
                                PrefManager.saveMessageList(getContext(), data);
                            }
                            break;
                            case 1:
                            case 2: {
                                data.remove(position);
                                PrefManager.saveMessageList(getContext(), data);
                            }
                            break;
                        }
                    }
                });
                listDialog.show();
                return false;
            }
        });
    }

    private void initData() {
        updateMessageData();
    }

    private void updateMessageData() {
        List<Message> updateData = PrefManager.getUnDealMessageList(getContext());
        if (updateData.isEmpty()) {
            updateData = new ArrayList<>();
            updateData.add(new Message("实验室401", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室402", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室401", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室403", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室402", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室405", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室402", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室401", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            updateData.add(new Message("实验室403", "2020.12.12 13:22:45", Message.STATE_UN_DOING, "危急"));
            PrefManager.saveMessageList(getContext(), updateData);
        }
        Collections.sort(updateData);
        data = updateData;
        adapter.setNewInstance(updateData);
    }

    @Subscribe
    public void updateMessageListEvent(MessageListUpdateEvent event) {
        updateMessageData();
    }

}