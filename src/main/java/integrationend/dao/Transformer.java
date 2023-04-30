package integrationend.dao;

import com.thoughtworks.xstream.XStream;
import integrationend.Utilities.Utility;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * 将xml文件转换成对象 持久化存储——>对象
 */
public class Transformer {

     public static CourseList transformXMLFileToCourseList(String filePath){
         String courseListStr="";
//将xml文件变成xml字符串=========================

         SAXReader saxReader = new SAXReader();
         Document document = null;
         try {
             document = saxReader.read(new FileReader(filePath));//从xml文件获取数据
             courseListStr = Utility.formatXML(document,"gb2312");


         } catch (FileNotFoundException e) {
             System.err.println("Can't find ");
         } catch (DocumentException e) {
         }


//将字符串转换成对象===================
         CourseList courseList = null;
         Reader reader = null;
         try{
             JAXBContext context = JAXBContext.newInstance(CourseList.class);
             Unmarshaller unmarshaller = context.createUnmarshaller();
             reader = new StringReader(courseListStr);
             courseList = (CourseList) unmarshaller.unmarshal(reader);
         }catch (JAXBException e){
             System.out.println("Can't resolve transition!");
         }





        return courseList;
    }


    public static ChoiceList transformXMLFileToChoiceList(String filePath){
        String choiceListStr="";
//将xml文件变成xml字符串=========================

        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new FileReader(filePath));//从xml文件获取数据
            choiceListStr = Utility.formatXML(document,"gb2312");


        } catch (FileNotFoundException e) {
            System.err.println("Can't find ");
        } catch (DocumentException e) {
        }


//将字符串转换成对象===================
        ChoiceList choiceList = null;
        Reader reader = null;
        try{
            JAXBContext context = JAXBContext.newInstance(ChoiceList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new StringReader(choiceListStr);
            choiceList = (ChoiceList) unmarshaller.unmarshal(reader);
        }catch (JAXBException e){
            System.out.println("Can't resolve transition!");
        }

        return choiceList;
    }


    public static StudentList transformXMLFileToStudentList(String filePath){
        String studentListStr="";
//将xml文件变成xml字符串=========================

        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new FileReader(filePath));//从xml文件获取数据
            studentListStr = Utility.formatXML(document,"gb2312");


        } catch (FileNotFoundException e) {
            System.err.println("Can't find ");
        } catch (DocumentException e) {
        }


//将字符串转换成对象===================
        StudentList studentList = null;
        Reader reader = null;
        try{
            JAXBContext context = JAXBContext.newInstance(StudentList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new StringReader(studentListStr);
            studentList = (StudentList) unmarshaller.unmarshal(reader);
        }catch (JAXBException e){
            System.out.println("Can't resolve transition!");
        }

        return studentList;
    }
}
