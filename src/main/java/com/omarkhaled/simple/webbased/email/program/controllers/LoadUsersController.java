package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonLoad;
import com.omarkhaled.simple.webbased.email.program.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadUsersController {
    private final UserService userService;

    @Autowired
    public LoadUsersController(UserService userService) {
        this.userService = userService;
    }

    //load users
    @GetMapping ("/users")
    public void load (){
        JsonLoad jsonLoad = new JsonLoad(userService);
        jsonLoad.load();
    }

}
