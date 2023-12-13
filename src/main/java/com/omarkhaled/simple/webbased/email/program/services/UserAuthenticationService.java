package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.helperClasses.UserAuthentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthenticationService {
    private List<UserAuthentication> userAuthenticationList;

    public List<UserAuthentication> getUserSignUpList() {
        return userAuthenticationList;
    }

    public void setUserSignUpList(List<UserAuthentication> userAuthenticationList) {
        this.userAuthenticationList = userAuthenticationList;
    }

    public void addUser(UserAuthentication userAuthentication){
        userAuthenticationList.add(userAuthentication);
    }

    public boolean checkUser(String email, String password){
        for (UserAuthentication userAuthentication : userAuthenticationList) {
            if (userAuthentication.getEmail().equals(email) && userAuthentication.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
