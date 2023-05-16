package integrationend.dao;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "course")
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "score")
    private int score;
    @XmlElement(name = "teacher")
    private String teacher;
    @XmlElement(name = "location")
    private String location;

    public String toString() {
        String result = "id=" + id + " name=" + name + " score=" + score + " teacher" + teacher + " location=" + location;
        return result;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setCourse(String id, String name, int score, String teacher, String location) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.teacher = teacher;
        this.location = location;
    }
}
