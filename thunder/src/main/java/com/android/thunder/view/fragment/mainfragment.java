package com.android.thunder.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.android.thunder.R;
import com.android.thunder.model.OkHttpAsyncTask;
import com.android.thunder.utils.MiscUtil;

import butterknife.BindView;

/**
 * Created by zcm on 2017/1/6.
 */

public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    @BindView(R.id.main_test_text)
    TextView main_test_text;
    @BindView(R.id.main_refresh_layout)
    SwipeRefreshLayout main_refresh_layout;
    @Override
    public void initViews() {
        OkHttpAsyncTask asyncTask=new OkHttpAsyncTask(main_test_text);
        asyncTask.execute();
        main_refresh_layout.setColorSchemeColors(R.color.red,R.color.green,R.color.line);
        main_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                main_test_text.setText("正在刷新");
                main_refresh_layout.setRefreshing(true);
            }
        });
        main_refresh_layout.setProgressViewOffset(false, 0, MiscUtil.dip2px(24));
       // main_refresh_layout.setRefreshing(true);
    }
}
