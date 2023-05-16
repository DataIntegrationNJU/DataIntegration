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
    private double grd;

    public String toString() {
        String result = "sno=" + sno + " sde=" + sde + " cno=" + cno + " cde=" + cde + " grd=" + grd;
        return result;
    }

    public String getSno() {
        return sno;
    }
    public String getSde() {
        return sde;
    }
    public String getCno() {
        return cno;
    }
    public String getCde() {
        return cde;
    }
    public double getGrd() {
        return grd;
    }

    public void setChoice(String sno, String sde, String cno, String cde, double grd) {
        this.sno = sno;
        this.sde = sde;
        this.cno = cno;
        this.cde = cde;
        this.grd = grd;
    }
}
