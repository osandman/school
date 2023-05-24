package net.osandman.school;

import net.osandman.school.dao.EntityDao;
import net.osandman.school.dao.StudentDao;
import net.osandman.school.dao.TeacherDao;
import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import net.osandman.school.entity.Teacher;
import net.osandman.school.model.*;

import net.osandman.school.service.AvgMarks;
import net.osandman.school.util.*;
import org.hibernate.SessionFactory;

import java.io.IOException;


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

//            studentDao.add(creator.getStudents().toArray(new Student[0]));
//            Print.printList(creator.getStudents());


//            testDB(studentDao, studentInfoDao);


//            System.out.println(studentDao.getStudentById(2000001095440L));

            //    static String dateFrom = "2023-02-27"; //3-й триместр
            String dateFrom = "2023-02-27"; //максимальный период
            String dateTo = "2023-05-24";
            AvgMarks.printAvgMarks(studentDao, schoolContext, "", dateFrom, dateTo);
            System.out.println("done!");
        }
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
