package ru.antonsibgatulin.apiserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.antonsibgatulin.apiserver.controllers.reg.request.RequestBodyRegUser;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestRespone;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestTaskCreate;
import ru.antonsibgatulin.apiserver.data.respond.Respond;
import ru.antonsibgatulin.apiserver.data.respond.mapper.ResponeMapper;
import ru.antonsibgatulin.apiserver.data.task.Task;
import ru.antonsibgatulin.apiserver.data.task.TypeTask;
import ru.antonsibgatulin.apiserver.data.task.mapper.TaskMapper;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.mapper.UserMapper;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;
import ru.antonsibgatulin.apiserver.utils.EnumListConverter;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {


    private final UserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {

            return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            @Override
            public User fromRequestRegUserToUser(RequestBodyRegUser requestBodyRegUser) {
                var user = new User();
                user.setEmail(requestBodyRegUser.getEmail());
                user.setPassword(requestBodyRegUser.getPassword());
                user.setProfile(requestBodyRegUser.getProfile());

                return null;
            }
        };
    }


    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper() {
            @Override
            public Task fromRequestTaskCreateoTask(RequestTaskCreate requestTaskCreate) {
                var task = new Task();
                task.setName(requestTaskCreate.getName());
                task.setDescription(requestTaskCreate.getDescription());
                task.setPrice(requestTaskCreate.getPrice());
                task.setBetterPrice(requestTaskCreate.getBetterPrice());
                task.setTimeCreate(requestTaskCreate.getTimeCreate());
                task.setCountLike(task.getCountLike());
                task.setCountView(requestTaskCreate.getCountView());
                var enumListConventer = new EnumListConverter();
                List<TypeTask> typeTasks = enumListConventer.convertToEntityAttribute(requestTaskCreate.getTypeTasks());
                task.setTypeTasks(typeTasks);
                return task;
            }

            @Override
            public void update(Task task, RequestTaskCreate requestTaskCreate) {
                task.setName(requestTaskCreate.getName());
                task.setDescription(requestTaskCreate.getDescription());
                task.setPrice(requestTaskCreate.getPrice());
                task.setBetterPrice(requestTaskCreate.getBetterPrice());
                task.setTimeCreate(requestTaskCreate.getTimeCreate());
                task.setCountLike(task.getCountLike());
                task.setCountView(requestTaskCreate.getCountView());
                var enumListConventer = new EnumListConverter();
                List<TypeTask> typeTasks = enumListConventer.convertToEntityAttribute(requestTaskCreate.getTypeTasks());
                task.setTypeTasks(typeTasks);

            }
        };
    }

    @Bean
    public ResponeMapper responeMapper() {
        return new ResponeMapper() {
            @Override
            public Respond fromRequestResponeToRespond(RequestRespone requestRespone) {
                var respond = new Respond();
                respond.setMessage(requestRespone.getMessage());
                respond.setPrice(requestRespone.getPrice());
                respond.setCountDay(requestRespone.getCountDay());
                respond.setType(requestRespone.getType());
                respond.setTime(System.currentTimeMillis());
                return respond;
            }
        };
    }

}
