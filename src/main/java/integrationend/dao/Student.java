package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "sex")
    private String sex;
    @XmlElement(name = "major")
    private String major;

    public String toString(){
        String result="id="+id+" name="+name+" sex="+sex+" major"+major;
        return result;
    }

}
