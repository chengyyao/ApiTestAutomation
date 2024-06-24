package com.ycy.api.test.util;

import com.ycy.api.test.util.ResourcePath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yaocy
 * @date 2024-6-13 9:15
 * @description
 */
public class Generate {
    private static String TESTCASE_PATH = "src\\main\\java\\com\\ycy\\api\\test\\testcase";

    public static void generate(String fileName) throws IOException {
        // 获取resources目录下的所有文件夹，然后再遍历每个文件夹下的data文件夹，再遍历data文件夹
        // 如果找到了名称一样的文件，那就把这个文件的 路径拼接 BusinessManagement/data/ExternalServiceAgreement_c_auth.yaml
        String resourcesPath = ResourcePath.getResourcePath();
        File fileResourcesPath = new File(resourcesPath);
        // 获取传入文件所在的data文件的上级路径 D:\daima\ApiTestAutomation\target\classes\BusinessManagement
        String filePath = extracted(fileResourcesPath, fileName);
        System.out.println(filePath);
        if (!filePath.isEmpty()) {
            File file = new File(filePath);
            String[] split = file.getName().split("\\\\");
            //获取到BusinessManagement
            String  lastSegment = split[split.length - 1];
            System.out.println(lastSegment);
            // 2、判断testcase下是否有 BusinessManagement 一样名字的文件夹
            File testCaseFile = new File(TESTCASE_PATH);
            if (!testCaseFile.exists()){
                testCaseFile.mkdir();
            }

            File lastSegmentTestCase = new File(TESTCASE_PATH + File.separator + lastSegment);
            if(!lastSegmentTestCase.exists()){
                lastSegmentTestCase.mkdir();
            }
            // 创建测试用例文件
            String[] fileNameSplit = fileName.split("\\.");
            String fileNameStr = fileNameSplit[0];
            File javaFile = new File(lastSegmentTestCase.getPath() + File.separator + fileNameStr + "_TestCase.java");
            String fileStr = "/" + lastSegment + "/" + "data" + "/" + fileName;
            if (javaFile.exists()){
                boolean delete = javaFile.delete();
                if (delete){
                    System.out.println("已存在，故先删除文件，文件删除成功");
                }
            }
            if (javaFile.createNewFile()) {
                System.out.println("文件创建成功，路径为：" + javaFile.getAbsolutePath());
                // 写入java类内容
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(javaFile));){
                    bw.write("package com.ycy.api.test.testcase." + lastSegment + ";" +"\n\n");
                    bw.write("import com.ycy.api.test.core.ConcreteClass;\n" +
                            "import com.ycy.api.test.core.ProcessStrategy;\n" +
                            "import com.ycy.api.test.core.SingleProcess;\n" +
                            "import com.ycy.api.test.core.TemplateMethod;\n" +
                            "import org.testng.annotations.Test;\n" +
                            "import java.io.IOException;\n");
                    bw.write("public class " + fileNameStr + "_TestCase" + "{\n");
                    bw.write("\t@Test(description = \" \")\n");
                    bw.write("\tpublic void " + fileNameStr + "_commit_test() throws IOException  {\n");
                    bw.write("\t\t SingleProcess singleProcess = new SingleProcess();\n" +
                            "        ProcessStrategy processStrategy = new ProcessStrategy(singleProcess, \"" + fileStr + "\" );\n" +
                            "        TemplateMethod templateMethod = new ConcreteClass();\n" +
                            "        templateMethod.execute(processStrategy);\n");
                    bw.write("\t}\n");
                    bw.write("}");
                }catch (IOException e){
                    e.printStackTrace();
                }


            } else {
                System.out.println("文件创建失败或文件已存在");
            }
        }else {
            System.out.println("文件路径为空，无法生成测试用例");
        }




        // 2.1 如果有，那遍历文件夹下文件
        // 2.1.1 如果有 跟 ExternalServiceAgreement_c_auth.yaml 去掉.yaml后一样名称的java类，不再重新生成
        // 2.1.2 如果没有 跟 ExternalServiceAgreement_c_auth.yaml 去掉.yaml后一样名称的java类，则生成一个java类文件，并写入方法
        //2.2 如果没有，则生成该文件夹，并且生成 同2.1.2里一样的逻辑；
    }

    private static String extracted(File dir, String fileName) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return null;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && !file.getName().contains("com") && !file.getName().contains("param")) {
                    String result = extracted(file, fileName);
                    if (result != null) {
                        return result;
                    }
                } else if (file.isFile() && file.getName().contains(fileName)) {
                    return file.getParentFile().getParent();
                }
            }
        }

        return null;
    }
}