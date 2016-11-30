package com.android.thunder.http;

import com.android.thunder.ThunderApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * 设置http缓存
 */
public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(ThunderApplication.getContext().getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
