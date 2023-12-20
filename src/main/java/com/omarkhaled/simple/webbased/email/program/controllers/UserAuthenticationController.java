package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.classes.UserAdapter;
import com.omarkhaled.simple.webbased.email.program.classes.UsersAdapter;
import com.omarkhaled.simple.webbased.email.program.interfaces.Adapter;
import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonLoad;
import com.omarkhaled.simple.webbased.email.program.saveLoad.SaveUsers;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping
@CrossOrigin
public class UserAuthenticationController {
    private final UsersService usersService;

    @Autowired
    public UserAuthenticationController(UsersService usersService) {
        this.usersService = usersService;
    }

    //load users
    @GetMapping ("/users")
    public void load () throws IOException {
        Adapter<Map<String, User>> adapter = new UsersAdapter();
        usersService.setUsersDB(adapter.load("users.json"));
    }

    //add new user
    @PostMapping ("/signUp")
    public ResponseEntity<Boolean> create (@RequestBody User user) throws IOException {
        Adapter<Map<String, User>> adapter = new UsersAdapter();
        User user1 = usersService.getUser(user.getEmail());
        //check for new user
        if(user1 != null) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        user1 = usersService.buildUser(user);
        usersService.addUser(user1);
        adapter.save(usersService.getUsersDB(), "users.json");
        return ResponseEntity.ok(true);
    }

    //get user
    @GetMapping ("/logIn")
    public ResponseEntity<Boolean> get (@RequestParam String email, @RequestParam String password){
         User user = usersService.getUser(email);
        //user not found
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //user password not acceptable
        if(!user.getPassword().equals(password)) throw new ResponseStatusException((HttpStatus.NOT_ACCEPTABLE));

        return ResponseEntity.ok(true);
    }

}
