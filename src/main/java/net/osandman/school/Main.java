package net.osandman.school;

import net.osandman.school.dao.EntityDao;
import net.osandman.school.dao.StudentDao;
import net.osandman.school.dao.StudentInfoDao;
import net.osandman.school.dto.*;
import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import net.osandman.school.model.SchoolContext;
import net.osandman.school.model.StudentAvgMark;
import net.osandman.school.entity.Subject;
import net.osandman.school.model.StudentCreator;
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
    static String dateFrom = "2023-02-27"; //максимальный период
    static String dateTo = "2023-05-09";

    public static void main(String[] args) throws IOException, InterruptedException {
        try (SessionFactory sessionFactory = SessionManager.getSessionFactory()) {

            EntityDao<Student> studentDao = new StudentDao(sessionFactory);
            EntityDao<StudentInfo> studentInfoDao = new StudentInfoDao(sessionFactory);

            SchoolContext schoolContext = new SchoolContext("src/main/private/init.properties");

            StudentCreator creator = StudentCreator.builder()
                    .setContext(schoolContext)
                    .createStudents()
                    .build();

            studentDao.addOrUpdateStudents(creator.getStudents().toArray(new Student[0]));
            studentInfoDao.addOrUpdateStudents(creator.getStudentsInfo().toArray(new StudentInfo[0]));

//            Print.printList(creator.getStudents());
//            Print.printList(creator.getStudentsInfo());

//            System.out.println(studentDao.getStudentById(2000001095440L));
//            printAvgMarks(studentDao, "технол");
        }
    }

    private static void printAvgMarks(EntityDao<Student> entityDao, String subjectContains) throws IOException {
        Map<String, String> init = PropertiesProcess.getTokens("src/main/private/tokens.properties");
        for (Map.Entry<String, String> entry : init.entrySet()) {
            Student currentStudent = entityDao.getStudentById(Long.parseLong(entry.getKey()));
            Map<String, String> headers = Map.of("Access-Token", entry.getValue());

            String getAllSubjects = PropertiesProcess.getUrl("subjects",
                    currentStudent.getSchoolId());
            subjects.addAll(ApiRequest.getDataList(getAllSubjects, Subject.class, headers));
            subjects = subjects.stream().filter(el -> el.getName()
                    .toLowerCase().contains(subjectContains)).collect(Collectors.toSet());
            String getSubjectMarks = PropertiesProcess.getUrl("weightedAverageMarks",
                    currentStudent.getGroupId(), dateFrom, dateTo);
            subjectMarks.addAll(ApiRequest.getSubjectMark(getSubjectMarks, headers));
            studentsFromDb.addAll(entityDao.getStudentsListByGroupId(currentStudent.getGroupId()));
        }

        studentsFromDb.sort(Comparator.comparing(Student::getLastName));
        List<StudentAvgMark> marksForStudents = InfoSelect.getStudentAvgMarksList(studentsFromDb,
                subjects, subjectMarks);
        marksForStudents.sort(Comparator.comparing(StudentAvgMark::getAvgMark).reversed());
        System.out.println("Средние оценки за период: с " + LocalDate.parse(dateFrom) + " по " + dateTo);
        Print.printList(marksForStudents);
    }

}
