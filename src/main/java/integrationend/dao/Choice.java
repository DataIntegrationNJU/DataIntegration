package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chooseCourse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Choice {
    @XmlElement(name = "Sno")
    private String sno;
    @XmlElement(name = "Sde")
    private String sde;

    @XmlElement(name = "Cno")
    private String cno;

    @XmlElement(name = "Cde")
    private String cde;

    @XmlElement(name = "Grd")
    private String grd;

    public String toString(){
        String result="sno="+sno+" sde="+sde+" cno="+cno+" cde="+cde+" grd="+grd;
        return result;
    }
}
