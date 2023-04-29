package ru.astondev.hibernateProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.astondev.hibernateProject.dao.PersonDAO;
import ru.astondev.hibernateProject.model.Person;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @GetMapping
    public List<Person> getAllPeson() {
        return personDAO.getAllPerson();
    }

    @GetMapping(value = "/all/{flag}")
    public List<Person> getAll(@PathVariable(required = false) Boolean flag) {
        if (flag==null){
            flag=false;
        }
        return personDAO.getAll(flag);
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Long id) {
        return personDAO.getById(id);
    }

    @PostMapping("/new")
    public Person newPerson(@RequestBody Person person) {
        return personDAO.createPerson(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personDAO.deleteByIdPerson(id);

    }
}
