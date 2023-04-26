package ru.astondev.hibernateProject.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.astondev.hibernateProject.exception.General;
import ru.astondev.hibernateProject.model.Person;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonDAO {
    private final SessionFactory sessionFactory;
    @Transactional(readOnly = true)
    public List<Person> getAllPerson() {
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("SELECT p FROM Person p", Person.class)
               .getResultList();
    }
    @Transactional(readOnly = true)
    public Person getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<Person> optional = Optional.ofNullable(session.get(Person.class, id));
        return optional.orElseThrow(()->
                new General(String.format("Запрашиваемого ID %d не существует", id)));
    }

    @Transactional
    public Person createPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
        return person;
    }
    @Transactional
    public void deleteByIdPerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
