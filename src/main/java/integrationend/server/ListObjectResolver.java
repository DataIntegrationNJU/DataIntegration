package integrationend.server;

import integrationend.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.BackOff;

/**
 * 这个类用于获得传给前端的对象
 */
@Service
public class ListObjectResolver {
    //==================================持久化存储部分路径，用于给集成端可视化使用
    //Choice
    private String formatAChoice="src/main/resources/static/formatChoice/AChoice/formatChoiceAXml.xml";
    private String formatBChoice="src/main/resources/static/formatChoice/BChoice/formatChoiceBXml.xml";
    private String formatCChoice="src/main/resources/static/formatChoice/CChoice/formatChoiceCXml.xml";
    private boolean choiceAReady=false;
    private boolean choiceBReady=false;
    private boolean choiceCReady=false;


    //Course

    private String formatACourse="src/main/resources/static/formatCourse/ACourse/formatCourseAXml.xml";
    private String formatBCourse="src/main/resources/static/formatCourse/BCourse/formatCourseBXml.xml";
    private String formatCCourse="src/main/resources/static/formatCourse/CCourse/formatCourseCXml.xml";
    private boolean courseAReady=false;
    private boolean courseBReady=false;
    private boolean courseCReady=false;

    //Students

    private String formatAStudent="src/main/resources/static/formatStudent/AStudent/formatStudentAXml.xml";
    private String formatBStudent="src/main/resources/static/formatStudent/BStudent/formatStudentBXml.xml";
    private String formatCStudent="src/main/resources/static/formatStudent/CStudent/formatStudentCXml.xml";
    private boolean studentAReady=false;
    private boolean studentBReady=false;
    private boolean studentCReady=false;


    private IntegrationResolver integrationResolver=new IntegrationResolver();
    private Transformer transformer=new Transformer();
    /**
     * 获取provider的所有学生信息
     * @param provider 取值为A/B/C
     * @return
     */
    public StudentList getAllStudents(String provider){
        StudentList studentList=null;
        switch (provider){
            case "A":
                if(!studentAReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllStudents(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //studentAReady=true;
                }
                studentList=Transformer.transformXMLFileToStudentList(formatAStudent);
                break;
            case "B":
                if(!studentBReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllStudents(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //studentBReady=true;
                }
                studentList=Transformer.transformXMLFileToStudentList(formatBStudent);
                break;
            case "C":
                if(!studentCReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllStudents(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //studentCReady=true;
                }
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

        ChoiceList choiceList=null;
        switch (provider){
            case "A":
                if(!choiceAReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllChoice(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //choiceAReady=true;
                }
                choiceList=Transformer.transformXMLFileToChoiceList(formatAChoice);
                break;
            case "B":
                if(!choiceBReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllChoice(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //choiceBReady=true;
                }
                choiceList=Transformer.transformXMLFileToChoiceList(formatBChoice);
                break;
            case "C":
                if(!choiceCReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllChoice(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //choiceCReady=true;
                }
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

        CourseList courseList=null;
        switch (provider){
            case "A":
                if(!courseAReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllCourses(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //courseAReady=true;
                }
                courseList=Transformer.transformXMLFileToCourseList(formatACourse);
                break;
            case "B":
                if(!courseBReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllCourses(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //courseBReady=true;
                }
                courseList=Transformer.transformXMLFileToCourseList(formatBCourse);
                break;
            case "C":
                if(!courseCReady){
                    boolean isStoreSuccessfully=integrationResolver.getAllCourses(provider);
                    if(!isStoreSuccessfully){
                        return null;
                    }
                    //courseCReady=true;
                }
                courseList=Transformer.transformXMLFileToCourseList(formatCCourse);
                break;
        }
        return courseList;

    }


}
