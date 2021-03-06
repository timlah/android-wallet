package io.xdag.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by ssyijiu  on 2018/5/22
 * <p>
 * desc :
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mContext;
    protected Unbinder mUnbinder;
    private boolean mFirstShow = true;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    protected void initView(View rootView) {
        if (!(this instanceof RefreshFragment)) {
            mUnbinder = ButterKnife.bind(this, rootView);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // for lazy init data
        if (!hidden && mFirstShow) {
            mFirstShow = false;
            initData();
        }
    }


    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        if (enableEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }
        super.onDestroy();
    }


    protected abstract int getLayoutResId();


    protected void initData() {
    }


    protected Toolbar getToolbar() {
        if (mContext instanceof ToolbarActivity) {
            return ((ToolbarActivity) mContext).getToolbar();
        }
        throw new RuntimeException("The fragment must attach on ToolbarActivity");
    }


    /**
     * default enable EventBus
     */
    protected boolean enableEventBus() {
        return false;
    }
}
