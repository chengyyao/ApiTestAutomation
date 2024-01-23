package com.ycy.api.test.core;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * @author yaocy
 * @date 2024/1/17 15:14
 * @description 传入单个文件路径，只读取该文件数据
 */
public class SingleProcess implements Strategy<String> {

    /**
     *
     * @param fileName  /BusinessManagement/data/ExternalServiceAgreement_c_1.yaml
     */
    @Override
    public ArrayList<JsonBean> process(String fileName) {
        String resourcesPath = getResourcePath();
        File file = new File(resourcesPath + fileName);
        //文件转成字符串
        String json = FileUtil.readString(file, "utf-8");
        //字符串的json转成bean
        JsonBean jsonBean = JSONObject.parseObject(json, JsonBean.class);
        // 参数化文件
        combParams(file, jsonBean);
        ArrayList<JsonBean> arrayList = new ArrayList<>();
        arrayList.add(jsonBean);
        return arrayList;
    }

}
