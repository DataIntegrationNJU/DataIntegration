package integrationend.server;

import org.dom4j.io.OutputFormat;
import org.dom4j.DocumentException;
import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


/**
 * 用于解决xml格式转换问题
 */
@Service //todo
public class XMLResolver {
    /**
     * 处理后foramt xml文件存储的位置
     */
    private String destinationFormatXmlFilePath="src/main/resources/static/result/";
    /**
     * 处理后A xml文件存储的位置
     */
    private String destinationAXmlFilePath="src/main/resources/static/tempAXml/";
    /**
     * 处理后B xml文件存储的位置
     */
    private String destinationBXmlFilePath="src/main/resources/static/tempBXml/";
    /**
     * 处理后C xml文件存储的位置
     */
    private String destinationCXmlFilePath="src/main/resources/static/tempCXml/";
    private String choiceToAXslFilePath="src/main/resources/static/choiceToA.xsl";
    private String choiceToBXslFilePath="src/main/resources/static/choiceToB.xsl";
    private String choiceToCXslFilePath="src/main/resources/static/choiceToC.xsl";


    private String classToAXslFilePath="src/main/resources/static/classToA.xsl";
    private String classToBXslFilePath="src/main/resources/static/classToB.xsl";
    private String classToCXslFilePath="src/main/resources/static/classToC.xsl";


    private String studentToAXslFilePath="src/main/resources/static/studentToA.xsl";
    private String studentToBXslFilePath="src/main/resources/static/studentToB.xsl";
    private String studentToCXslFilePath="src/main/resources/static/studentToC.xsl";

    private String formatClassFilePath="src/main/resources/static/formatClass.xsl";
    private String formatClassChoiceFilePath="src/main/resources/static/formatClassChoice.xsl";
    private String formatStudentFilePath="src/main/resources/static/formatStudent.xsl";

    /**
     * 将统一选课格式转换到 A/B/C选课格式
     * @param targetXmlFilePath
     * @param targetFileName 转换后的文件名字,如formatChoice，存储时，会将该文件后面加上".xml"
     * @param des 希望转换成A 1/B 2/C 3
     * @return 结果文件的存储路径
     */
    public String choiceToABC(String targetXmlFilePath,String targetFileName,int des){
        //读目标文件
        SAXReader saxReader = new SAXReader();
        Document document =null;
        try {
            document=saxReader.read(new FileReader(new File(targetXmlFilePath)));//从xml文件获取数据
        }catch (FileNotFoundException e){
            System.err.println("Can't find ");
        }catch (DocumentException e){

        }

        //通过xsl转换文件
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer=null;
        try{
            switch (des){
                case 1:
                    transformer=factory.newTransformer(new StreamSource(choiceToAXslFilePath));
                    break;
                case 2:
                    transformer=factory.newTransformer(new StreamSource(choiceToBXslFilePath));
                    break;
                case 3:
                    transformer=factory.newTransformer(new StreamSource(choiceToCXslFilePath));
                    break;
            }

        }catch (TransformerConfigurationException e){

        }

        //写入
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
//========XML 转换开始========
        try{
            transformer.transform(source, result);
        }catch (TransformerException e){
            System.err.println("Can't transform!");
        }

        Document transformedDoc = result.getDocument();
        Writer w =null;
        String resultPath="";
        switch (des){
            case 1:
                resultPath=destinationAXmlFilePath+targetFileName+".xml";
                break;
            case 2:
                resultPath=destinationBXmlFilePath+targetFileName+".xml";
                break;
            case 3:
                resultPath=destinationCXmlFilePath+targetFileName+".xml";
                break;
        }

        try{
            w=new FileWriter(resultPath);
            OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("GB2312");
            XMLWriter xw = new XMLWriter(w, opf);
            xw.write(transformedDoc);
// 关闭文件流
            xw.close();
            w.close();
        }catch (IOException e){

        }
        return resultPath;

    }



