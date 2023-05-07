package integrationend;

import integrationend.dao.ChoiceList;
import integrationend.dao.CourseList;
import integrationend.server.ListObjectResolver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListObjectResolverTester {

    ListObjectResolver listObjectResolver=new ListObjectResolver();

    @Test
    void testChoices(){
        ChoiceList choiceList=listObjectResolver.getAllChoices("A");
        System.out.println(choiceList);
    }
    @Test
    void testClass(){
        CourseList courseList=listObjectResolver.getAllCourses("A");
        System.out.println(courseList);
    }


}
