package ru.oleynik.pet.spring.retry.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.oleynik.pet.spring.retry.model.Card;

import java.sql.Types;
import java.time.Instant;
import java.time.ZoneOffset;

@Repository
@RequiredArgsConstructor
public class PostgreCardRepository implements CardRepository {

    private static final String CARD_TABLE = "card";
    private static final String ID_FIELD = "id";
    private static final String MESSAGE_FIELD = "message";
    private static final String CREATED_AT_FIELD = "createdAt";
    private static final String INSERT_SQL = String.format(
            "INSERT INTO %s (message, created_at) VALUES (:%s, :%s)",
            CARD_TABLE,
            MESSAGE_FIELD,
            CREATED_AT_FIELD);
    private static final String SELECT_SQL = String.format(
            "SELECT id as %1$s, message as %2$s, created_at as %3$s from %4$s where id=?",
            ID_FIELD,
            MESSAGE_FIELD,
            CREATED_AT_FIELD,
            CARD_TABLE
    );

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public int create(Card card) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbcTemplate.update(INSERT_SQL, new MapSqlParameterSource()
                        .addValue(MESSAGE_FIELD, card.getMessage())
                        .addValue(CREATED_AT_FIELD, Instant.now().atOffset(ZoneOffset.UTC)),
                keyHolder);

        return (int) keyHolder.getKeys().get(ID_FIELD);
    }

    @Override
    public Card get(int id) {
        return jdbcTemplate.query(SELECT_SQL,
                new Object[]{id},
                new int[]{Types.INTEGER},
                (rs) -> rs.next()
                        ? Card.builder()
                        .withId(rs.getInt(ID_FIELD))
                        .withMessage(rs.getString(MESSAGE_FIELD))
                        .withCreatedAt(rs.getTimestamp(CREATED_AT_FIELD).toInstant())
                        .build()
                        : null
        );
    }
}
