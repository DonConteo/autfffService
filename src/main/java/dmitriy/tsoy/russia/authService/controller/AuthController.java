package dmitriy.tsoy.russia.authService.controller;

import dmitriy.tsoy.russia.authService.config.JwtUtil;
import dmitriy.tsoy.russia.authService.model.AuthRequest;
import dmitriy.tsoy.russia.authService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("auth")
    public ResponseEntity checkAuthAndGiveTokenToUser(@RequestBody AuthRequest authRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if(authService.findUserByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword()) == null) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        responseHeaders.set("JwtToken", jwtUtil.generateToken(authRequest.getLogin()));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("I need Food");
    }

    @GetMapping("check")
    public ResponseEntity<Boolean> checkAuth(@RequestHeader("JwtToken") String token) {
        return jwtUtil.checkToken(token)
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }
}
