package ru.oleynik.pet.spring.retry.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import ru.oleynik.pet.spring.retry.model.Card;
import ru.oleynik.pet.spring.retry.service.CardService;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardCreateUseCase {

    private final CardService service;

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public int exec(Card card) {
        log.info("TRY EXEC: message={}", card.getMessage());
        return service.create(card);
    }

    @Recover
    public int recoverCreate(Exception e, Card card) {
        log.error("FROM RECOVER: message={}, ex={}", card.getMessage(), e.getMessage());
        throw new IllegalStateException("card creating error", e);
    }

}
