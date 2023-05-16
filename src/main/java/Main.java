import integrationend.Utilities.Utility;
import integrationend.dao.ChoiceList;
import integrationend.dao.CourseList;
import integrationend.dao.StudentList;
import integrationend.dao.Transformer;
import integrationend.server.IntegrationResolver;

public class Main {
    private static Utility utility=new Utility();
    private static IntegrationResolver integrationResolver=new IntegrationResolver();
    private static Transformer transformer=new Transformer();
    public static void main(String[] args){
        /*String filePath="src\\main\\resources\\static\\result\\1.xml";
        utility.changeStringToXmlFile("mmmmmmm",filePath);*/

        //integrationResolver.getAllCourses("B","A");
       // CourseList c=transformer.transformXMLFileToCourseList("E:\\Work\\数据集成\\homework\\DataIntegration\\src\\main\\resources\\static\\formatCourse\\ACourse\\formatCourseAXml.xml");
        //ChoiceList cL=transformer.transformXMLFileToChoiceList("E:\\Work\\数据集成\\homework\\DataIntegration\\src\\main\\resources\\static\\formatChoice\\AChoice\\formatChoiceAXml.xml");
        StudentList sL=transformer.transformXMLFileToStudentList("E:\\Work\\数据集成\\homework\\DataIntegration\\src\\main\\resources\\static\\formatStudent\\AStudent\\formatStudentAXml.xml");
        System.out.println(sL.toString());
    }
}
