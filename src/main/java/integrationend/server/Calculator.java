package integrationend.server;

import java.util.ArrayList;
import java.util.List;

import integrationend.Utilities.MathAlgorithm;
import integrationend.dao.Choice;
import integrationend.dao.Course;
import integrationend.dao.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这个类对数据层传回的对象做处理，计算平均值等高阶结果
 */

/**
 * 学生编号→选修的课程编号、课程名称、成绩、总选修的学分绩、选修的各院系课程学分
 * <p>
 * 课程编号→选修的每个院系学生数量、所有选修学生的课程成绩、课程均分
 */
@Service
public class Calculator {

    @Autowired
    ListObjectResolver listObjectResolver;


    /**
     * TODO
     */
    public List<Student> getStudentList(String ABC) {
//        return listObjectResolver.getAllStudents(ABC).getStudents();

        /******/
        List<Student> studentList = new ArrayList<>();
        if (ABC.equals("A")) {
            Student student = new Student();
            student.setStudent("201905075001", "古璐", "女", "A");
            Student student2 = new Student();
            student2.setStudent("201905075003", "严秋伶", "女", "A");

            studentList.add(student);
            studentList.add(student2);
        } else if (ABC.equals("B")) {
            Student student1 = new Student();
            student1.setStudent("201905075002", "范坤", "男", "B");

            studentList.add(student1);
        } else if (ABC.equals("C")) {
            Student student3 = new Student();
            student3.setStudent("201905075004", "阮龙", "男", "B");

            studentList.add(student3);
        }
        return studentList;
        /*****/
    }

    public List<Course> getCourseList(String ABC) {
//        return listObjectResolver.getAllCourses(ABC).getCourses();


        /*****/
        List<Course> courseList = new ArrayList<>();
        if (ABC.equals("A")) {
            Course course = new Course();
            course.setCourse("05075001", "BIM概论与建模", 2, "陈艳云", "北教302");
            Course course1 = new Course();
            course1.setCourse("05075078", "国家公务员录用实务", 2, "陈艳云", "北教302");
            Course course2 = new Course();
            course2.setCourse("05075003", "会计信息系统", 3, "郑细端", "南教302");
            Course course3 = new Course();
            course3.setCourse("05075010", "工程造价信息管理", 2, "傅再育", "南教513");
            courseList.add(course);
            courseList.add(course1);
            courseList.add(course2);
            courseList.add(course3);
        } else if (ABC.equals("B")) {
            Course course4 = new Course();
            course4.setCourse("05075089", "计算机审计", 4, "郑怀瑾", "南教505");
            courseList.add(course4);
        } else if (ABC.equals("C")) {
            Course course5 = new Course();
            course5.setCourse("05075101", "数据库系统原理与操作", 2, "张铮燕", "北教102");
            courseList.add(course5);
        }

        return courseList;
        /*****/

    }

    public List<Choice> getChoiceList(String ABC) {
//        return listObjectResolver.getAllChoices(ABC).getChoices();

        /*****/
        List<Choice> choiceList = new ArrayList<>();
        if (ABC.equals("A")) {
            Choice choice = new Choice();
            choice.setChoice("201905075001", "A", "05075001", "A", 90.0);
            Choice choice1 = new Choice();
            choice1.setChoice("201905075001", "A", "05075078", "A", 80.0);
            Choice choice2 = new Choice();
            choice2.setChoice("201905075001", "A", "05075003", "A", 70.0);

            choiceList.add(choice);
            choiceList.add(choice1);
            choiceList.add(choice2);
        } else if (ABC.equals("B")) {
            Choice choice3 = new Choice();
            choice3.setChoice("201905075002", "B", "05075001", "A", -1.0);
            Choice choice4 = new Choice();
            choice4.setChoice("201905075002", "B", "05075078", "A", -1.0);

            choiceList.add(choice3);
            choiceList.add(choice4);
        } else {

        }
        return choiceList;
        /*****/
    }


    public List<Student> getAllStudent() {
        List<Student> studentList = getStudentList("A");
        List<Student> studentList1 = getStudentList("B");
        List<Student> studentList2 = getStudentList("C");
        studentList.addAll(studentList1);
        studentList.addAll(studentList2);

        return studentList;
    }


    public List<Course> getAllCourse() {
        List<Course> courseList = getCourseList("A");
        List<Course> courseList1 = getCourseList("B");
        List<Course> courseList2 = getCourseList("C");
        courseList.addAll(courseList1);
        courseList.addAll(courseList2);

        return courseList;
    }

    public List<Choice> getAllChoice() {
        List<Choice> choiceList = getChoiceList("A");
        List<Choice> choiceList1 = getChoiceList("B");
        List<Choice> choiceList2 = getChoiceList("C");
        choiceList.addAll(choiceList1);
        choiceList.addAll(choiceList2);

        return choiceList;
    }

