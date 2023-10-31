package ru.oleynik.pet.spring.retry.dao;

import ru.oleynik.pet.spring.retry.model.Card;

public interface CardRepository {

    int create(Card card);

    Card get(int id);

}
