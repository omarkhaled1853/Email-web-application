package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.helperClasses.UserHelper;
import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonSave;
import com.omarkhaled.simple.webbased.email.program.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.UUID;


@RestController
@RequestMapping
@CrossOrigin
public class UserAuthenticationController {

    private final UserService userService;

    @Autowired
    public UserAuthenticationController(UserService userService) {
        this.userService = userService;
        System.out.println(UUID.randomUUID().toString());
    }

    //add new user
    @PostMapping ("/signUp")
    public ResponseEntity<Boolean> create (@RequestBody User user) {
//        JsonSave jsonSave = new JsonSave(userService);
        String id = user.getId();
        //check for new user
        if(id != null) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        User user1 = new User
                .Builder()
                .setId(UUID.randomUUID().toString())
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .build();
        userService.mapping(user);
        userService.addUser(user);
//        jsonSave.save();

        return ResponseEntity.ok(true);
    }

    //get user
    @GetMapping ("/logIn")
    public ResponseEntity<Boolean> get (@RequestParam String email, @RequestParam String password){
        UserHelper userHelper = new UserHelper();
        userHelper.setEmail(email);
        userHelper.setPassword(password);

        String id = userService.getMapping(userHelper);
        //user not found
        if(id == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(true);
    }

}
