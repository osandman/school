package net.osandman.school.dao;

import net.osandman.school.entity.Student;

import java.util.List;

public interface EntityDao <T> {

    void addOrUpdateStudents(T... students);

    T getStudentById(long id);
    List<T> getStudentsListByGroupId(long groupId);

    void removeStudentById(long id);

    void updateStudents(T... students);

}
