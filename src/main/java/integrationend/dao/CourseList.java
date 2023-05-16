package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import lombok.Data;


@XmlRootElement(name = "courses")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CourseList {
    @XmlElement(name = "course")
    private List<Course> courses;

    public String toString() {
        String result = "";
        for (Course c : courses) {
            result += c.toString() + "\n";
        }
        return result;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
