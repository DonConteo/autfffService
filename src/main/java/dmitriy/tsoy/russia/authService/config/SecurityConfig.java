package dmitriy.tsoy.russia.authService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderCrutch();
    }

    class PasswordEncoderCrutch implements PasswordEncoder {
        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }
        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return charSequence.toString().equalsIgnoreCase(s);
        }
    }
}
