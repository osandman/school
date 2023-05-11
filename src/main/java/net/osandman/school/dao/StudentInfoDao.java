package net.osandman.school.dao;

import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentInfoDao implements EntityDao<StudentInfo> {
    @Override
    public void add(StudentInfo... entities) {

    }

    @Override
    public StudentInfo getById(long id) {
        return null;
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public void update(StudentInfo... entities) {

    }
}
