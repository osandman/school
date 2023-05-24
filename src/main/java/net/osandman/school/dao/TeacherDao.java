package net.osandman.school.dao;

import net.osandman.school.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class TeacherDao implements EntityDao<Teacher> {
    private final SessionFactory sessionFactory;

    public TeacherDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Teacher... entities) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Teacher teacher : entities) {
            session.persist(teacher);
        }
        session.getTransaction().commit();
    }

    @Override
    public Teacher getById(long personId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class, personId);
        session.getTransaction().commit();
        return teacher;
    }

    @Override
    public void removeById(long personId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class, personId);
        if (teacher != null) {
            session.remove(teacher);
        }
        session.getTransaction().commit();
    }

    @Override
    public void update(Teacher... entities) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Teacher teacher : entities) {
            session.merge(teacher);
        }
        session.getTransaction().commit();
    }
}
