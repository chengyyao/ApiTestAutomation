package com.ycy.api.test.testcase.BusinessManagement;

import com.alibaba.fastjson2.annotation.JSONAutowired;
import com.ycy.api.test.core.ConcreteClass;
import com.ycy.api.test.core.ProcessStrategy;
import com.ycy.api.test.core.SingleProcess;
import com.ycy.api.test.core.TemplateMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author yaocy
 * @date 2024-6-12 14:03
 * @description
 */

public class IntellectualPropertyContract_c_TestCase {
    @Test(description = "知识产权合同保存和提交")
    public void IntellectualPropertyContract_c_commit_test() throws IOException {
        SingleProcess singleProcess = new SingleProcess();
        ProcessStrategy processStrategy = new ProcessStrategy(singleProcess, "/BusinessManagement/data/IntellectualPropertyContract_c.yaml");
        TemplateMethod templateMethod = new ConcreteClass();
        templateMethod.execute(processStrategy);
    }
}
