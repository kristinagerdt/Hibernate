package org.example.hibernate.manyToOneBi;

import javax.persistence.*;

@Entity
@Table(name = "client_address")
public class ClientAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @ManyToOne(cascade = CascadeType.ALL)
    //{CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;

    public ClientAddress() {
    }

    public ClientAddress(String houseNumber, String street) {
        this.houseNumber = houseNumber;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientAddress{id=" + id + ", houseNumber=" + houseNumber + ", street=" + street + '}';
    }
}