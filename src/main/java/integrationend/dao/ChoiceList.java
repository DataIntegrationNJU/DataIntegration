package integrationend.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import lombok.Data;


@XmlRootElement(name = "chooseCourses")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ChoiceList {
    @XmlElement(name = "chooseCourse")
    private List<Choice> choices;

    public String toString() {
        String result = "";
        for (Choice c : choices) {
            result += c.toString() + "\n";
        }
        return result;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
