package org.example.hibernate.oneToManyBi;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "man")
public class Man {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "man", cascade = CascadeType.ALL)
            //cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ManPet> manPets;

    public Man() {
    }

    public Man(String name) {
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

    public List<ManPet> getManPets() {
        return manPets;
    }

    public void setManPets(List<ManPet> manPets) {
        this.manPets = manPets;
    }

    public void addManPet(ManPet manPet) {
        if (manPets == null) manPets = new ArrayList<>();
        manPets.add(manPet);
        manPet.setMan(this);
    }

    @Override
    public String toString() {
        return "Man{id=" + id + ", name=" + name + ", " + manPets + '}';
    }
}