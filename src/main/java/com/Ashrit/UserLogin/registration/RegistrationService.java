package com.Ashrit.UserLogin.registration;

import com.Ashrit.UserLogin.appuser.AppUser;
import com.Ashrit.UserLogin.appuser.AppUserRole;
import com.Ashrit.UserLogin.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;


    public String register(RegistrationRequest request) {
        boolean validEmail = emailValidator.test(request.getEmail());

        if(!validEmail){
            return "invalid email";
        }
        String token= appUserService.signup(
                new AppUser(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER

        ));


        return token;

    }

}
