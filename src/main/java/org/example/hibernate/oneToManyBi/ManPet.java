package org.example.hibernate.oneToManyBi;

import javax.persistence.*;

@Entity
@Table(name = "man_pet")
public class ManPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "kind")
    private String kind;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    //{CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "man_id")
    private Man man;

    public ManPet() {
    }

    public ManPet(String kind, String name) {
        this.kind = kind;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    @Override
    public String toString() {
        return "ManPet{id=" + id + ", kind=" + kind + ", name=" + name + '}';
    }
}