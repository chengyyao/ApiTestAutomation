package com.ycy.api.test.core;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ycy.api.test.util.CustomizeException;
import com.ycy.api.test.util.ResourcePath;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author yaocy
 * @date 2024/1/17 13:16
 * @description 策略模式接口
 */
public interface Strategy<T> {
    String PublicParamFilePath = "PublicParamConfig.conf";
    static String randomStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String META_DATA_FILE = "data";
    String META_PARAMS_FILE = "params";
    ArrayList<JsonBean> process(T t);

    /**
     * @param file
     * @param jsonBean
     */
    default void combParams(File file, JsonBean jsonBean) {
        // 根据传入的文件路径，解析文件内容为字符串，然后取authorization 的值 set到jsonBean中
        File PublicParamFile = new File(ResourcePath.getResourcePath() + PublicParamFilePath);
        //文件转成字符串
        String PublicParam = FileUtil.readString(PublicParamFile, "utf-8");
        // 字符串转成json
        JSONObject PublicParamJson = JSONObject.parseObject(PublicParam);
        //字符串的值，存入jsonBean
        String authorization = PublicParamJson.getString("authorization");
        jsonBean.setAuthorization(authorization.trim());
        // 替换url
        String host = PublicParamJson.getString("host");
        jsonBean.setUrl(host + jsonBean.getUrl());
        jsonBean.setPostUrl(host + jsonBean.getPostUrl());
        //根据传入的参数文件file，取到它上级的上级全路径
        String paramsFileName = Paths.get(file.getParentFile().getParent(), META_PARAMS_FILE, file.getName()).toString();
        File paramsFile = new File(paramsFileName);
        String paramJson = FileUtil.readString(paramsFile, "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(paramJson);
        // 省略一步
//        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
            jsonBean.setUrl(replaceParams(stringObjectEntry, jsonBean.getUrl()));
            jsonBean.setBody(replaceParams(stringObjectEntry, jsonBean.getBody()));
            jsonBean.setPostUrl(replaceParams(stringObjectEntry, jsonBean.getPostUrl()));

        }


    }

    // 用参数文件中的key对应的值，替换原文件中的{name}
    default String replaceParams(Map.Entry<String, Object> stringObjectEntry, String oldStr) {
        return oldStr.replace("{" + stringObjectEntry.getKey() + "}", stringObjectEntry.getValue().toString() + getRandomStr());
    }


    default String getRandomStr() {
        // 创建一个包含大写字母和数字的字符数组
        char[] chars = randomStr.toCharArray();

        // 创建一个StringBuilder来构建最终的随机字符串
        StringBuilder sb = new StringBuilder();

        // 创建Random对象用于生成随机数
        Random random = new Random();

        // 生成5个随机字符
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        // 将StringBuilder转换为字符串
        return sb.toString();
    }

}

