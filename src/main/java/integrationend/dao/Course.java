package integrationend.dao;


import javax.xml.bind.annotation.*;

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

    public String toString(){
        String result="id="+id+" name="+name+" score="+score+" teacher"+teacher+" location="+location;
        return result;
    }
}
