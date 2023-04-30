package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentList {
    @XmlElement(name = "student")
    private List<Student> students;
    public String toString(){
        String result="";
        for(Student c:students){
            result+=c.toString()+"\n";
        }
        return result;
    };
}
