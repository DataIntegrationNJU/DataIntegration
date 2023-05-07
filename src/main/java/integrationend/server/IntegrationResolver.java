package integrationend.server;

import integrationend.Utilities.Utility;
import integrationend.controller.HTTPClient;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.http.ResponseEntity;
import org.dom4j.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationResolver {

    private HTTPClient httpClient=new HTTPClient();
    private Utility utility=new Utility();

    private XMLResolver xmlResolver=new XMLResolver();
    private String tempFilePathForAXml="src/main/resources/static/tempAXml/originAXml.xml";
    private String tempFilePathForBXml="src/main/resources/static/tempBXml/originBXml.xml";
    private String tempFilePathForCXml="src/main/resources/static/tempCXml/originCXml.xml";

//==================================持久化存储部分路径，用于给集成端可视化使用
    //Choice
    private String formatAChoice="src/main/resources/static/formatChoice/AChoice/formatChoiceAXml.xml";
    private String formatBChoice="src/main/resources/static/formatChoice/BChoice/formatChoiceBXml.xml";
    private String formatCChoice="src/main/resources/static/formatChoice/CChoice/formatChoiceCXml.xml";

    //Course

    private String formatACourse="src/main/resources/static/formatCourse/ACourse/formatCourseAXml.xml";
    private String formatBCourse="src/main/resources/static/formatCourse/BCourse/formatCourseBXml.xml";
    private String formatCCourse="src/main/resources/static/formatCourse/CCourse/formatCourseCXml.xml";

    //Students

    private String formatAStudent="src/main/resources/static/formatStudent/AStudent/formatStudentAXml.xml";
    private String formatBStudent="src/main/resources/static/formatStudent/BStudent/formatStudentBXml.xml";
    private String formatCStudent="src/main/resources/static/formatStudent/CStudent/formatStudentCXml.xml";


    //==================ABC的url
    String Aurl="http://192.168.49.187:9092";
    String Burl="http://192.168.49.35:9091";
    String Curl="http://192.168.49.184:9090";

    /**
     * choice 1/class 2/student 3
     */
    private List<String> replaceAttribute=new ArrayList();

    public IntegrationResolver(){
        replaceAttribute.add("xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"chooseCourse.xsd\"");
        replaceAttribute.add("xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\"");
        replaceAttribute.add("xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \"xsi:schemaLocation=\"student.xsd\"");

    }



    /**
     * 获得requester的所有课程
     * @param requester
     * @return 返回最终返给requester的文件路径列表
     */
    public String getAllCourses(String requester,String provider) {
        //先找到供应商，拿回xml文件
        switch (provider) {
            case "A": {
                //向A server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo:更改获得A课程的url
                String urlA = Aurl+"/integration/sendXML";
                //获得xml文件的string形式
                ResponseEntity<String> AXmlFileResponse = HTTPClient.sendPostRequest(urlA, "");
                String AXmlString = AXmlFileResponse.getBody();
                System.out.println("AXml=" + AXmlString);

               /* String AXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";*/

                //去除标签中的内容
                int indexA = AXmlString.indexOf(replaceAttribute.get(1));
                if (indexA == -1) {
                    System.err.println("Can't find attribute in AXmlString!");
                }

                String tA = AXmlString.substring(0, indexA - 1) + AXmlString.substring(indexA + replaceAttribute.get(1).length());
                AXmlString = tA;

                //将string类型的xml转换成xml文件,并将其存入tempFilePathForAXml（tempXXX/originXXX.xml）
                utility.changeStringToXmlFile(AXmlString, tempFilePathForAXml);

                //将发回的请求转换成统一格式，存到了下面路径里面(result/formatXXX.xml)
                String formatAXmlPath = xmlResolver.ABCToFormat(tempFilePathForAXml, "formatCourseAXml", 2);

                //将获得的内容持久化存储
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForAXml, formatACourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store A courses!");
                }


                System.out.println("format for A:" + formatAXmlPath);

                //将转换成的文件路径放到aPath中(tempB/courseAXml1.xml或者tempC/courseAXml1.xml) 注意！！！ tempA文件夹中可能出现tempB、C
                String aPath="";
                if(requester.equals("B")){
                    //A xml转B xml
                    //将合并后的xml文件转换成B的xml格式
                    aPath = xmlResolver.classToABC(formatAXmlPath, "courseAXml1", 2);
                    System.out.println("a " + aPath);
                }else if(requester.equals("C")){
                    //A xml转C xml
                    aPath = xmlResolver.classToABC(formatAXmlPath, "courseAXml1", 3);
                    System.out.println("a " + aPath);
                }

                //将xml文件转换为string
                String aToXStr = "";

                SAXReader saxReader = new SAXReader();
                Document document = null;
                try {
                    document = saxReader.read(new FileReader(aPath));//从xml文件获取数据
                    aToXStr = Utility.formatXML(document,"gb2312");


                } catch (FileNotFoundException e) {
                    System.err.println("Can't find ");
                } catch (DocumentException e) {
                }

                //将内容加回去
                //------找到字符串的位置

                indexA = aToXStr.indexOf("<courses>");
                if (indexA == -1) {
                    System.err.println("Can't find attribute in aToBStr!");
                }
                indexA += 8;
                //-------插回去
                StringBuilder builderA = new StringBuilder(aToXStr);
                builderA.insert(indexA, " " + replaceAttribute.get(1));
                aToXStr = builderA.toString();

                //返回是否成功
                return aToXStr;

            }
            case "B": {
                //向 B server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo:更改获得B课程的url
                String urlB = Burl+"/integration/sendXML";

                //获得xml文件的string形式
                ResponseEntity<String> BXmlFileResponse = HTTPClient.sendPostRequest(urlB, "");
                String BXmlString = BXmlFileResponse.getBody();
                System.out.println("BXml=" + BXmlString);


                //==========测试部分
               /* String BXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";
                String CXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <Cno>0B01</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>季勇</Tec>\n" +
                        "        <Pla>北教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B03</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>3</Cpt>\n" +
                        "        <Tec>黄建秋</Tec>\n" +
                        "        <Pla>南教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B07</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>贺云翱</Tec>\n" +
                        "        <Pla>北教102</Pla>\n" +
                        "    </course>\n" +
                        "</courses>";*/

                //==============end test

                //去除标签中的内容
                int indexB = BXmlString.indexOf(replaceAttribute.get(1));
                if (indexB == -1) {
                    System.err.println("Can't find attribute in BXmlString!");
                }

                String tB = BXmlString.substring(0, indexB - 1) + BXmlString.substring(indexB + replaceAttribute.get(1).length());
                BXmlString = tB;


                //将String格式的回复存储到本地的xml文件中(tempB/originxxx)
                utility.changeStringToXmlFile(BXmlString, tempFilePathForBXml);


                //将发回的请求转换成统一格式，存到了下面两个路径里面(result/format……)
                String formatBXmlPath = xmlResolver.ABCToFormat(tempFilePathForBXml, "formatCourseBXml", 2);
                System.out.println("format for B:" + formatBXmlPath);

                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForBXml, formatBCourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store B courses!");
                }


                //将合并后的xml文件转换成要求的xml格式
                String bPath="";
                if(requester.equals("A")){
                     bPath = xmlResolver.classToABC(formatBXmlPath, "courseBXml1", 1);
                    System.out.println("b " + bPath);
                }else if(requester.equals("C")){
                    bPath = xmlResolver.classToABC(formatBXmlPath, "courseBXml1", 3);
                    System.out.println("b " + bPath);
                }


                //将xml文件转换为string
                String bToXStr = "";
                SAXReader saxReader = new SAXReader();
                Document document = null;
                try {
                    document = saxReader.read(new FileReader(bPath));//从xml文件获取数据
                    bToXStr=Utility.formatXML(document,"gb2312");

                } catch (FileNotFoundException e) {
                    System.err.println("Can't find ");
                } catch (DocumentException e) {
                }

                //将内容加回去
                //------找到字符串的位置
                indexB = bToXStr.indexOf("<courses>");
                if (indexB == -1) {
                    System.err.println("Can't find attribute in bToAStr!");
                }
                indexB += 8;
                StringBuilder builderB = new StringBuilder(bToXStr);
                builderB.insert(indexB, " " + replaceAttribute.get(1));
                bToXStr = builderB.toString();


                //返回是否成功
                return bToXStr;
            }
            case "C": {
                //询问server C
                Map<String, String> params = new HashMap<>();
                //todo:更改获得C课程的url
                String urlC = Curl+"/integration/sendXML";
                //获得xml文件的string形式

                ResponseEntity<String> CXmlFileResponse = HTTPClient.sendPostRequest(urlC, "");
                String CXmlString = CXmlFileResponse.getBody();
                System.out.println("CXml=" + CXmlString);

                //==========测试部分
               /* String BXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";
                String CXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <Cno>0B01</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>季勇</Tec>\n" +
                        "        <Pla>北教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B03</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>3</Cpt>\n" +
                        "        <Tec>黄建秋</Tec>\n" +
                        "        <Pla>南教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B07</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>贺云翱</Tec>\n" +
                        "        <Pla>北教102</Pla>\n" +
                        "    </course>\n" +
                        "</courses>";*/

                //==============end test

                //去除标签中的内容

                int indexC = CXmlString.indexOf(replaceAttribute.get(1));

                if (indexC == -1) {
                    System.err.println("Can't find attribute in CXmlString!");
                }

                String tC = CXmlString.substring(0, indexC - 1) + CXmlString.substring(indexC + replaceAttribute.get(1).length());
                CXmlString = tC;

                //将String格式的回复存储到本地的xml文件中

                utility.changeStringToXmlFile(CXmlString, tempFilePathForCXml);


                //将发回的请求转换成统一格式，存到了下面两个路径里面

                String formatCXmlPath = xmlResolver.ABCToFormat(tempFilePathForCXml, "formatCourseCXml", 2);
                System.out.println("format for C:" + formatCXmlPath);

                //持久化存储
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForCXml, formatCCourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store C courses!");
                }
                //String formatBXmlPath="src/main/resources/static/result/classBXml.xml";

                //将合并后的xml文件转换成要求的xml格式

                String cPath="";
                if(requester.equals("A")){
                    cPath = xmlResolver.classToABC(formatCXmlPath, "courseCXml1", 1);
                    System.out.println("c " + cPath);
                }else if(requester.equals("B")){
                    cPath = xmlResolver.classToABC(formatCXmlPath, "courseCXml1", 2);
                    System.out.println("c " + cPath);
                }


                //将xml文件转换为string

                String cToXStr = "";
                SAXReader saxReader = new SAXReader();
                Document document = null;
                try {


                    document = saxReader.read(new FileReader(cPath));
                    cToXStr = Utility.formatXML(document,"gb2312");

                } catch (FileNotFoundException e) {
                    System.err.println("Can't find ");
                } catch (DocumentException e) {
                }

                //将内容加回去
                //------找到字符串的位置

                indexC = cToXStr.indexOf("<courses>");
                indexC += 8;
                if (indexC == -1) {
                    System.err.println("Can't find attribute in cToBStr!");
                }
                StringBuilder builderC = new StringBuilder(cToXStr);
                builderC.insert(indexC, " " + replaceAttribute.get(1));
                cToXStr = builderC.toString();


                //返回是否成功
                return cToXStr;
            }


        }
        return "";
    }
    /**
     * 获得requester的所有课程
     * @param provider
     * @return 返回最终返给requester的文件路径列表
     */
    public boolean getAllCourses(String provider) {
        //先找到供应商，拿回xml文件
        switch (provider) {
            case "A": {
                //向A server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo:更改获得A课程的url
                String urlA = Aurl+"/integration/sendXML";
                //获得xml文件的string形式
/*                ResponseEntity<String> AXmlFileResponse = HTTPClient.sendPostRequest(urlA, "");
                String AXmlString = AXmlFileResponse.getBody();
                System.out.println("AXml=" + AXmlString);*/

                String AXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";

                //去除标签中的内容
                int indexA = AXmlString.indexOf(replaceAttribute.get(1));
                if (indexA == -1) {
                    System.err.println("Can't find attribute in AXmlString!");
                }

                String tA = AXmlString.substring(0, indexA - 1) + AXmlString.substring(indexA + replaceAttribute.get(1).length());
                AXmlString = tA;

                //将string类型的xml转换成xml文件,并将其存入tempFilePathForAXml（tempXXX/originXXX.xml）
                utility.changeStringToXmlFile(AXmlString, tempFilePathForAXml);


                //将获得的内容持久化存储
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForAXml, formatACourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store A courses!");
                    return false;
                }
                return true;

            }
            case "B": {
                //向 B server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo:更改获得B课程的url
                String urlB = Burl+"/integration/sendXML";

                //获得xml文件的string形式
                ResponseEntity<String> BXmlFileResponse = HTTPClient.sendPostRequest(urlB, "");
                String BXmlString = BXmlFileResponse.getBody();
                System.out.println("BXml=" + BXmlString);


                //==========测试部分
               /* String BXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";
                String CXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <Cno>0B01</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>季勇</Tec>\n" +
                        "        <Pla>北教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B03</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>3</Cpt>\n" +
                        "        <Tec>黄建秋</Tec>\n" +
                        "        <Pla>南教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B07</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>贺云翱</Tec>\n" +
                        "        <Pla>北教102</Pla>\n" +
                        "    </course>\n" +
                        "</courses>";*/

                //==============end test

                //去除标签中的内容
                int indexB = BXmlString.indexOf(replaceAttribute.get(1));
                if (indexB == -1) {
                    System.err.println("Can't find attribute in BXmlString!");
                }

                String tB = BXmlString.substring(0, indexB - 1) + BXmlString.substring(indexB + replaceAttribute.get(1).length());
                BXmlString = tB;


                //将String格式的回复存储到本地的xml文件中(tempB/originxxx)
                utility.changeStringToXmlFile(BXmlString, tempFilePathForBXml);


                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForBXml, formatBCourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store B courses!");
                    return false;
                }
                return true;
            }
            case "C": {
                //询问server C
                Map<String, String> params = new HashMap<>();
                //todo:更改获得C课程的url
                String urlC = Curl+"/integration/sendXML";
                //获得xml文件的string形式

                ResponseEntity<String> CXmlFileResponse = HTTPClient.sendPostRequest(urlC, "");
                String CXmlString = CXmlFileResponse.getBody();
                System.out.println("CXml=" + CXmlString);

                //==========测试部分
               /* String BXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <课程编号>05075001</课程编号>\n" +
                        "        <课程名称>BIM概论与建模</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>陈艳云</授课老师>\n" +
                        "        <授课地点>北教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075003</课程编号>\n" +
                        "        <课程名称>会计信息系统</课程名称>\n" +
                        "        <学分>3</学分>\n" +
                        "        <授课老师>郑细端</授课老师>\n" +
                        "        <授课地点>南教302</授课地点>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <课程编号>05075010</课程编号>\n" +
                        "        <课程名称>工程造价信息管理</课程名称>\n" +
                        "        <学分>2</学分>\n" +
                        "        <授课老师>傅再育</授课老师>\n" +
                        "        <授课地点>南教513</授课地点>\n" +
                        "    </course>\n" +
                        "</courses>";
                String CXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n<courses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3schools.com course.xsd\">\n" +
                        "    <course>\n" +
                        "        <Cno>0B01</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>季勇</Tec>\n" +
                        "        <Pla>北教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B03</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>3</Cpt>\n" +
                        "        <Tec>黄建秋</Tec>\n" +
                        "        <Pla>南教302</Pla>\n" +
                        "    </course>\n" +
                        "\n" +
                        "    <course>\n" +
                        "        <Cno>0B07</Cno>\n" +
                        "        <Cnm>古建筑鉴定分析学</Cnm>\n" +
                        "        <Cpt>2</Cpt>\n" +
                        "        <Tec>贺云翱</Tec>\n" +
                        "        <Pla>北教102</Pla>\n" +
                        "    </course>\n" +
                        "</courses>";*/

                //==============end test

                //去除标签中的内容

                int indexC = CXmlString.indexOf(replaceAttribute.get(1));

                if (indexC == -1) {
                    System.err.println("Can't find attribute in CXmlString!");
                }

                String tC = CXmlString.substring(0, indexC - 1) + CXmlString.substring(indexC + replaceAttribute.get(1).length());
                CXmlString = tC;

                //将String格式的回复存储到本地的xml文件中

                utility.changeStringToXmlFile(CXmlString, tempFilePathForCXml);



                //持久化存储
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForCXml, formatCCourse, 2);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store C courses!");
                    return false;
                }
                return true;
                //String formatBXmlPath="src/main/resources/static/result/classBXml.xml";

            }

        }
        return true;
    }

    /**
     * 获得provider的所有选课
     * @param provider 提供课程信息的服务器
     */
    public boolean getAllChoice(String provider) {
        //先找到供应商，拿回xml文件
        switch (provider) {
            case "A": {
                //向A server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得A选课的url
                String urlA = Aurl+"/integration/sendXML";
                //获得xml文件的string形式
/*                ResponseEntity<String> AXmlFileResponse = HTTPClient.sendPostRequest(urlA, "");
                String AXmlString = AXmlFileResponse.getBody();
                System.out.println("AXml=" + AXmlString);*/

                String AXmlString="<?xml version=\"1.0\" encoding=\"gb2312\"?>\n" +
                        "<chooseCourses xmlns=\"http://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"chooseCourse.xsd\">\n" +
                        "    <chooseCourse>\n" +
                        "        <Sno>190322002</Sno>\n" +
                        "        <Sde>C</Sde>\n" +
                        "        <Cno>0B01</Cno>\n" +
                        "        <Cde>C</Cde>\n" +
                        "        <Grd>1</Grd>\n" +
                        "    </chooseCourse>\n" +
                        "</chooseCourses>";

                //去除标签中的内容
                int indexA = AXmlString.indexOf(replaceAttribute.get(0));
                if (indexA == -1) {
                    System.err.println("Can't find attribute in AXmlString!");
                }

                String tA = AXmlString.substring(0, indexA - 1) + AXmlString.substring(indexA + replaceAttribute.get(0).length());
                AXmlString = tA;


                //将string类型的xml转换成xml文件,存到了下面路径里面(formatChoice/AChoice/formatXXX.xml)
                utility.changeStringToXmlFile(AXmlString, formatAChoice);

                break;
            }
            case "B": {
                //向 B server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得B选课的url
                String urlB = Burl+"/integration/sendXML";

                //获得xml文件的string形式
                ResponseEntity<String> BXmlFileResponse = HTTPClient.sendPostRequest(urlB, "");
                String BXmlString = BXmlFileResponse.getBody();
                System.out.println("BXml=" + BXmlString);


                //==========测试部分
               /* String BXmlString="";*/

                //==============end test

                //去除标签中的内容
                int indexB = BXmlString.indexOf(replaceAttribute.get(0));
                if (indexB == -1) {
                    System.err.println("Can't find attribute in AXmlString!");
                }

                String tB = BXmlString.substring(0, indexB - 1) + BXmlString.substring(indexB + replaceAttribute.get(0).length());
                BXmlString = tB;

                //将String格式的回复存到了下面路径里面(formatChoice/BChoice/formatXXX.xml)
                utility.changeStringToXmlFile(BXmlString, formatBChoice);
                break;
            }
            case "C": {
                //询问server C
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得C选课的url
                String urlC = Curl+"/integration/sendXML";
                //获得xml文件的string形式

                ResponseEntity<String> CXmlFileResponse = HTTPClient.sendPostRequest(urlC, "");
                String CXmlString = CXmlFileResponse.getBody();
                System.out.println("CXml=" + CXmlString);

                //==========测试部分
               /* String BXmlString="";*/

                //==============end test

                //去除标签中的内容

                int indexC = CXmlString.indexOf(replaceAttribute.get(0));

                if (indexC == -1) {
                    System.err.println("Can't find attribute in CXmlString!");
                }

                String tC = CXmlString.substring(0, indexC - 1) + CXmlString.substring(indexC + replaceAttribute.get(0).length());
                CXmlString = tC;

                //将String格式的回复存到了下面路径里面(formatChoice/CChoice/formatXXX.xml)

                utility.changeStringToXmlFile(CXmlString, formatCChoice);

                break;
            }


        }
        return true;
    }


    /**
     * 获得provider的所有学生
     * @param provider 提供课程信息的服务器
     */
    public boolean getAllStudents(String provider) {
        //先找到供应商，拿回xml文件
        switch (provider) {
            case "A": {
                //向A server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得A学生的url
                String urlA = Aurl+"/integration/sendXML";
                //获得xml文件的string形式
                ResponseEntity<String> AXmlFileResponse = HTTPClient.sendPostRequest(urlA, "");
                String AXmlString = AXmlFileResponse.getBody();
                System.out.println("AXml=" + AXmlString);

                /* String AXmlString="";*/

                //去除标签中的内容
                int indexA = AXmlString.indexOf(replaceAttribute.get(1));
                if (indexA == -1) {
                    System.err.println("Can't find attribute in AXmlString!");
                }

                String tA = AXmlString.substring(0, indexA - 1) + AXmlString.substring(indexA + replaceAttribute.get(1).length());
                AXmlString = tA;

                //将string类型的xml转换成xml文件,并将其存入tempFilePathForAXml（tempXXX/originXXX.xml）
                utility.changeStringToXmlFile(AXmlString, tempFilePathForAXml);

                //将发回的请求转换成统一格式，存到了下面路径里面(formatChoice/AChoice/formatXXX.xml)
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForAXml, formatAStudent, 3);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store A students!");
                    return false;
                }
                return true;

            }
            case "B": {
                //向 B server 发送请求
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得B学生的url
                String urlB = Burl+"/integration/sendXML";

                //获得xml文件的string形式
                ResponseEntity<String> BXmlFileResponse = HTTPClient.sendPostRequest(urlB, "");
                String BXmlString = BXmlFileResponse.getBody();
                System.out.println("BXml=" + BXmlString);


                //==========测试部分
                /* String BXmlString="";*/

                //==============end test

                //去除标签中的内容
                int indexB = BXmlString.indexOf(replaceAttribute.get(1));
                if (indexB == -1) {
                    System.err.println("Can't find attribute in BXmlString!");
                }

                String tB = BXmlString.substring(0, indexB - 1) + BXmlString.substring(indexB + replaceAttribute.get(1).length());
                BXmlString = tB;


                //将String格式的回复存储到本地的xml文件中(tempB/originxxx)
                utility.changeStringToXmlFile(BXmlString, tempFilePathForBXml);


                //将发回的请求转换成统一格式，存到了下面路径里面(formatChoice/BStudents/formatXXX.xml)
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForBXml, formatBStudent, 3);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store B students!");
                    return false;
                }
                return true;
            }
            case "C": {
                //询问server C
                Map<String, String> params = new HashMap<>();
                //todo: 更改获得C学生的url
                String urlC = Curl+"/integration/sendXML";
                //获得xml文件的string形式

                ResponseEntity<String> CXmlFileResponse = HTTPClient.sendPostRequest(urlC, "");
                String CXmlString = CXmlFileResponse.getBody();
                System.out.println("CXml=" + CXmlString);

                //==========测试部分
                /* String BXmlString="";*/

                //==============end test

                //去除标签中的内容

                int indexC = CXmlString.indexOf(replaceAttribute.get(1));

                if (indexC == -1) {
                    System.err.println("Can't find attribute in CXmlString!");
                }

                String tC = CXmlString.substring(0, indexC - 1) + CXmlString.substring(indexC + replaceAttribute.get(1).length());
                CXmlString = tC;

                //将String格式的回复存储到本地的xml文件中

                utility.changeStringToXmlFile(CXmlString, tempFilePathForCXml);


                //将发回的请求转换成统一格式，存到了下面路径里面(formatChoice/CChoice/formatXXX.xml)
                boolean isChangedSuccessfully = xmlResolver.ABCToFormatGivenFullPath(tempFilePathForCXml, formatCStudent, 3);
                System.out.println(isChangedSuccessfully);
                if (!isChangedSuccessfully) {
                    System.out.println("Fail to store C students!");
                    return false;
                }
                return true;


            }


        }
        return true;
    }
}
