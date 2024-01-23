package com.ycy.api.test.core;



import com.ycy.api.test.util.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yaocy
 * @date 2024/1/17 19:51
 * @description
 */
public class ConcreteClass extends TemplateMethod{
    @Override
    public void post() throws IOException {
        ArrayList<JsonBean> jsonBeans = TemplateMethod.getJsonBeans();
        for (JsonBean jsonBean : jsonBeans) {
            jsonBean.setUrl(String.format(jsonBean.getPostUrl(),jsonBean.getId()));
            OkHttpUtil.postJson(jsonBean);
        }
    }


}
