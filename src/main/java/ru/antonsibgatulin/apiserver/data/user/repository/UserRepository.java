package ru.antonsibgatulin.apiserver.data.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antonsibgatulin.apiserver.data.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email,String password);
    User getUserById(Long id);

}
