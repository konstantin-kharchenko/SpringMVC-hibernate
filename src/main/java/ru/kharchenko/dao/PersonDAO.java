package ru.kharchenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.kharchenko.models.Person;

import java.util.List;

public class PersonDAO {

    SessionFactory sessionFactory;

    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(final Person engine) {
        try (final Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.save(engine);

            session.getTransaction().commit();
        }
    }

    public Person read(int id) {
        try (final Session session = sessionFactory.openSession()) {

            Person result = (Person) session.get(Person.class,id);

            return result;
        }
    }

    public void update(final Person engine) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.update(engine);

            session.getTransaction().commit();
        }
    }
    public void delete(Person engine) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.delete(engine);

            session.getTransaction().commit();
        }
    }

    public List<Person> readAll() {
        try (final Session session = sessionFactory.openSession()) {
            List developers = session.createQuery("FROM Person").list();
            return developers;
        }
    }
}
