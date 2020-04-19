package io.agora.openlive.fragment;


import android.os.Bundle;

import io.agora.openlive.R;


public class UserMessageFragment extends LazyLoadFragment {
    private static final String TAG = "UserMessageFragment";

    private static final String[] USUALLY_TITLE = {"报警记录", "", "我的订单", "退出登陆"};
    private static final int[] USUALLY_IMG = {R.mipmap.gv_animation, R.mipmap.gv_multipleltem, R.mipmap.gv_header_and_footer, R.mipmap.gv_pulltorefresh};

    @Override
    protected int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad(Bundle savedInstanceStat) {

    }



}
