package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonLoad;
import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonSave;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



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
    public void load (){
        JsonLoad jsonLoad = new JsonLoad(usersService);
        jsonLoad.load();
    }

    //add new user
    @PostMapping ("/signUp")
    public ResponseEntity<Boolean> create (@RequestBody User user) {
        JsonSave jsonSave = new JsonSave(usersService);
        User user1 = usersService.getUser(user.getEmail());
        //check for new user
        if(user1 != null) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        user1 = usersService.buildUser(user);
        usersService.addUser(user1);
        jsonSave.save();
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
