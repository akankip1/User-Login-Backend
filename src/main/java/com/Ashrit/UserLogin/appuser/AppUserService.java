package com.Ashrit.UserLogin.appuser;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {
    private final static String User_not_found="User with email %s doesn't exist";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->new UsernameNotFoundException(String.format(User_not_found,email)));
    }
    public String signup(AppUser appUser){
        boolean emailExists=appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(emailExists){


            return "email exists";
        }
        String encodedPwd=bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPwd);
        appUserRepository.save(appUser);


        return "Registered Successfully";
    }

    public List<String> getMessages(ResponseEntity<List<String>> messages){
        return messages.getBody();
    }
}
