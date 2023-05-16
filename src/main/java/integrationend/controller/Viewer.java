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


//    @GetMapping("/findAllCourse")
//    public Response findAllCourse(){
//        List<Course> courses = courseService.findAllCourse();
//        if(courses==null){
//            return Response.buildFailure();
//        }else{
//            return Response.buildSuccess(courses);
//        }
//    }
//
//    @GetMapping("/getCourseByCno")
//    public Response getCourseByCno(@RequestParam(value = "cno")String Cno){
//        Course course = courseService.getCourseByCno(Cno);
//        if(course==null){
//            return Response.buildFailure();
//        }else{
//            return Response.buildSuccess(course);
//        }
//    }
//
//    @PostMapping("/add")
//    public Response addCourse(@RequestBody Course course){
//        if(course!=null&& courseService.addCourse(course.getCno(),course.getCnm(),course.getCtm(),course.getCpt(),course.getTec(),course.getPla(),course.getShare())){
//            return Response.buildSuccess();
//        }else{
//            return Response.buildFailure();
//        }
//    }
//
//    @PostMapping("/update")
//    public Response updateCourse(@RequestBody Course course){
//        if(course!=null&& courseService.updateCourse(course.getCno(),course.getCnm(),course.getCtm(),course.getCpt(),course.getTec(),course.getPla(),course.getShare())){
//            return Response.buildSuccess();
//        }else{
//            return Response.buildFailure();
//        }
//    }
}
