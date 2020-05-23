package io.agora.openlive.adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.bean.Message;

public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {

    public MessageAdapter(@Nullable List<Message> data, Context context) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        // helper.setText(R.id.room_name, item.name);

    }
}