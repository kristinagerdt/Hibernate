package org.example.hibernate.oneToManyBi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Man.class)
                        .addAnnotatedClass(ManPet.class)
                        .buildSessionFactory()) {
//            create(sessionFactory);
            read(sessionFactory);
//            update(sessionFactory);
//            delete(sessionFactory);
//            deleteOnlyManPet(sessionFactory);
        }
    }

    private static void create(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Man man = new Man("Max Smith");
        ManPet manPet = new ManPet("cat", "Betty");

        // it's possible to add one of two dependencies
        // man.addManPet(manPet);
        manPet.setMan(man);

        // it's necessary to save both(!) object
        session.save(man);
        session.save(manPet);

        session.getTransaction().commit();
    }

    private static void read(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int manId = 1;
        Man man = session.get(Man.class, manId);
        System.out.println(man);

        System.out.println(man.getManPets());

        int manPetId = 1;
        ManPet manPet = session.get(ManPet.class, manPetId);
        System.out.println(manPet);

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int manId = 1;
        Man man = session.get(Man.class, manId);
        man.setName("MAX SMITH");
        System.out.println(man);

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = CascadeType.ALL or cascade = CascadeType.REMOVE
    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int manId = 3;
        Man man = session.get(Man.class, manId);
        if (man != null) session.delete(man);

        session
                .createQuery("delete from ManPet where id=4")
                .executeUpdate();

        session
                .createQuery("delete from Man where id=4")
                .executeUpdate();

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private static void deleteOnlyManPet(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int manPetId = 5;
        ManPet manPet = session.get(ManPet.class, manPetId);
        System.out.println(manPet.getMan());
        session.delete(manPet);

        session.getTransaction().commit();
    }
}