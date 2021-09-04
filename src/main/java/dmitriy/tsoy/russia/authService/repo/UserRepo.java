package dmitriy.tsoy.russia.authService.repo;

import dmitriy.tsoy.russia.authService.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepo {

    private List<User> list;

    public UserRepo() {
        this.list = Arrays.asList(new User("vasya", "123"),
                new User("petya", "1234"));
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return this.list.stream()
                .filter(user -> login.equals(user.getLogin()))
                .filter(user -> password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
