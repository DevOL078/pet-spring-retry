package ru.oleynik.pet.spring.retry.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.oleynik.pet.spring.retry.model.Card;
import ru.oleynik.pet.spring.retry.service.CardService;

@Component
@RequiredArgsConstructor
public class CardGetUseCase {

    private final CardService service;

    public Card exec(int id) {
        return service.get(id);
    }

}
