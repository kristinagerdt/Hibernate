package org.example.hibernate.oneToOneBi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Teacher.class)
                        .addAnnotatedClass(TeacherDetail.class)
                        .buildSessionFactory()) {
//            createTeacher(sessionFactory);
//            createTeacherDetail(sessionFactory);
            read(sessionFactory);
//            update(sessionFactory);
//            delete(sessionFactory);
//            deleteOnlyTeacherDetail(sessionFactory);
        }
    }

    private static void createTeacher(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        TeacherDetail teacherDetail = new TeacherDetail("ann_teacher@gmail.com");
        Teacher teacher = new Teacher("Ann White");
        teacher.setTeacherDetail(teacherDetail);

        session.save(teacher);
        session.getTransaction().commit();
    }

    private static void createTeacherDetail(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Teacher teacher = new Teacher("Max Smith");
        TeacherDetail teacherDetail = new TeacherDetail("max_teacher@gmail.com");

        teacherDetail.setTeacher(teacher);
        teacher.setTeacherDetail(teacherDetail);

        session.save(teacherDetail);
        session.getTransaction().commit();
    }

    private static void read(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int teacherId = 1;
        Teacher teacher = session.get(Teacher.class, teacherId);
        System.out.println(teacher.getName());

        System.out.println(teacher.getTeacherDetail());

        int teacherDetailId = teacher.getTeacherDetail().getId();
        TeacherDetail teacherDetail = session.get(TeacherDetail.class, teacherDetailId);
        System.out.println(teacherDetail);

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int teacherId = 1;
        Teacher teacherFirst = session.get(Teacher.class, teacherId);
        teacherFirst.setName("ANN WHITE");
        System.out.println(teacherFirst);

        session
                .createQuery("update Teacher set name='MAX SMITH' where id=2")
                .executeUpdate();
        teacherId = 2;
        Teacher teacherSecond = session.get(Teacher.class, teacherId);
        System.out.println(teacherSecond);

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = CascadeType.ALL or cascade = CascadeType.REMOVE
    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int teacherId = 3;
        Teacher teacher = session.get(Teacher.class, teacherId);
        if (teacher != null) session.delete(teacher);

        session
                .createQuery("delete from Teacher where id=4")
                .executeUpdate();
        session
                .createQuery("delete from TeacherDetail where id=4")
                .executeUpdate();

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    private static void deleteOnlyTeacherDetail(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int teacherDetailId = 5;
        TeacherDetail teacherDetail = session.get(TeacherDetail.class, teacherDetailId);
        System.out.println(teacherDetail.getTeacher());
        teacherDetail.getTeacher().setTeacherDetail(null);
        session.delete(teacherDetail);

        session.getTransaction().commit();
    }
}