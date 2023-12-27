package com.omarkhaled.simple.webbased.email.program.components;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.classes.UsersAdapter;
import com.omarkhaled.simple.webbased.email.program.interfaces.Adapter;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoadUsersController implements CommandLineRunner {
    private final Adapter<Map<String, User>> adapter;
    private final UsersService usersService;

    @Autowired
    public LoadUsersController(UsersAdapter adapter, UsersService usersService) {
        this.adapter = adapter;
        this.usersService = usersService;
    }

    @Override
    public void run(String... args) throws Exception {
        usersService.setUsersDB(adapter.load("users.json"));
    }
}