    /**
     * 将统一课程格式转换到 A/B/C课程格式
     * @param targetXmlFilePath
     * @param targetFileName 转换后的文件名字,如formatChoice，存储时，会将该文件后面加上".xml"
     * @param des 希望转换成A 1/B 2/C 3
     * @return 结果文件的存储路径
     */
    public String classToABC(String targetXmlFilePath,String targetFileName,int des){
        //读目标文件
        SAXReader saxReader = new SAXReader();
        Document document =null;
        try {
            document=saxReader.read(new FileReader(new File(targetXmlFilePath)));//从xml文件获取数据
        }catch (FileNotFoundException e){
            System.err.println("Can't find ");
        }catch (DocumentException e){

        }

        //通过xsl转换文件
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer=null;
        try{
            switch (des){
                case 1:
                    transformer=factory.newTransformer(new StreamSource(classToAXslFilePath));
                    break;
                case 2:
                    transformer=factory.newTransformer(new StreamSource(classToBXslFilePath));
                    break;
                case 3:
                    transformer=factory.newTransformer(new StreamSource(classToCXslFilePath));
                    break;
            }

        }catch (TransformerConfigurationException e){

        }

        //写入
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
//========XML 转换开始========
        try{
            transformer.transform(source, result);
        }catch (TransformerException e){
            System.err.println("Can't transform!");
        }

        Document transformedDoc = result.getDocument();
        Writer w =null;
        String resultPath="";
        switch (des){
            case 1:
                resultPath=destinationAXmlFilePath+targetFileName+".xml";
                break;
            case 2:
                resultPath=destinationBXmlFilePath+targetFileName+".xml";
                break;
            case 3:
                resultPath=destinationCXmlFilePath+targetFileName+".xml";
                break;
        }
        try{
            w=new FileWriter(resultPath);
            OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("GB2312");
            XMLWriter xw = new XMLWriter(w, opf);
            xw.write(transformedDoc);
// 关闭文件流
            xw.close();
            w.close();
        }catch (IOException e){

        }
        return resultPath;

    }


    /**
     * 将统一学生格式转换到 A/B/C学生格式
     * @param targetXmlFilePath
     * @param targetFileName 转换后的文件名字,如formatChoice，存储时，会将该文件后面加上".xml"
     * @param des 希望转换成A 1/B 2/C 3
     * @return 结果文件的存储路径
     */
    public String studentToABC(String targetXmlFilePath,String targetFileName,int des){
        //读目标文件
        SAXReader saxReader = new SAXReader();
        Document document =null;
        try {
            document=saxReader.read(new FileReader(new File(targetXmlFilePath)));//从xml文件获取数据
        }catch (FileNotFoundException e){
            System.err.println("Can't find ");
        }catch (DocumentException e){

        }

        //通过xsl转换文件
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer=null;
        try{
            switch (des){
                case 1:
                    transformer=factory.newTransformer(new StreamSource(studentToAXslFilePath));
                    break;
                case 2:
                    transformer=factory.newTransformer(new StreamSource(studentToBXslFilePath));
                    break;
                case 3:
                    transformer=factory.newTransformer(new StreamSource(studentToCXslFilePath));
                    break;
            }

        }catch (TransformerConfigurationException e){

        }

        //写入
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
//========XML 转换开始========
        try{
            transformer.transform(source, result);
        }catch (TransformerException e){
            System.err.println("Can't transform!");
        }

        Document transformedDoc = result.getDocument();
        Writer w =null;
        String resultPath="";
        switch (des){
            case 1:
                resultPath=destinationAXmlFilePath+targetFileName+".xml";
                break;
            case 2:
                resultPath=destinationBXmlFilePath+targetFileName+".xml";
                break;
            case 3:
                resultPath=destinationCXmlFilePath+targetFileName+".xml";
                break;
        }
        try{
            w=new FileWriter(resultPath);
            OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("GB2312");
            XMLWriter xw = new XMLWriter(w, opf);
            xw.write(transformedDoc);
// 关闭文件流
            xw.close();
            w.close();
        }catch (IOException e){

        }

        return resultPath;
    }


    /**
     * 将A/B/C格式 转换成标准格式
     * @param targetXmlFilePath
     * @param targetFileName 转换后的文件名字,如formatChoice，存储时，会将该文件后面加上".xml"
     * @param des 希望转换成choice 1/class 2/student 3
     * @return 结果文件存储路径
     */
    public String ABCToFormat(String targetXmlFilePath,String targetFileName,int des){
        //读目标文件
        SAXReader saxReader = new SAXReader();
        Document document =null;
        try {
            document=saxReader.read(new FileReader(targetXmlFilePath));//从xml文件获取数据
        }catch (FileNotFoundException e){
            System.err.println("Can't find ");
        }catch (DocumentException e){

        }


        //通过xsl转换文件
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer=null;
        try{
            switch (des){
                case 1:
                    transformer=factory.newTransformer(new StreamSource(formatClassChoiceFilePath));
                    break;
                case 2:
                    transformer=factory.newTransformer(new StreamSource(formatClassFilePath));
                    break;
                case 3:
                    transformer=factory.newTransformer(new StreamSource(formatStudentFilePath));
                    break;
            }

        }catch (TransformerConfigurationException e){

        }

        //写入
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
//========XML 转换开始========
        try{
            transformer.transform(source, result);
        }catch (TransformerException e){
            System.err.println("Can't transform!");
        }

        Document transformedDoc = result.getDocument();
        Writer w =null;
        String resultPath=destinationFormatXmlFilePath+targetFileName+".xml";
        try{
            w=new FileWriter(resultPath);
            OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("GB2312");
            XMLWriter xw = new XMLWriter(w, opf);
            xw.write(transformedDoc);//todo:如果这里的文件已经存在怎么办？
// 关闭文件流
            xw.close();
            w.close();
        }catch (IOException e){

        }
        return resultPath;

    }







}
