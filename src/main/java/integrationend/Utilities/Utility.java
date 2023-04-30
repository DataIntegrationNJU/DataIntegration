package integrationend.Utilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;


public class Utility {
    /**
     * 将xml字符串装入指定文件中
     * @param fileStr 待转换的xml文件的字符串格式
     * @param outputFileLocation 转换出的文件的存储位置
     */
    public void changeStringToXmlFile(String fileStr,String outputFileLocation){
       //创建文件
        File newFile = new File(outputFileLocation);
        //如果该文件已经存在，则不用创建，也不会报错
        try {
            System.out.println(newFile.createNewFile());
        }catch (IOException e){
            System.out.println("Create fail!");
        }

//        if (!newFile.exists()) {
//            newFile.mkdirs();// 能创建多级目录
//        }

        //写入文件
        WriteToFile(outputFileLocation,fileStr,false);



    }

    /**
     *  将字符串写入文件
     * @param filePath 文件路径
     * @param content 写入的内容
     * @param append 是否在原有内容上追加 append为false的情况下，如果原文件中有内容，则会覆盖源文件内容
     */
    public static void WriteToFile(String filePath, String content, boolean append)
    {
        FileWriter fileWriter;
        try
        {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Can't find file!");
            e.printStackTrace();
        }
    }


    public static String formatXML(Document document, String charset) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        StringWriter sw =  new StringWriter();
        XMLWriter xw =  new XMLWriter(sw, format);
        try {
            xw.write(document);
            xw.flush();
            xw.close();
        }  catch (IOException e) {
            System.err.println( "格式化XML文档发生异常，请检查！");
        }
        return sw.toString();
    }


}
