package ru.oleynik.pet.spring.retry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oleynik.pet.spring.retry.card.CardCreateUseCase;
import ru.oleynik.pet.spring.retry.card.CardGetUseCase;
import ru.oleynik.pet.spring.retry.model.Card;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardGetUseCase getUseCase;
    private final CardCreateUseCase createUseCase;

    @GetMapping("/{id}")
    public Card get(@PathVariable("id") int id) {
        return getUseCase.exec(id);
    }

    @PostMapping
    public int create(@RequestBody Card card) {
        return createUseCase.exec(card);
    }

}
