package com.ycy.api.test.core;

import java.util.ArrayList;

/**
 * @author yaocy
 * @date 2024/1/17 13:12
 * @description 使用策略策略接口； 根据创建策略对象不同，执行不同的读取文件方式
 */

public class ProcessStrategy<T>{
    private final Strategy<T> strategy;

    private final T packageName;

    public ProcessStrategy(Strategy<T> strategy,T t) {
        this.strategy = strategy;
        this.packageName = t;
    }

    // 执行实现类中方法
    public ArrayList<JsonBean> processConvert(){
        return strategy.process(packageName);
    }
}