    public Student getStudentById(String id, List<Student> students) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public Course getCourseById(String id, List<Course> courses) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Choice> getChoiceGroupByCourse(String cid, List<Choice> choices) {
        List<Choice> resChoices = new ArrayList<>();

        for (Choice ch : choices) {
            if (ch.getCno().equals(cid)) {
                resChoices.add(ch);
            }
        }
        return resChoices;
    }

    public List<Choice> getChoiceGroupByStudent(String sno, List<Choice> choices) {
        List<Choice> resChoices = new ArrayList<>();

        for (Choice ch : choices) {
            if (ch.getSno().equals(sno)) {
                resChoices.add(ch);
            }
        }
        return resChoices;
    }

    /*****************************************************************************************/

    public String getStudentABC(String sno, List<Student> studentListA, List<Student> studentListB, List<Student> studentListC) {
        for (Student s : studentListA) {
            if (s.getId().equals(sno)) {
                return "A";
            }
        }
        for (Student s : studentListB) {
            if (s.getId().equals(sno)) {
                return "B";
            }
        }
        for (Student s : studentListC) {
            if (s.getId().equals(sno)) {
                return "C";
            }
        }
        return null;
    }

    public String getCourseABC(String cno, List<Course> courseListA, List<Course> courseListB, List<Course> courseListC) {
        for (Course c : courseListA) {
            if (c.getId().equals(cno)) {
                return "A";
            }
        }
        for (Course c : courseListB) {
            if (c.getId().equals(cno)) {
                return "B";
            }
        }
        for (Course c : courseListC) {
            if (c.getId().equals(cno)) {
                return "C";
            }
        }
        return null;
    }

    public JSONObject getStudentInfoById(String sno) {
        List<Student> allStudent = getAllStudent();

        JSONObject object = new JSONObject();
        if (allStudent.isEmpty()) {
            System.out.println("*【error】*: getAllStudent()");
            object.put("error", "getAllStudent()");
            return object;
        }

        Student s = getStudentById(sno, allStudent);
        if (s == null) {
            System.out.println("*【error】*: 找不到指定学生getStudentById()");
            object.put("error", "找不到指定学生getStudentById()");
            return object;
        }
        object.put("sno", s.getId());
        object.put("sname", s.getName());
        object.put("sex", s.getSex());
        object.put("sde", s.getMajor());

        return object;
    }

    public JSONObject getCourseInfoById(String cno) {
        List<Course> courseListA = getCourseList("A");
        List<Course> courseListB = getCourseList("B");
        List<Course> courseListC = getCourseList("C");

        JSONObject object = new JSONObject();
        if (courseListA.isEmpty()) {
            System.out.println("*【error】*: 找不到A学院课程，getCourseList(\"A\")");
            object.put("error", "找不到A学院课程，getCourseList(\"A\")");
            return object;
        }
        if (courseListB.isEmpty()) {
            System.out.println("*【error】*: 找不到B学院课程，getCourseList(\"B\")");
            object.put("error", "找不到B学院课程，getCourseList(\"B\")");
            return object;
        }
        if (courseListC.isEmpty()) {
            System.out.println("*【error】*: 找不到C学院课程，getCourseList(\"C\")");
            object.put("error", "找不到C学院课程，getCourseList(\"C\")");
            return object;
        }

        String ABC = getCourseABC(cno, courseListA, courseListB, courseListC);
        if (ABC == null) {
            System.out.println("*【error】*: getCourseABC()");
            object.put("error", "getCourseABC()");
            return object;
        }

        Course c = new Course();
        switch (ABC) {
            case "A":
                c = getCourseById(cno, courseListA);
                break;
            case "B":
                c = getCourseById(cno, courseListB);
                break;
            case "C":
                c = getCourseById(cno, courseListC);
                break;
        }

        object.put("cno", c.getId());
        object.put("cname", c.getName());
        object.put("credit", c.getScore());
        object.put("teacher", c.getTeacher());
        object.put("location", c.getLocation());
        object.put("ABC", ABC);

        return object;
    }

    public JSONArray getAllStudentInfo() {
        List<Student> allStudent = getAllStudent();

        JSONArray jsonArray = new JSONArray();
        JSONObject message = new JSONObject();
        if (allStudent.isEmpty()) {
            System.out.println("*【error】*: 找不到所有学生信息getAllStudent()");
            message.put("error", "找不到所有学生信息getAllStudent()");
            jsonArray.add(message);
            return jsonArray;
        }

        for (Student s : allStudent) {
            JSONObject object = new JSONObject();
            object.put("sno", s.getId());
            object.put("sname", s.getName());
            object.put("sex", s.getSex());
            object.put("sde", s.getMajor());
            jsonArray.add(object);
        }
        return jsonArray;
    }

