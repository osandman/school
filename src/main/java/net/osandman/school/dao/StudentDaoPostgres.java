package net.osandman.school.dao;

import net.osandman.school.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDaoPostgres implements StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDaoPostgres(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrUpdateStudents(Student... students) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Student student : students) {
            session.saveOrUpdate(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public Student getStudentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> getStudentsListByGroupId(long groupId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student " +
                "where groupId = " + groupId).getResultList();
        session.getTransaction().commit();
        return students;

    }

    @Override
    public void removeStudentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public void updateStudents(Student... students) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Student student : students) {
            session.update(student);
        }
        session.getTransaction().commit();
    }
}
