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
        helper.setText(R.id.address, item.address);
        helper.setText(R.id.time, item.time);
        helper.setText(R.id.notice, item.notice);
        helper.setText(R.id.state, item.state);

        if (item.isTop) {
            helper.findView(R.id.item_layout).setBackgroundColor(Color.parseColor("#CCCCCC"));
        } else {
            helper.findView(R.id.item_layout).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}