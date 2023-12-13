package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.helperClasses.UserAuthentication;
import com.omarkhaled.simple.webbased.email.program.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class JsonLoad {
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public JsonLoad(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    //test
    public void load(){
        String path = "D:\\users.json";
        load(Paths.get(path));
    }

    public void load(Path path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(path.toFile());
            TypeReference<List<UserAuthentication>> typeReference = new TypeReference<List<UserAuthentication>>(){};
            List<UserAuthentication> list = mapper.readValue(inputStream, typeReference);
            userAuthenticationService.setUserSignUpList(list);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
