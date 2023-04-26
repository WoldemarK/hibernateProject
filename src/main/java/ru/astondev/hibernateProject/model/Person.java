package ru.astondev.hibernateProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "person")
public class Person  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть минимум 2 символа максимум 50")
    private String name;
    @Column(name = "age")
    @Min(value = 18, message = "Возраст должен быть больше 18")
    private int age;
    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<BankCard> bankCards;
    public void addBankCard(BankCard bankCard) {
        if (this.bankCards == null)
            this.bankCards = new ArrayList<>();
        this.bankCards.add(bankCard);
        bankCard.setPerson(this);

    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
