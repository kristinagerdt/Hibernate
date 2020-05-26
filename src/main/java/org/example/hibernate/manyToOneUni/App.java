package org.example.hibernate.manyToOneUni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(CourseReview.class)
                        .buildSessionFactory()) {
//            create(sessionFactory);
            readCourseFromReview(sessionFactory);
//            update(sessionFactory);
//            delete(sessionFactory);
        }
    }

    private static void create(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Course course = new Course("Java++");
        CourseReview courseReview1 = new CourseReview("Good!!");
        courseReview1.setCourse(course);
        session.save(courseReview1);

        CourseReview courseReview2 = new CourseReview("Perfect");
        courseReview2.setCourse(course);
        session.save(courseReview2);

        session.getTransaction().commit();
    }

    private static void readCourseFromReview(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int courseReviewId = 2;
        CourseReview courseReview = session.get(CourseReview.class, courseReviewId);
        System.out.println(courseReview);
        System.out.println(courseReview.getCourse());

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int courseReviewId = 2;
        CourseReview courseReview = session.get(CourseReview.class, courseReviewId);
        courseReview.setComment("Updated comment");
        System.out.println(courseReview);

        session.getTransaction().commit();
    }

    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int courseReviewId = 3;
        CourseReview courseReview = session.get(CourseReview.class, courseReviewId);
        session.delete(courseReview);

        session.getTransaction().commit();
    }
}