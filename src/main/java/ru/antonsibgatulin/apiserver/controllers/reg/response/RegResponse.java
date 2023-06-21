package ru.antonsibgatulin.apiserver.controllers.reg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonsibgatulin.apiserver.data.token.Token;
import ru.antonsibgatulin.apiserver.data.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegResponse {
    private Token token;

}
