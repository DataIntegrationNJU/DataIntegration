package integrationend.dao;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ChoiceList {
    @XmlElement(name = "choice")
    private List<Choice> choices;
    public String toString(){
        String result="";
        for(Choice c:choices){
            result+=c.toString()+"\n";
        }
        return result;
    };
}
