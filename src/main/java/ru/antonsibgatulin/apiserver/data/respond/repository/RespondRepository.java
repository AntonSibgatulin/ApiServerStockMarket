package ru.antonsibgatulin.apiserver.data.respond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonsibgatulin.apiserver.data.respond.Respond;

public interface RespondRepository extends JpaRepository<Respond,Long> {
}
