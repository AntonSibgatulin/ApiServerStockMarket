package ru.antonsibgatulin.apiserver.data.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antonsibgatulin.apiserver.data.token.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token getTokenByToken(String token);
}
