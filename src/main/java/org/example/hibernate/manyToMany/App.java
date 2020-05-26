package org.example.hibernate.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Subject.class)
                        .buildSessionFactory()) {
//            create(sessionFactory);
//            addSubjectsForStudent(sessionFactory);
            getSubjectsFromStudent(sessionFactory);
//            deleteSubject(sessionFactory);
//            deleteStudent(sessionFactory);
        }
    }

    private static void create(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Student studentMax = new Student("Max Smith");
        session.save(studentMax);

        Student studentAnn = new Student("Ann Black");
        session.save(studentAnn);

        Subject subject = new Subject("Mathematics");
        session.save(subject);

        subject.addStudent(studentMax);
        subject.addStudent(studentAnn);

        session.getTransaction().commit();
    }

    private static void addSubjectsForStudent(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Student student = session.get(Student.class, 2);

        Subject chemistry = new Subject("Chemistry");
        session.save(chemistry);

        Subject trigonometry = new Subject("Trigonometry");
        session.save(trigonometry);

        student.addSubject(chemistry);
        student.addSubject(trigonometry);

        session.getTransaction().commit();
    }

    private static void getSubjectsFromStudent(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Student student = session.get(Student.class, 2);
        System.out.println(student);
        System.out.println(student.getSubjects());

        session.getTransaction().commit();
    }

    private static void deleteSubject(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Subject subject = session.get(Subject.class, 2);
        session.delete(subject);

        session.getTransaction().commit();
    }

    private static void deleteStudent(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Student student = session.get(Student.class, 3);
        session.delete(student);

        session.getTransaction().commit();
    }
}