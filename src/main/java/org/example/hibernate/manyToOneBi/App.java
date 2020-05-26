package org.example.hibernate.manyToOneBi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Client.class)
                        .addAnnotatedClass(ClientAddress.class)
                        .buildSessionFactory()) {
//            create(sessionFactory);
            read(sessionFactory);
//            update(sessionFactory);
//            delete(sessionFactory);
//            deleteOnlyClientAddress(sessionFactory);
        }
    }

    private static void create(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Client client = new Client("Max Smith");
        ClientAddress clientAddress = new ClientAddress("10", "Broadway");

        // it's possible to add one of two dependencies
        //clientAddress.setClient(client);
        clientAddress.setClient(client);

        // it's necessary to save both(!) object
        session.save(client);
        session.save(clientAddress);

        session.getTransaction().commit();
    }

    private static void read(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int clientId = 1;
        Client client = session.get(Client.class, clientId);
        System.out.println(client);

        System.out.println(client.getClientAddresses());

        int clientAddressId = 1;
        ClientAddress clientAddress = session.get(ClientAddress.class, clientAddressId);
        System.out.println(clientAddress);

        session.getTransaction().commit();
    }

    private static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int clientId = 1;
        Client client = session.get(Client.class, clientId);
        client.setName("MAX SMITH");
        System.out.println(client);

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = CascadeType.ALL or cascade = CascadeType.REMOVE
    private static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int clientId = 2;
        Client client = session.get(Client.class, clientId);
        if (client != null) session.delete(client);

        session
                .createQuery("delete from ClientAddress where id=3")
                .executeUpdate();

        session
                .createQuery("delete from Client where id=4")
                .executeUpdate();

        session.getTransaction().commit();
    }

    // it works, when:
    // cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private static void deleteOnlyClientAddress(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        int clientAddressId = 2;
        ClientAddress clientAddress = session.get(ClientAddress.class, clientAddressId);
        System.out.println(clientAddress.getClient());
        session.delete(clientAddress);

        session.getTransaction().commit();
    }
}