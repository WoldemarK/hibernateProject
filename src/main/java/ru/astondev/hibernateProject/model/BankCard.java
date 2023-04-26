package ru.astondev.hibernateProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@RequiredArgsConstructor
public class BankCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", name='" + name;
    }
}
