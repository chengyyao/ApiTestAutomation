package com.ycy.api.test;

import com.ycy.api.test.core.BatchProcess;
import com.ycy.api.test.core.ConcreteClass;
import com.ycy.api.test.core.ProcessStrategy;
import com.ycy.api.test.core.TemplateMethod;
import java.io.IOException;

/**
 * @author yaocy
 * @date 2024/1/23 19:02
 * @description
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BatchProcess batchProcess = new BatchProcess();
        String[] li = {"BusinessManagement"};
        ProcessStrategy processStrategy = new ProcessStrategy(batchProcess, li);
        TemplateMethod templateMethod = new ConcreteClass();
        templateMethod.execute(processStrategy);
    }
}
