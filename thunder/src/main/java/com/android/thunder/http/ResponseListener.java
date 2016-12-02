package com.android.thunder.http;

import org.json.JSONObject;

/**
 * Created by dayima on 16-11-2.
 */

public interface ResponseListener {
    void onSuccess(JSONObject object);
    void onError(Throwable e);
    void onFail(JSONObject object);
    void showProgress();
    void disMissProgress();
}
