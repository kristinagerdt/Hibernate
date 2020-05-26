package org.example.hibernate.oneToManyUni;

import javax.persistence.*;

@Entity
@Table(name = "person_account")
public class PersonAccount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "iban")
    private String iban;

    public PersonAccount() {
    }

    public PersonAccount(String iban) {
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "PersonAccount{id=" + id + ", iban=" + iban + '}';
    }
}