package ru.oleynik.pet.spring.retry.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder(setterPrefix = "with")
public class Card {

    private int id;
    private String message;
    private Instant createdAt;

}
