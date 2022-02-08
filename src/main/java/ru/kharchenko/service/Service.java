package ru.kharchenko.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.kharchenko.dao.PersonDAO;

@Component
public class Service {

    private PersonDAO personDAO;

    public Service(){
        Configuration configuration= new Configuration();
        configuration.configure();
        SessionFactory factory=configuration.buildSessionFactory();
        personDAO = new PersonDAO(factory);
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
}
