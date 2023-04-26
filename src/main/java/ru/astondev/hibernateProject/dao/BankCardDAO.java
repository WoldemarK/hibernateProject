package ru.astondev.hibernateProject.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.astondev.hibernateProject.exception.General;
import ru.astondev.hibernateProject.model.BankCard;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BankCardDAO {
    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<BankCard> getAllBankCard() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM BankCard p", BankCard.class)
                .getResultList();

    }

    @Transactional(readOnly = true)
    public BankCard getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<BankCard> cardOptional = Optional.ofNullable(session.get(BankCard.class, id));
        return cardOptional.orElseThrow(() ->
                new General(String.format("Запрашиваемого ID %d не существует", id)));
    }

    @Transactional
    public BankCard createBankCard(BankCard bankCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bankCard);
        return bankCard;
    }

    @Transactional
    public void deleteByIdBankCard(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(BankCard.class, id));
    }
}
