package net.osandman.school;

import net.osandman.school.dao.StudentDao;
import net.osandman.school.dao.StudentDaoPostgres;
import net.osandman.school.dto.*;
import net.osandman.school.entity.Student;
import net.osandman.school.model.StudentAvgMark;
import net.osandman.school.entity.Subject;
import net.osandman.school.service.*;
import net.osandman.school.util.Print;
import net.osandman.school.util.PropertiesProcess;
import net.osandman.school.util.SessionManager;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static Set<Subject> subjects = new HashSet<>();
    static List<SubjectMark> subjectMarks = new ArrayList<>();
    static List<Student> studentsFromDb = new ArrayList<>();

    //    static String dateFrom = "2023-02-27"; //3-й триместр
    static String dateFrom = "2022-10-10"; //максимальный период
    static String dateTo = "2023-05-05";

    public static void main(String[] args) throws IOException, InterruptedException {
        try (SessionFactory sessionFactory = SessionManager.getSessionFactory()) {

            StudentDao studentDao = new StudentDaoPostgres(sessionFactory);

//            SchoolContext schoolContext = new SchoolContext("src/main/private/init.properties");
//
//            StudentCreator creator = StudentCreator.builder()
//                    .setContext(schoolContext)
//                    .createStudents()
//                    .build();
//
//            Print.printList(creator.getStudents());
//            Print.printList(creator.getStudentsInfo());

            System.out.println(studentDao.getStudentById(2000001095440L));
            printAvgMarks(studentDao, "");
        }
    }

    private static void printAvgMarks(StudentDao studentDao, String subjectContains) throws IOException {
        Map<String, String> init = PropertiesProcess.getTokens("src/main/private/tokens.properties");
        for (Map.Entry<String, String> entry : init.entrySet()) {
            Student currentStudent = studentDao.getStudentById(Long.parseLong(entry.getKey()));
            Map<String, String> headers = Map.of("Access-Token", entry.getValue());

            String getAllSubjects = PropertiesProcess.getUrl("subjects",
                    currentStudent.getSchoolId());
            subjects.addAll(ApiRequest.getDataList(getAllSubjects, Subject.class, headers));
            subjects = subjects.stream().filter(el -> el.getName()
                    .toLowerCase().contains(subjectContains)).collect(Collectors.toSet());
            String getSubjectMarks = PropertiesProcess.getUrl("weightedAverageMarks",
                    currentStudent.getGroupId(), dateFrom, dateTo);
            subjectMarks.addAll(ApiRequest.getSubjectMark(getSubjectMarks, headers));
            studentsFromDb.addAll(studentDao.getStudentsListByGroupId(currentStudent.getGroupId()));
        }

        studentsFromDb.sort(Comparator.comparing(Student::getLastName));
        List<StudentAvgMark> marksForStudents = InfoSelect.getStudentAvgMarksList(studentsFromDb,
                subjects, subjectMarks);
        marksForStudents.sort(Comparator.comparing(StudentAvgMark::getAvgMark).reversed());
        System.out.println("Средние оценки за период: с " + LocalDate.parse(dateFrom) + " по " + dateTo);
        Print.printList(marksForStudents);
    }

}
