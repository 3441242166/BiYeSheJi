package io.agora.openlive.adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.bean.RoomBean;

public class ModuleAdapter extends BaseQuickAdapter<RoomBean, BaseViewHolder> {

    public ModuleAdapter(@Nullable List<RoomBean> data, Context context) {
        super(R.layout.item_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomBean item) {
        helper.setText(R.id.room_name, item.name);
        if (item.state == RoomBean.SAFE_STATE) {
            helper.findView(R.id.room_state).setBackgroundColor(Color.BLUE);
        } else {
            helper.findView(R.id.room_state).setBackgroundColor(Color.RED);
        }
        if (item.isTop) {
            helper.findView(R.id.item_layout).setBackgroundColor(Color.parseColor("#CCCCCC"));
        } else {
            helper.findView(R.id.item_layout).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}