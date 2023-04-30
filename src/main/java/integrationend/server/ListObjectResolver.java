package integrationend.server;

import integrationend.dao.*;
import org.springframework.util.backoff.BackOff;

/**
 * 这个类用于获得传给前端的对象
 */
public class ListObjectResolver {
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


    private IntegrationResolver integrationResolver=new IntegrationResolver();
    private Transformer transformer=new Transformer();
    /**
     * 获取provider的所有学生信息
     * @param provider 取值为A/B/C
     * @return
     */
    public StudentList getAllStudents(String provider){
        boolean isStoreSuccessfully=integrationResolver.getAllStudents(provider);
        if(isStoreSuccessfully){
            return null;
        }
        StudentList studentList=null;
        switch (provider){
            case "A":
                studentList=Transformer.transformXMLFileToStudentList(formatAStudent);
                break;
            case "B":
                studentList=Transformer.transformXMLFileToStudentList(formatBStudent);
                break;
            case "C":
                studentList=Transformer.transformXMLFileToStudentList(formatCStudent);
                break;
        }
        return studentList;

    }


    /**
     * 获取provider的所有选课信息
     * @param provider 取值为A/B/C
     * @return
     */
    public ChoiceList getAllChoices(String provider){
        boolean isStoreSuccessfully=integrationResolver.getAllChoice(provider);
        if(isStoreSuccessfully){
            return null;
        }
        ChoiceList choiceList=null;
        switch (provider){
            case "A":
                choiceList=Transformer.transformXMLFileToChoiceList(formatAChoice);
                break;
            case "B":
                choiceList=Transformer.transformXMLFileToChoiceList(formatBChoice);
                break;
            case "C":
                choiceList=Transformer.transformXMLFileToChoiceList(formatCChoice);
                break;
        }
        return choiceList;

    }

    /**
     * 获取provider的所有课程信息
     * @param provider 取值为A/B/C
     * @return
     */
    public CourseList getAllCourses(String provider){
        boolean isStoreSuccessfully=integrationResolver.getAllCourses(provider);
        if(isStoreSuccessfully){
            return null;
        }
        CourseList courseList=null;
        switch (provider){
            case "A":
                courseList=Transformer.transformXMLFileToCourseList(formatACourse);
                break;
            case "B":
                courseList=Transformer.transformXMLFileToCourseList(formatBCourse);
                break;
            case "C":
                courseList=Transformer.transformXMLFileToCourseList(formatCCourse);
                break;
        }
        return courseList;

    }


}
