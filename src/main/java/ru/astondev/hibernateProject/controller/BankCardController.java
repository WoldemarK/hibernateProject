package ru.astondev.hibernateProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.astondev.hibernateProject.dao.BankCardDAO;
import ru.astondev.hibernateProject.dao.PersonDAO;
import ru.astondev.hibernateProject.model.BankCard;
import ru.astondev.hibernateProject.model.Person;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankCardController {
    private final BankCardDAO bankCardDAO;

    @GetMapping
    public List<BankCard> getAll() {
        return bankCardDAO.getAllBankCard();
    }

    @GetMapping("/{id}")
    public BankCard getById(@PathVariable("id") Long id) {
        return bankCardDAO.getById(id);
    }

    @PostMapping("/new")
    public BankCard newBankCard(@RequestBody BankCard bankCard) {
        return bankCardDAO.createBankCard(bankCard);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bankCardDAO.deleteByIdBankCard(id);

    }
}
