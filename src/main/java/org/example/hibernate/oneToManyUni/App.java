package org.example.hibernate.oneToManyUni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Person.class)
                        .addAnnotatedClass(PersonAccount.class)
                        .buildSessionFactory()) {
//            create(sessionFactory);
            read(sessionFactory);
//            update(sessionFactory);
//            delete(sessionFactory);
        }
    }

    private static void create(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = new Person("Max White");
        PersonAccount personAccount = new PersonAccount("DE123456789");
        person.addPersonAccount(personAccount);
        session.save(person);

        session.getTransaction().commit();
    }

    private static void read(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int personId = 1;
        Person person = session.get(Person.class, personId);
        System.out.println(person);
        System.out.println(person.getPersonAccounts());

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int personId = 1;
        Person person = session.get(Person.class, personId);
        person.setName("MAX WHITE");


        int personAccountId = 1;
        PersonAccount personAccount = session.get(PersonAccount.class, personAccountId);
        personAccount.setIban("DEXXXXXXXXX");

        session.getTransaction().commit();
    }

    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int personId = 2;
        Person person = session.get(Person.class, personId);
        if (person != null) session.delete(person);

        session.getTransaction().commit();
    }
}