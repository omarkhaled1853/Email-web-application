package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonSave {

    private final UsersService usersService;

    public JsonSave(UsersService usersService) {
        this.usersService = usersService;
    }

    //test
    public void save(){
        String path = "D:\\Mail Server\\users.json";
        save(Paths.get(path));
    }

    public void save(Path path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, User> usersDB = usersService.getUsersDB();
            mapper.writeValue(path.toFile(), usersDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
