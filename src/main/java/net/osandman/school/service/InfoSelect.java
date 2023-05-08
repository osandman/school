package net.osandman.school.service;

import net.osandman.school.entity.Subject;
import net.osandman.school.dto.SubjectMark;
import net.osandman.school.entity.Student;
import net.osandman.school.model.StudentAvgMark;

import java.util.*;

public class InfoSelect {

    public static List<StudentAvgMark> getStudentAvgMarksList(List<Student> students,
                                                              Set<Subject> subjects, List<SubjectMark> subjectMarks) {
        List<StudentAvgMark> marksForStudents = new ArrayList<>();
        StudentAvgMark studentAvgMark;
        Map<String, Double> avgMarks;
        for (Student currentStudent : students) {
            avgMarks = new HashMap<>();
            studentAvgMark = new StudentAvgMark(currentStudent, avgMarks);
            for (SubjectMark subjectsMark : subjectMarks) {
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
