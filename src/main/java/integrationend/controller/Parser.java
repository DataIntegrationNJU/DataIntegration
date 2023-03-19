package integrationend.controller;

import integrationend.server.IntegrationResolver;
import integrationend.server.XMLResolver;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.*;
import org.dom4j.Document;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/fetch")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Parser {
    /**
     * 请求的动作是什么
     */
    private String action="";
    /**
     * 请求者是A/B/C
     */
    private String requester="";

    private XMLResolver xmlResolver=new XMLResolver();
    private IntegrationResolver integrationResolver=new IntegrationResolver();


    /**
     * 根据请求方参数向只当服务器请求数据，并以请求方服务器的xml格式返回数据
     * 具体实现：
     *  请求将目标服务器的数据（目标服务器所能识别的xml格式）
     *  将目标服务器特有的xml格式转换成统一的xml格式
     *  将统一的xml格式转化成请求方服务器可以识别的格式
     * @param request
     */
    @PostMapping("/class")
    public void askAllCoursesInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody String requestBody){
       System.out.println(requestBody);

        String requester=request.getHeader("requester");
        String provider=request.getHeader("provider");
        String result=integrationResolver.getAllCourses(requester,provider);

        //把转化好的文件转成String形式

        try {
            System.out.println(result);
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-type", "text/html; charset=UTF-8");
            response.getWriter().write(result);
        }catch (IOException e){

        }
        //return result;
    }

    /**
     * 选课通信
     * 获取请求方的选课信息，并通过socket转发
     * @param request
     * @param response
     */
    @PostMapping("/selectClass")
    public void selectCourse(HttpServletRequest request, HttpServletResponse response){
        System.out.println("login!");
        //根据需要提供的业务进行处理
        //发给
        String requestServer=(String) request.getHeader("provider"); //想要请求的服务方
        String url="";
        String result="";
        //为请求所需的url赋值
        switch (requestServer){
            case "A":
                url="http://192.168.10.153:9091/integration/takeCourse";
                break;
            case "B":
                url="http://192.168.10.153:9091/integration/takeCourse";
                break;
            case "C":
                url="http://192.168.10.153:9091/integration/takeCourse";
                break;
        }
       /* System.out.println(request.getHeader("sno"));
        System.out.println();*/

        Map<String,String> params=new HashMap<>();
        params.put("sno",request.getHeader("sno"));
        params.put("cno",request.getHeader("cno"));
        params.put("de",request.getHeader("de"));
        //添加信息学号、课程号、select标识
        ResponseEntity<String> q = HTTPClient.sendPostRequest(url, params);
        result = q.getBody();
        System.out.println("result="+result);

/*        String finalUrl=url+"?sno="+request.getHeader("sno")+"&cno="+request.getHeader("cno");
        String getUrlParam = restTemplate.getForObject(finalUrl, String.class);*/
       // System.out.println("==>/server/get/url/{one}/{two} return: " + getUrlParam);

        /*ResponseEntity<String> responseX = HTTPClient.sendGetRequest(url, params);
        result = responseX.getBody();*/

        //System.out.println(getUrlParam);
        //返回信息



        //System.out.println(requestBody);
        try{
            response.getWriter().println(result);
        }catch (IOException en){

        }


    }


    /**
     * 退课通信
     * 获取请求方的退课信息，并通过socket转发
     * @param request
     * @param response
     */
    @PostMapping("/dropClass")
    public void dropCourse(HttpServletRequest request, HttpServletResponse response){

        //根据需要提供的业务进行处理
        //发给
        String requestServer=(String) request.getHeader("provider"); //想要请求的服务方
        String url="";
        String result="";
        //为请求所需的url赋值
        switch (requestServer){
            case "A":
                url="http://192.168.10.153:9091/integration/delete";
                break;
            case "B":
                url="http://192.168.10.153:9091/integration/delete";
                break;
            case "C":
                url="http://192.168.10.153:9091/integration/delete";
                break;
        }
       /* System.out.println(request.getHeader("sno"));
        System.out.println();*/

        Map<String,String> params=new HashMap<>();
        params.put("sno",request.getHeader("sno"));
        params.put("cno",request.getHeader("cno"));
        params.put("de",request.getHeader("de"));
        //添加信息学号、课程号、select标识
        ResponseEntity<String> q = HTTPClient.sendPostRequest(url, params);
        result = q.getBody();
        System.out.println("result="+result);

/*        String finalUrl=url+"?sno="+request.getHeader("sno")+"&cno="+request.getHeader("cno");
        String getUrlParam = restTemplate.getForObject(finalUrl, String.class);*/
        // System.out.println("==>/server/get/url/{one}/{two} return: " + getUrlParam);

        /*ResponseEntity<String> responseX = HTTPClient.sendGetRequest(url, params);
        result = responseX.getBody();*/

        //System.out.println(getUrlParam);
        //返回信息



        //System.out.println(requestBody);
        try{
            response.getWriter().println(result);
        }catch (IOException en){

        }
    }

    /**
     * 登分通信
     * 获取请求方的登分信息，并通过socket转发
     * @param request
     * @param response
     */
    @PostMapping("/registerScore")
    public void registerScore(HttpServletRequest request, HttpServletResponse response){

        //根据需要提供的业务进行处理
        //发给
        String requestServer=(String) request.getHeader("provider"); //想要请求的服务方
        String url="";
        String result="";
        //为请求所需的url赋值
        switch (requestServer){
            case "A":
                url="http://192.168.10.153:9091/integration/setGrade";
                break;
            case "B":
                url="http://192.168.10.153:9091/integration/setGrade";
                break;
            case "C":
                url="http://192.168.10.153:9091/integration/setGrade";
                break;
        }
       /* System.out.println(request.getHeader("sno"));
        System.out.println();*/

        Map<String,String> params=new HashMap<>();
        params.put("sno",request.getHeader("sno"));
        params.put("cno",request.getHeader("cno"));
        params.put("de",request.getHeader("de"));
        params.put("grd",request.getHeader("grd"));
        //添加信息学号、课程号、select标识
        ResponseEntity<String> q = HTTPClient.sendPostRequest(url, params);
        result = q.getBody();
        System.out.println("result="+result);

/*        String finalUrl=url+"?sno="+request.getHeader("sno")+"&cno="+request.getHeader("cno");
        String getUrlParam = restTemplate.getForObject(finalUrl, String.class);*/
        // System.out.println("==>/server/get/url/{one}/{two} return: " + getUrlParam);

        /*ResponseEntity<String> responseX = HTTPClient.sendGetRequest(url, params);
        result = responseX.getBody();*/

        //System.out.println(getUrlParam);
        //返回信息



        //System.out.println(requestBody);
        try{
            response.getWriter().println(result);
        }catch (IOException en){

        }
    }






}
