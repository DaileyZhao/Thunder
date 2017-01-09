package com.android.thunder.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.thunder.R;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by zcm on 2017/1/6.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.basefragment,container,false);
        setContent(view);
        initViews();
        ButterKnife.bind(this,view);
        return view;
    }
    /**
     * 添加布局
     */
    private void setContent(View rootView) {
        ((FrameLayout) rootView.findViewById(R.id.ly_content)).removeAllViews();
        ((FrameLayout) rootView.findViewById(R.id.ly_content)).addView(getContentView());
    }
    /**
     * 根据类名来绑定布局
     */
    private View getContentView() {
        Class<?> clazz = this.getClass();
        View view = null;
        while (true) {
            try {
                view = LayoutInflater.from(getActivity()).inflate(
                        R.layout.class.getField(clazz.getSimpleName().toLowerCase(Locale.getDefault()))
                                .getInt(R.layout.class), null);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                if (clazz.getClass().equals(Activity.class))
                    break;
                else
                    clazz = clazz.getSuperclass();
            }
        }
        return view;
    }
    public abstract void initViews();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
