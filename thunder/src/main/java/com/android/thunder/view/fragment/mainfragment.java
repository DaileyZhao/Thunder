package com.android.thunder.view.fragment;

import android.widget.TextView;

import com.android.thunder.R;
import com.android.thunder.model.OkHttpAsyncTask;

import butterknife.BindView;

/**
 * Created by zcm on 2017/1/6.
 */

public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    @BindView(R.id.main_test_text)
    TextView main_test_text;
    @Override
    public void initViews() {
        OkHttpAsyncTask asyncTask=new OkHttpAsyncTask(main_test_text);
        asyncTask.execute();
    }
}
