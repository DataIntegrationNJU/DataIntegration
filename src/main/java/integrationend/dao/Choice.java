package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "choice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Choice {
    @XmlElement(name = "sid")
    private String sid;
    @XmlElement(name = "cid")
    private String cid;
    @XmlElement(name = "score")
    private int score;


    public String toString(){
        String result="sid="+sid+" cid="+cid+" score="+score;
        return result;
    }
}
