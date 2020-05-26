package org.example.hibernate.manyToOneBi;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    //cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ClientAddress> clientAddresses;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClientAddress> getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(List<ClientAddress> clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

    public void addClientAddress(ClientAddress clientAddress) {
        if (clientAddresses == null) clientAddresses = new ArrayList<>();
        clientAddresses.add(clientAddress);
        clientAddress.setClient(this);
    }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name=" + name + ", " + clientAddresses + '}';
    }
}