    public JSONArray getAllCourseInfo() {
        List<Course> courseListA = getCourseList("A");
        List<Course> courseListB = getCourseList("B");
        List<Course> courseListC = getCourseList("C");

        JSONArray jsonArray = new JSONArray();
        JSONObject message = new JSONObject();
        if (courseListA.isEmpty()) {
            System.out.println("*【error】*: 找不到A学院课程信息getCourseList(\"A\")");
            message.put("error", "找不到A学院课程信息getCourseList(\"A\")");
            jsonArray.add(message);
            return jsonArray;
        }
        if (courseListB.isEmpty()) {
            System.out.println("*【error】*: 找不到B学院课程信息getCourseList(\"B\")");
            message.put("error", "找不到B学院课程信息getCourseList(\"B\")");
            jsonArray.add(message);
            return jsonArray;
        }
        if (courseListC.isEmpty()) {
            System.out.println("*【error】*: 找不到C学院课程信息getCourseList(\"C\")");
            message.put("error", "找不到C学院课程信息getCourseList(\"C\")");
            jsonArray.add(message);
            return jsonArray;
        }

        for (Course c : courseListA) {
            JSONObject object = new JSONObject();
            object.put("cno", c.getId());
            object.put("cname", c.getName());
            object.put("credit", c.getScore());
            object.put("teacher", c.getTeacher());
            object.put("location", c.getLocation());
            object.put("ABC", "A");
            jsonArray.add(object);
        }
        for (Course c : courseListB) {
            JSONObject object = new JSONObject();
            object.put("cno", c.getId());
            object.put("cname", c.getName());
            object.put("credit", c.getScore());
            object.put("teacher", c.getTeacher());
            object.put("location", c.getLocation());
            object.put("ABC", "B");
            jsonArray.add(object);
        }
        for (Course c : courseListB) {
            JSONObject object = new JSONObject();
            object.put("cno", c.getId());
            object.put("cname", c.getName());
            object.put("credit", c.getScore());
            object.put("teacher", c.getTeacher());
            object.put("location", c.getLocation());
            object.put("ABC", "C");
            jsonArray.add(object);
        }

        return jsonArray;
    }

