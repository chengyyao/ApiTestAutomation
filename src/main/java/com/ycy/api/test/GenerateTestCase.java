package com.ycy.api.test;

import java.io.IOException;
import static com.ycy.api.test.util.Generate.generate;


/**
 * @author yaocy
 * @date 2024-6-19 19:29
 * @description
 */
public class GenerateTestCase {
    public static void main(String[] args) throws IOException {
        generate("ExternalServiceAgreement_c_auth.yaml");
    }
}
