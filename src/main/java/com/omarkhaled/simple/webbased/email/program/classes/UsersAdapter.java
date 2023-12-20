package com.omarkhaled.simple.webbased.email.program.classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.interfaces.Adapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class UsersAdapter implements Adapter<Map<String, User>> {
    private final ObjectMapper mapper = new ObjectMapper();
    private String path = "D:\\Mail Server\\";
    @Override
    public void save(Map<String, User> users, String file) throws IOException {
        path = path.concat(file);
        mapper.writeValue(Paths.get(path).toFile(), users);
    }

    @Override
    public Map<String, User> load(String file) throws IOException {
        path = path.concat(file);
        InputStream inputStream = new FileInputStream(Paths.get(path).toFile());
        TypeReference<Map<String, User>> typeReference = new TypeReference<Map<String, User>>(){};
        Map<String, User> usersDB = mapper.readValue(inputStream, typeReference);
        inputStream.close();
        return usersDB;
    }

}
