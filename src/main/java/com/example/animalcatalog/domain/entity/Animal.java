package com.example.animalcatalog.domain.entity;

import com.example.animalcatalog.validation.AnimalType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Internal data structure(Animal entity)
 * @author ilyin
 * @since 08.07.2022
 */
@Getter
@Setter
@Entity
@Table(name = "animal")
public class Animal extends BaseEntity{

    String animalType;
    String name;
    String gender;
    String dayOfBirth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
