package io.agora.openlive.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.bean.LiveBean;

public class ModuleAdapter extends BaseQuickAdapter<LiveBean, BaseViewHolder> {

    public ModuleAdapter(@Nullable List<LiveBean> data, Context context) {
        super(R.layout.item_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveBean item) {
//        helper.setText(R.id.item_module_title,item.getTitle());
//        Glide.with(context).load(item.getImgID()).into((ImageView) helper.getView(R.id.item_module_img));
    }
}