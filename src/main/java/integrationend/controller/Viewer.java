package integrationend.controller;

import integrationend.Utilities.Response;
import integrationend.server.Calculator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类用于和集成端前端交互
 */
@RestController
@CrossOrigin
@RequestMapping("/viewer")
public class Viewer {

    @Autowired
    Calculator calculator;

    @GetMapping("/start")
    public Response start() {
        return Response.buildSuccess("00000", "连接成功");
    }

    @GetMapping("/getStudentById")
    public Response getStudentInfoById(@RequestParam(value = "sno")String sno) {
        JSONObject jsonObject = calculator.getStudentInfoById(sno);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }
    @GetMapping("/getCourseById")
    public Response getCourseInfoById(@RequestParam(value = "cno")String cno) {
        JSONObject jsonObject = calculator.getCourseInfoById(cno);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }
    @GetMapping("/getAllStudent")
    public Response getAllStudentInfo() {
        JSONArray jsonArray = calculator.getAllStudentInfo();
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonArray);
    }

    @GetMapping("/getAllCourse")
    public Response getAllCourseInfo() {
        JSONArray jsonArray = calculator.getAllCourseInfo();
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonArray);
    }

    /**
     * 根据学生编号
     * 获取选修的课程信息 & 成绩
     *
     * @param sno 学号
     * @return
     */
    @GetMapping("/getCourseInfoByStudent")
    public Response getChoiceAndCourseInfoByStudentId(@RequestParam(value = "sno")String sno){
        JSONArray jsonArray = calculator.getChoiceAndCourseInfoByStudentId(sno);
        if (jsonArray == null) {
            return Response.buildSuccess("00000", "操作成功，但是结果集为空");
        }
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonArray);
    }

    @GetMapping("/getGPA")
    public Response getGPA(@RequestParam(value = "sno")String sno){
        JSONObject jsonObject = calculator.getGPAByStudentId(sno);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }

    @GetMapping("/getABCStudentOfCourse")
    public Response getABCStudentNumAndPercentOfOneCourse(@RequestParam(value = "cno")String cno){
        JSONObject jsonObject = calculator.getABCStudentNumAndPercentOfOneCourse(cno);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }

    @GetMapping("/getCourseMath")
    public Response getCourseMathById(@RequestParam(value = "cno")String cno){
        JSONObject jsonObject = calculator.getCourseMathById(cno);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }

    @GetMapping("/getChoiceInfoByCourse")
    public Response getChoiceAndStudentInfoByCourseId(@RequestParam(value = "cno")String cno){
        JSONArray jsonArray = calculator.getChoiceAndStudentInfoByCourseId(cno);
        if (jsonArray == null) {
            return Response.buildSuccess("00000", "操作成功，但是结果集为空");
        }
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonArray);
    }

    @GetMapping("/getMFPercentOfOne")
    public Response getMenWomenPercentOfOne(@RequestParam(value = "ABC")String ABC){
        JSONObject object = calculator.getMenWomenPercentOfOne(ABC);
        if (object.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + object.get("error") + "）");
        }
        return Response.buildSuccess(object);
    }

    @GetMapping("/getMFPercentOfAll")
    public Response getMenWomenPercentOfAll(){
        JSONObject object = calculator.getMenWomenPercentOfAll();
        if (object.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + object.get("error") + "）");
        }
        return Response.buildSuccess(object);
    }

    @GetMapping("/getAllTeacher")
    public Response getAllTeacher(){
        JSONArray jsonArray = calculator.getAllTeachers();

        if (jsonArray == null) {
            return Response.buildSuccess("00000", "操作成功，但是结果集为空");
        }
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonArray);
    }

    @GetMapping("/getCnoByTeacher")
    public Response getCnoByTeacher(@RequestParam(value = "teacherName")String teacherName){
        JSONObject jsonObject = calculator.getCnoByTeacher(teacherName);

        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }

    @GetMapping("/getAllLocation")
    public Response getAllLocation(){
        JSONObject jsonObject = calculator.getAllLocation();

        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }
    @GetMapping("/getCnoByLocation")
    public Response getCnoByLocation(@RequestParam(value = "location")String location){
        JSONObject jsonObject = calculator.getCnoByLocation(location);

        if (jsonObject.get("error") != null) {
            return Response.buildFailed("11111", "操作失败（lwl备注：" + jsonObject.get("error") + "）");
        }
        return Response.buildSuccess(jsonObject);
    }

}
