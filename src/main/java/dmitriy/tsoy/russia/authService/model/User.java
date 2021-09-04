package dmitriy.tsoy.russia.authService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private String login;
    private String password;
}
