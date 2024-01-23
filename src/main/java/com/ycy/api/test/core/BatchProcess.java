package com.ycy.api.test.core;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * @author yaocy
 * @date 2024/1/17 15:14
 * @description 传入多个文件路径，批量读取多个文件数据
 */
public class BatchProcess implements Strategy<String[]> {

    @Override
    public ArrayList<JsonBean> process(String[] packageName) {
        ArrayList<JsonBean> arrayList = new ArrayList<>();
        String resourcePath = getResourcePath();
        for (int i = 0; i < packageName.length; i++) {
            String s = resourcePath + File.separator + packageName[i] + File.separator + META_DATA_FILE;
            File[] files = new File(s).listFiles(); // 获取当前包路径下所有文件
            if (files == null) {
                continue;
            }
            for (File file : files) {
                //文件转成字符串
                String json = FileUtil.readString(file, "utf-8");
                //字符串的json转成bean
                JsonBean jsonBean = JSONObject.parseObject(json, JsonBean.class);
                // 参数化文件
                combParams(file, jsonBean);
                arrayList.add(jsonBean);
            }
        }
        return arrayList;
    }


}
