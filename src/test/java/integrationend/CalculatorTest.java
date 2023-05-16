package integrationend;


import java.util.List;

import integrationend.dao.Choice;
import integrationend.dao.Course;
import integrationend.dao.Student;
import integrationend.server.Calculator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    void Test1() {
        List<Course> courseList = calculator.getAllCourse();
        System.out.println("*************************");
        System.out.println(courseList);
    }

    @Test
    void Test2() {
        List<Choice> choiceList = calculator.getAllChoice();
        System.out.println("*************************");
        System.out.println(choiceList);
    }

    @Test
    void Test3() {
        List<Student> studentList = calculator.getAllStudent();
        System.out.println("*************************");
        System.out.println(studentList);
    }

    @Test
    void TestJSON() {
        JSONArray jsonArray = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("e", "getAllChoice()");
        jsonArray.add(message);

        JSONObject test = jsonArray.getJSONObject(0);
        System.out.println("*************************");
        System.out.println(test.get("error"));
    }

    @Test
    void TestGetChoiceAndCourseInfoByStudentId() {
        System.out.println("*************************");
        System.out.println(calculator.getChoiceAndCourseInfoByStudentId("201905075001"));
        System.out.println("*************************");
        System.out.println(calculator.getChoiceAndCourseInfoByStudentId("201905075002"));
        System.out.println("*************************");
        System.out.println(calculator.getChoiceAndCourseInfoByStudentId("201905075003"));
    }

    @Test
    void TestGetGPAByStudentId() {
        System.out.println("*************************");
        System.out.println(calculator.getGPAByStudentId("201905075001"));
        System.out.println("*************************");
        System.out.println(calculator.getGPAByStudentId("201905075002"));
        System.out.println("*************************");
        System.out.println(calculator.getGPAByStudentId("201905075003"));
    }

    @Test
    void TestGetABCStudentNumAndPercentOfOneCourse() {
        System.out.println("*************************");
        System.out.println(calculator.getABCStudentNumAndPercentOfOneCourse("05075001"));
        System.out.println("*************************");
        System.out.println(calculator.getABCStudentNumAndPercentOfOneCourse("05075078"));
        System.out.println("*************************");
        System.out.println(calculator.getABCStudentNumAndPercentOfOneCourse("05075003"));
        System.out.println("*************************");
        System.out.println(calculator.getABCStudentNumAndPercentOfOneCourse("05075010"));
    }

    @Test
    void TestGetCourseMathById() {
        System.out.println("*************************");
        System.out.println(calculator.getCourseMathById("05075001"));
        System.out.println("*************************");
        System.out.println(calculator.getCourseMathById("05075078"));
        System.out.println("*************************");
        System.out.println(calculator.getCourseMathById("05075003"));
        System.out.println("*************************");
        System.out.println(calculator.getCourseMathById("05075010"));
    }

}
