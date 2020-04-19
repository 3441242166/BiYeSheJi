package io.agora.openlive.fragment;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.adapter.ModuleAdapter;
import io.agora.openlive.bean.LiveBean;
import io.agora.openlive.utils.SPUtils;

/**
 * Created by wanhao on 2018/2/23.
 */

public class MainFragment extends LazyLoadFragment {

    RecyclerView recyclerView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {
        recyclerView = findViewById(R.id.recycler_view);
        ModuleAdapter moduleAdapter = new ModuleAdapter(null, getContext());
        recyclerView.setAdapter(moduleAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String liveJson = SPUtils.get(getContext(), "live_data", "");
        List<LiveBean> data = new Gson().fromJson(liveJson, new TypeToken<List<LiveBean>>() {
        }.getType());

        moduleAdapter.setNewInstance(data);
    }

}
