package com.ycy.api.test.util;

import java.net.URL;
import java.util.Objects;

/**
 * @author yaocy
 * @date 2024-6-13 9:38
 * @description
 */
public class ResourcePath {
    public static String getResourcePath() {
        URL resourcePathUrl = ResourcePath.class.getClassLoader().getResource("");
        if (Objects.isNull(resourcePathUrl)) throw new CustomizeException();
        return resourcePathUrl.getPath();
}
}
