package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.helperClasses.UserAuthentication;
import com.omarkhaled.simple.webbased.email.program.services.UserAuthenticationService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonSave {

    private final UserAuthenticationService userAuthenticationService;

    public JsonSave(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    //test
    public void save(){
        String path = "D:\\users.json";
        save(Paths.get(path));
    }

    public void save(Path path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<UserAuthentication> list = userAuthenticationService.getUserSignUpList();
            mapper.writeValue(path.toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
