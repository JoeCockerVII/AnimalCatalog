package com.example.animalcatalog.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static lombok.AccessLevel.PRIVATE;

/**
 * Internal data structure(User entity)
 * @author ilyin
 * @since 07.07.2022
 */

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String firstName;
    private String lastName;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH}
    )
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setUser(this);
    }

    public void removeOrder(Animal animal) {
        this.animals.remove(animal);
    }
}
