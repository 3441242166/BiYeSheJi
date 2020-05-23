package io.agora.openlive.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * 懒加载的Fragment
 */
public abstract class LazyLoadFragment extends Fragment {
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected final String TAG = "LazyLoadFragment";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        isInit = true;
        /**初始化的时候去加载数据**/
        Log.i(TAG, "onCreateView: "+ "  class: "+getClass().getName());
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        lazyLoad(savedInstanceState);
        return view;
    }

//    /**
//     * 视图是否已经对用户可见，系统的方法
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        Log.i(TAG, "setUserVisibleHint: "+isVisibleToUser + "  class: "+getClass().getName());
//        isCanLoadData();
//    }
//
//    /**
//     * 是否可以加载数据
//     * 可以加载数据的条件：
//     * 1.视图已经初始化
//     * 2.视图对用户可见
//     */
//    private void isCanLoadData() {
//        if (!isInit) {
//            return;
//        }
//        // 可见 并且 未加载过
//        if (getUserVisibleHint() && !isLoad) {
//            lazyLoad();
//            isLoad = true;
//        } else {
//            if (isLoad) {
//                stopLoad();
//            }
//        }
//    }

    /**
     * 视图销毁的时候将Fragment是否初始化的状态变为false宿舍
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    protected abstract int setContentView();

    /**
     * 获取设置的布局
     *
     * @return
     */
    protected View getContentView() {
        return view;
    }

    /**
     * 找出对应的控件
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View>  T findViewById(int id) {
        return getContentView().findViewById(id);
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad(Bundle savedInstanceState);

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {

    }

    @Override
    public void onDestroy() {
        EventBus.builder().build().unregister(this);
        super.onDestroy();
    }

}