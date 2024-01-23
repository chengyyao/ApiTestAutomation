package com.ycy.api.test.core;

/**
 * @author yaocy
 * @date 2024/1/17 11:16
 * @description Json文件数据实体类
 */
public class JsonBean {
    private String url;
    private String body;

    private String Authorization;

    private String method;

    private String postUrl;

    private String id;

    public JsonBean() {
    }

    public JsonBean(String url, String body) {
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
