package net.osandman.school.dao;

import net.osandman.school.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDao implements EntityDao<Student> {
    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Student... entities) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Student student : entities) {
            session.persist(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public Student getById(long personId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, personId);
        session.getTransaction().commit();
        return student;
    }

    public List<Student> getStudentsListByGroupId(long groupId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student " +
                "where groupId = " + groupId).getResultList();
        session.getTransaction().commit();
        return students;

    }

    @Override
    public void removeById(long personId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, personId);
        if (student != null) {
            session.remove(student);
        }
        session.getTransaction().commit();
    }

    @Override
    public void update(Student... entities) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Student student : entities) {
            session.merge(student);
        }
        session.getTransaction().commit();
    }
}
