package net.osandman.school;

import net.osandman.school.dao.EntityDao;
import net.osandman.school.dao.StudentDao;
import net.osandman.school.dao.TeacherDao;
import net.osandman.school.dto.TeacherDto;
import net.osandman.school.dto.UserDto;
import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import net.osandman.school.entity.Teacher;
import net.osandman.school.model.*;

import net.osandman.school.service.ApiRequest;
import net.osandman.school.service.AvgMarks;
import net.osandman.school.util.*;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(System.getProperty("java.class.path"));
        try (SessionFactory sessionFactory = SessionManager.getSessionFactory()) {
            StudentDao studentDao = new StudentDao(sessionFactory);
            EntityDao<Teacher> teacherDao = new TeacherDao(sessionFactory);

//            SchemaGenerator.exportCreateQuery(Teacher.class, "create.sql");
            SchoolContext schoolContext = new SchoolContext("src/main/private/init.properties");

//            List<Teacher> teachers = new TeacherCreator(schoolContext).getTeachers();
//            Print.printList(teachers);
//            teacherDao.add(teachers.toArray(new Teacher[0]));


//            StudentCreator creator = StudentCreator.builder()
//                    .setContext(schoolContext)
//                    .createStudents()
//                    .build();
//            Print.printList(creator.getStudents());
//            studentDao.add(creator.getStudents().toArray(new Student[0]));


//            testDB(studentDao, studentInfoDao);


//            System.out.println(studentDao.getStudentById(2000001095440L));

            //    static String dateFrom = "2023-02-27"; //3-й триместр
            String dateFrom = "2023-02-27"; //максимальный период
            String dateTo = "2023-05-24";
//            AvgMarks.printAvgMarks(studentDao, schoolContext, "", dateFrom, dateTo);

//            test();

            System.out.println("done!");
        }
    }

    private static void test() {
        var v = ApiRequest.getData(
                PropertiesProcess.getUrl("userV1", "1000005891735"),
                UserDto.class,
                Map.of("Access-Token", PropertiesProcess
                        .getTokens("src/main/private/init.properties")
                        .get("token.nas")));
        System.out.println(v);
    }

    private static void testDB(StudentDao studentDao, EntityDao<StudentInfo> studentInfoDao) {
        Student student = Student.builder()
                .personId(8)
                .studentInfo(StudentInfo.builder()
                        .shortName("Petya")
                        .personId(8)
                        .build())
                .build();

        studentDao.add(student);
        studentDao.removeById(6);
//        studentInfoDao.removeStudentById(1);
        //            studentInfoDao.removeStudentById(2000001100383L);
//            studentDao.removeStudentById(2000001100383L);

    }


}
