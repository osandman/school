package net.osandman.school.service;

import net.osandman.school.dao.StudentDao;
import net.osandman.school.entity.Subject;
import net.osandman.school.dto.SubjectMarkDto;
import net.osandman.school.entity.Student;
import net.osandman.school.model.SchoolContext;
import net.osandman.school.model.StudentAvgMark;
import net.osandman.school.model.UserContext;
import net.osandman.school.util.Print;
import net.osandman.school.util.PropertiesProcess;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AvgMarks {
    private static final Set<Subject> subjects = new HashSet<>();
    private static final List<SubjectMarkDto> subjectMarks = new ArrayList<>();
    private static final List<Student> studentsFromDb = new ArrayList<>();


    public static void printAvgMarks(StudentDao entityDao, SchoolContext context,
                                     String subj, String dateFrom, String dateTo) throws IOException {
        getDataFromApi(entityDao, context, subj, dateFrom, dateTo);
        studentsFromDb.sort(Comparator.comparing(Student::getLastName));
        List<StudentAvgMark> marksForStudents = getStudentAvgMarksList(studentsFromDb,
                subjects, subjectMarks);
        marksForStudents.sort(Comparator.comparing(StudentAvgMark::getAvgMark).reversed());
        System.out.println("Средние оценки за период: с " + dateFrom + " по " + dateTo);
        Print.printList(marksForStudents);
    }

    private static void getDataFromApi(StudentDao entityDao, SchoolContext context,
                                       String subj, String dateFrom, String dateTo) throws IOException {
        for (UserContext userContext : context.getUserContexts()) {
            Map<String, String> headers = Map.of("Access-Token", userContext.getToken());
            String getAllSubjects = PropertiesProcess.getUrl("subjects", userContext.getSchoolId());
            subjects.addAll(ApiRequest.getDataList(getAllSubjects, Subject.class, headers)
                    .stream()
                    .filter(el -> el.getName().toLowerCase().contains(subj))
                    .collect(Collectors.toSet()));
            String getSubjectMarks = PropertiesProcess.getUrl("weightedAverageMarks",
                    userContext.getGroupId(), dateFrom, dateTo);
            subjectMarks.addAll(ApiRequest.getSubjectMark(getSubjectMarks, headers));
            studentsFromDb.addAll(entityDao.getStudentsListByGroupId(userContext.getGroupId()));
        }
    }

    public static List<StudentAvgMark> getStudentAvgMarksList(List<Student> students,
                                                              Set<Subject> subjects, List<SubjectMarkDto> subjectMarks) {
        List<StudentAvgMark> marksForStudents = new ArrayList<>();
        StudentAvgMark studentAvgMark;
        Map<String, Double> avgMarks;
        for (Student currentStudent : students) {
            avgMarks = new HashMap<>();
            studentAvgMark = new StudentAvgMark(currentStudent, avgMarks);
            for (SubjectMarkDto subjectsMark : subjectMarks) {
                String subjName = subjects.stream().filter(subj -> subj.getId() == subjectsMark.subjectId())
                        .findAny().orElse(new Subject()).getName();
                if (subjName == null || subjName.equals("")) {
                    continue;
                }
                for (Map.Entry<Long, Double> mark : subjectsMark.marks().entrySet()) {
                    if (mark.getKey() == currentStudent.getPersonId()) {
                        avgMarks.put(subjName, mark.getValue());
                    }
                }
            }
            marksForStudents.add(studentAvgMark);
        }
        return marksForStudents;
    }
}
