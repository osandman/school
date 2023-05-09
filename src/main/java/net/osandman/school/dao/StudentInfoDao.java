package net.osandman.school.dao;

import net.osandman.school.entity.Student;
import net.osandman.school.entity.StudentInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentInfoDao implements EntityDao<StudentInfo> {
    private final SessionFactory sessionFactory;

    public StudentInfoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrUpdateStudents(StudentInfo... students) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (StudentInfo student : students) {
            session.saveOrUpdate(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public StudentInfo getStudentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StudentInfo student = session.get(StudentInfo.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<StudentInfo> getStudentsListByGroupId(long groupId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<StudentInfo> students = session.createQuery("from StudentInfo "
        ).getResultList();
        session.getTransaction().commit();
        return students;

    }

    @Override
    public void removeStudentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StudentInfo student = session.get(StudentInfo.class, id);
        if (student != null) {
            session.delete(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public void updateStudents(StudentInfo... students) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (StudentInfo student : students) {
            session.update(student);
        }
        session.getTransaction().commit();
    }
}
