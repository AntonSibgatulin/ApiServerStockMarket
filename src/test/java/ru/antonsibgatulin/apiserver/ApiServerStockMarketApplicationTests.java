package ru.antonsibgatulin.apiserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.antonsibgatulin.apiserver.data.profile.Profile;
import ru.antonsibgatulin.apiserver.data.task.repository.TaskRepository;
import ru.antonsibgatulin.apiserver.data.token.Token;
import ru.antonsibgatulin.apiserver.data.token.repository.TokenRepository;
import ru.antonsibgatulin.apiserver.data.user.Role;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;

@SpringBootTest
class ApiServerStockMarketApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    void saveUserData() {

        var profile = new Profile();
        profile.setName("Test");
        profile.setSurname("TestSurname");


        var user = new User();
        user.setProfile(profile);

        user.setEmail("test@test.test");
        user.setPassword("password");
        user.setRole(Role.USER);

        userRepository.save(user);

        assert user.getId() != null;

    }


    @Test
    void saveTokenData() {

        var profile = new Profile();
        profile.setName("Test");
        profile.setSurname("TestSurname");


        var user = new User();
        user.setProfile(profile);

        user.setEmail("test@test.test");
        user.setPassword("password");
        user.setRole(Role.USER);

        userRepository.save(user);

        var token = new Token("test_token", user);
        tokenRepository.save(token);

        assert token.getId() != null;

    }



    @Test
    public void countTask(){
        long count = taskRepository.count();
        System.out.println(count);
    }
}
