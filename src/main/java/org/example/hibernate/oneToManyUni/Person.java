package org.example.hibernate.oneToManyUni;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<PersonAccount> personAccounts;

    public Person() {
    }

    public Person(String name) {
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

    public List<PersonAccount> getPersonAccounts() {
        return personAccounts;
    }

    public void setPersonAccounts(List<PersonAccount> personAccounts) {
        this.personAccounts = personAccounts;
    }

    public void addPersonAccount(PersonAccount personAccount) {
        if (personAccounts == null) personAccounts = new ArrayList<>();
        personAccounts.add(personAccount);
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name=" + name + ", " + personAccounts + '}';
    }
}