package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.helperClasses.UserAuthentication;
import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonSave;
import com.omarkhaled.simple.webbased.email.program.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@CrossOrigin
public class UserAuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }


    @PostMapping ("/signUp")
    public void saveUsers (@RequestBody String user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonSave jsonSave = new JsonSave(userAuthenticationService);
        UserAuthentication userAuthentication = mapper.readValue(user, UserAuthentication.class);
        userAuthenticationService.addUser(userAuthentication);
        jsonSave.save();
    }

    @GetMapping ("/logIn")
    public ResponseEntity<Boolean> checkUser (@RequestParam String email, @RequestParam String password){
        boolean isUSerValid = userAuthenticationService.checkUser(email, password);
        return isUSerValid? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }

}
