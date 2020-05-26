package org.example.hibernate.oneToOneUni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
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

        InstructorDetail instructorDetail = new InstructorDetail("swimming");
        Instructor instructorAnn = new Instructor("Ann White");
        instructorAnn.setInstructorDetail(instructorDetail);
        session.save(instructorAnn);

        instructorDetail = new InstructorDetail("coding");
        Instructor instructorAlex = new Instructor("Alex Brown");
        instructorAlex.setInstructorDetail(instructorDetail);
        session.save(instructorAlex);

        instructorDetail = new InstructorDetail("dancing");
        Instructor instructorMax = new Instructor("Max Black");
        instructorMax.setInstructorDetail(instructorDetail);
        session.save(instructorMax);

        session.getTransaction().commit();
    }

    private static void read(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int instructorId = 1;
        Instructor instructor = session.get(Instructor.class, instructorId);
        System.out.println(instructor);

        System.out.println(instructor.getInstructorDetail());

        int instructorDetailId = instructor.getInstructorDetail().getId();
        InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
        System.out.println(instructorDetail);

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int instructorId = 1;
        Instructor instructorFirst = session.get(Instructor.class, instructorId);
        instructorFirst.setName("ANN WHITE");
        System.out.println(instructorFirst);

        session
                .createQuery("update Instructor set name='ALEX BROWN' where id=2")
                .executeUpdate();
        instructorId = 2;
        Instructor instructorSecond = session.get(Instructor.class, instructorId);
        System.out.println(instructorSecond);

        session.getTransaction().commit();
    }

    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int instructorId = 1;
        Instructor instructor = session.get(Instructor.class, instructorId);
        if (instructor != null) session.delete(instructor);

        session
                .createQuery("delete from Instructor where id=2")
                .executeUpdate();
        session
                .createQuery("delete from InstructorDetail where id=2")
                .executeUpdate();

        session.getTransaction().commit();
    }
}