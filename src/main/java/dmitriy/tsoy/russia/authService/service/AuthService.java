package dmitriy.tsoy.russia.authService.service;

import dmitriy.tsoy.russia.authService.model.User;
import dmitriy.tsoy.russia.authService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    public User findUserByLoginAndPassword(String login, String password) {
        return userRepo.getUserByLoginAndPassword(login, password);
    }
}
