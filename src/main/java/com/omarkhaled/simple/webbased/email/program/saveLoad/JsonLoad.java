package com.omarkhaled.simple.webbased.email.program.saveLoad;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonLoad {
    private final UsersService usersService;

    public JsonLoad(UsersService usersService) {
        this.usersService = usersService;
    }

    //test
    public void load(){
        String path = "D:\\Mail Server\\users.json";
        if(Paths.get(path).toFile().length() != 0)
            load(Paths.get(path));
    }

    public void load(Path path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(path.toFile());
            TypeReference<Map<String, User>> typeReference = new TypeReference<Map<String, User>>(){};
            Map<String, User> usersDB = mapper.readValue(inputStream, typeReference);
            usersService.setUsersDB(usersDB);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