    /**
     * 根据学生编号
     * 获取选修的课程编号、课程名称、课程学分、成绩
     */
    public JSONArray getChoiceAndCourseInfoByStudentId(String sno) {
        JSONArray jsonArray = new JSONArray();
        List<Choice> allChoices = getAllChoice();
        List<Course> allCourses = getAllCourse();
        JSONObject message = new JSONObject();
        if (allChoices.isEmpty()) {
            System.out.println("*【error】*: getAllChoice()");
            message.put("error", "getAllChoice()");
            jsonArray.add(message);
            return jsonArray;
        }
        if (allCourses.isEmpty()) {
            System.out.println("*【error】*: getAllCourse()");
            message.put("error", "getAllCourse()");
            jsonArray.add(message);
            return jsonArray;
        }

        List<Choice> choices = getChoiceGroupByStudent(sno, allChoices);

        if (choices.isEmpty()) {
            System.out.println("*【message】*: 没有该学生的选课信息 ");
            return null;
        }

        for (Choice ch : choices) {
            Course c = getCourseById(ch.getCno(), allCourses);
            try {
                JSONObject object = new JSONObject();
                object.put("cno", c.getId());
                object.put("cname", c.getName());
                object.put("credit", c.getScore());
                object.put("teacher", c.getTeacher());
                object.put("location", c.getLocation());
                object.put("grade", ch.getGrd());
                jsonArray.add(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    /**
     * 根据学生编号
     * 获取选修的课程编号、课程名称、课程学分、成绩
     */
    public JSONObject getGPAByStudentId(String sno) {
        List<Choice> allChoices = getAllChoice();
        List<Course> allCourses = getAllCourse();
        JSONObject object = new JSONObject();

        if (allChoices.isEmpty()) {
            System.out.println("*【error】*: getAllChoice()");
            object.put("error", "getAllChoice()");
            return object;
        }
        if (allCourses.isEmpty()) {
            System.out.println("*【error】*: getAllCourse()");
            object.put("error", "getAllCourse()");
            return object;
        }

        List<Choice> choices = getChoiceGroupByStudent(sno, allChoices);
        if (choices.isEmpty()) {
            System.out.println("*【message】*: 没有该学生的选课信息 ");
            object.put("gpa", "-1");
            return object;
        }

        int sumScore = 0;
        double sumGrdWithScore = 0.0;

        int count = 0;
        for (Choice ch : choices) {
            if (ch.getGrd() != -1) {
                Course c = getCourseById(ch.getCno(), allCourses);
                sumScore += c.getScore();
                double temp = c.getScore() * ch.getGrd();
                sumGrdWithScore += temp;
                continue;
            }
            count++;
        }

        if (count == choices.size()) {
            System.out.println("*【message】*: 该学生的课均未出成绩 ");
            object.put("gpa", "-2");
            return object;
        }

        double res = sumGrdWithScore / sumScore / 20;

        object.put("gpa", res);
        return object;
    }

    /**
     * 根据课程编号
     * 获取选修的每个院系学生数量、比例
     */
    public JSONObject getABCStudentNumAndPercentOfOneCourse(String cno) {
        List<Choice> allChoices = getAllChoice();
        JSONObject object = new JSONObject();

        if (allChoices.isEmpty()) {
            System.out.println("*【error】*: getAllChoice()");
            object.put("error", "getAllChoice()");
            return object;
        }

        List<Choice> choices = getChoiceGroupByCourse(cno, allChoices);

        int aNum = 0;
        int bNum = 0;
        int cNum = 0;
        int sum = 0;
        if (choices.isEmpty()) {
            System.out.println("*【message】*: 没有人选这门课 ");
            sum = 1;
        } else {
            for (Choice ch : choices) {
                if (ch.getSde().equals("A")) {
                    aNum++;
                } else if (ch.getSde().equals("B")) {
                    bNum++;
                } else if (ch.getSde().equals("C")) {
                    cNum++;
                }
                sum++;
            }
        }
        object.put("ANum", aNum);
        object.put("BNum", bNum);
        object.put("CNum", cNum);
        object.put("APercent", aNum * 1.0 / sum);
        object.put("BPercent", bNum * 1.0 / sum);
        object.put("CPercent", cNum * 1.0 / sum);
        return object;
    }

    /**
     * 根据课程编号
     * 获取所有选修学生的课程成绩
     */
    public JSONArray getChoiceAndStudentInfoByCourseId(String cno) {
        JSONArray jsonArray = new JSONArray();
        List<Choice> allChoices = getAllChoice();
        List<Course> allCourses = getAllCourse();
        List<Student> allStudent = getAllStudent();

        JSONObject message = new JSONObject();
        if (allChoices.isEmpty()) {
            System.out.println("*【error】*: getAllChoice()");
            message.put("error", "getAllChoice()");
            jsonArray.add(message);
            return jsonArray;
        }
        if (allCourses.isEmpty()) {
            System.out.println("*【error】*: getAllCourse()");
            message.put("error", "getAllCourse()");
            jsonArray.add(message);
            return jsonArray;
        }
        if (allStudent.isEmpty()) {
            System.out.println("*【error】*: getAllStudent()");
            message.put("error", "getAllStudent()");
            jsonArray.add(message);
            return jsonArray;
        }

        List<Choice> choices = getChoiceGroupByCourse(cno, allChoices);

        if (choices.isEmpty()) {
            System.out.println("*【message】*: 没有人选这门课 ");
            return null;
        }

        for (Choice ch : choices) {
            Student s = getStudentById(ch.getSno(), allStudent);
            try {
                JSONObject object = new JSONObject();
                object.put("sno", s.getId());
                object.put("sname", s.getName());
                object.put("sex", s.getSex());
                object.put("sde", s.getMajor());
                object.put("grade", ch.getGrd());
                jsonArray.add(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    /**
     * 根据课程编号
     * 获取指定选修课程均分、方差、标准差，中位数，众数
     */
    public JSONObject getCourseMathById(String cno) {
        List<Choice> allChoices = getAllChoice();

        JSONObject object = new JSONObject();
        if (allChoices.isEmpty()) {
            System.out.println("*【error】*: getAllChoice()");
            object.put("error", "getAllChoice()");
            return object;
        }

        List<Choice> choices = getChoiceGroupByCourse(cno, allChoices);

        if (choices.isEmpty()) {
            System.out.println("*【message】*: 没有人选这门课 ");
            object.put("average", "-1");
            return object;
        }

        double[] gradeTemp = new double[choices.size()];
        int count = 0;
        for (Choice ch : choices) {
            if (ch.getGrd() != -1) {
                gradeTemp[count] = ch.getGrd();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("*【message】*: 该课没有出分 ");
            object.put("average", "-2");
            return object;
        }

        double[] grade = new double[count];
        System.arraycopy(gradeTemp, 0, grade, 0, count);
//        for (double d : grade) {
//            System.out.println(d);
//        }
//        System.out.println("***********");
        MathAlgorithm mathAlgorithm = new MathAlgorithm();
        double average = mathAlgorithm.getAverage(grade);
        double variance = mathAlgorithm.getVariance(grade);
        double standardDiviation = mathAlgorithm.getStandardDiviation(grade);
        double medium = mathAlgorithm.getMedium(grade);
        List<Double> mode = mathAlgorithm.getMode(grade);


        object.put("average", average);
        object.put("variance", variance);
        object.put("standardDiviation", standardDiviation);
        object.put("medium", medium);
        object.put("mode", mode);

        return object;
    }
}
