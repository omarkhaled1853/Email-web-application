package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

public class JsonSave {

    private final UserService userService;

    public JsonSave(UserService userService) {
        this.userService = userService;
    }

    //test
    public void save(){
        String path = "D:\\users.json";
        save(Paths.get(path));
    }

    public void save(Path path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, User> userDB = userService.getUsersDB();
            mapper.writeValue(path.toFile(), userDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
