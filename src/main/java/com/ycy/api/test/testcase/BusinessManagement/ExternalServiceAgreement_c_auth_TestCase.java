package com.ycy.api.test.testcase.BusinessManagement;

import com.ycy.api.test.core.ConcreteClass;
import com.ycy.api.test.core.ProcessStrategy;
import com.ycy.api.test.core.SingleProcess;
import com.ycy.api.test.core.TemplateMethod;
import org.testng.annotations.Test;
import java.io.IOException;
public class ExternalServiceAgreement_c_auth_TestCase{
	@Test(description = " ")
	public void ExternalServiceAgreement_c_auth_commit_test() throws IOException  {
		 SingleProcess singleProcess = new SingleProcess();
        ProcessStrategy processStrategy = new ProcessStrategy(singleProcess, "/BusinessManagement/data/ExternalServiceAgreement_c_auth.yaml" );
        TemplateMethod templateMethod = new ConcreteClass();
        templateMethod.execute(processStrategy);
	}
}