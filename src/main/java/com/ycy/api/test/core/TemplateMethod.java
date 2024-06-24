package com.ycy.api.test.core;
import com.alibaba.fastjson2.JSONObject;
import com.ycy.api.test.util.OkHttpUtil;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author yaocy
 * @date 2024/1/17 17:02
 * @description 定义模板方法， 再去创建子类继承该类，重写部分方法（有时候只想建保存的单据、并不想提交）
 */
public abstract class TemplateMethod {
    private static ArrayList<JsonBean> jsonBeans;
    // 把私有的静态属性 提供出去一个获取的方法
    public static ArrayList<JsonBean> getJsonBeans(){
        return jsonBeans;
    }
    public final void execute(ProcessStrategy<Object> processStrategy) throws IOException {
        pre(processStrategy);
        requestProcess();
        post();
    }
    // 接收执行策略
    public static void pre(ProcessStrategy<Object> processStrategy){
        jsonBeans = processStrategy.processConvert();
    }
    public void requestProcess() throws IOException {
        for (JsonBean jsonBean : jsonBeans) {
            if ("POST".equals(jsonBean.getMethod())){
                Response response = OkHttpUtil.postJson(jsonBean);
                if (jsonBean.getPostUrl() != null && !jsonBean.getPostUrl().isEmpty()){
                    ResponseBody body = response.body();
                    if (body == null)continue;
                    String string = body.string();
                    System.out.println(string);
                    // 需要把字符串转成json对象，然后再取返回的_id的值，并且给赋值到jsonBean中，供后续使用
                    JSONObject jsonObject = JSONObject.parseObject(string, JSONObject.class);
                    jsonBean.setId(jsonObject.getString("_id"));
                }
            }
        }
    }
    public abstract void  post() throws IOException;

}
