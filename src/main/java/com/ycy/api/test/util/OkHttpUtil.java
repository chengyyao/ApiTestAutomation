package com.ycy.api.test.util;


import com.ycy.api.test.core.JsonBean;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yaocy
 * @date 2024/1/17 10:41
 * @description
 */
public  class OkHttpUtil {

    public static Response postJson(JsonBean jsonBean) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, jsonBean.getBody());
        Request request = new Request.Builder()
                .url(jsonBean.getUrl())
                .method("POST", body)
                .addHeader("Authorization", jsonBean.getAuthorization())
                .build();
        return client.newCall(request).execute();
    }


}
