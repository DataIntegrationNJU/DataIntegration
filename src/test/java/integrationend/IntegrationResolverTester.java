package integrationend;

import integrationend.server.IntegrationResolver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrationResolverTester {
    IntegrationResolver integrationResolver=new IntegrationResolver();

    @Test
    void testGetAllCourses(){
        boolean result=integrationResolver.getAllCourses("A");

    }

    @Test
    void testGetAllChoices(){
        boolean result=integrationResolver.getAllChoice("A");

    }

    @Test
    void testGetAllStudent(){
        boolean result=integrationResolver.getAllStudents("A");

    }
}
