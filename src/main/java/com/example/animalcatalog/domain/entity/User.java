package com.example.animalcatalog.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.EnumType.STRING;
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
    private String userName;
    private String password;

    private boolean accountNonLocked = true;
    private int attemptCount;
    private Date lockTime;

    @Enumerated(STRING)
    private Role role = Role.USER;
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
