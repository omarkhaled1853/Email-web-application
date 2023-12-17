package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonLoad {
    private final UserService userService;

    public JsonLoad(UserService userService) {
        this.userService = userService;
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
            TypeReference<Map<String, User>> typeReference = new TypeReference<Map<String, User>>(){};
            Map<String, User> userDB = mapper.readValue(inputStream, typeReference);
            userService.setUsersDB(userDB);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
