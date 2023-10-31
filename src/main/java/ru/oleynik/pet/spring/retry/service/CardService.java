package ru.oleynik.pet.spring.retry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oleynik.pet.spring.retry.dao.CardRepository;
import ru.oleynik.pet.spring.retry.model.Card;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    public Card get(int id) {
        return repository.get(id);
    }

    @Transactional
    public int create(Card card) {
        return repository.create(card);
    }

}
