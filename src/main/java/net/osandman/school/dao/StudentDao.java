package net.osandman.school.dao;

import net.osandman.school.entity.Student;

import java.util.List;

public interface StudentDao {
    void addOrUpdateStudents(Student... students);

    Student getStudentById(long id);
    List<Student> getStudentsListByGroupId(long groupId);

    void removeStudentById(long id);

    void updateStudents(Student... students);

}
