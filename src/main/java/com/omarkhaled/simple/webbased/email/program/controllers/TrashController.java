package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.SentService;
import com.omarkhaled.simple.webbased.email.program.services.TrashService;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TrashController {

    private final UsersService usersService;
    private final TrashService trashService;

    @Autowired
    public TrashController(UsersService usersService, TrashService trashService) {
        this.usersService = usersService;
        this.trashService = trashService;
    }

    //get trashed mails
    @GetMapping ("/mails/trash")
    public Collection<Mail> getTrash (@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        trashService.setTrashDB(user.getTrash());

        return trashService.getMails();
    }

    //delete trashed mail
    @DeleteMapping ("/mail/delete")
    public void delete(@RequestParam List<String> ids) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<String> ids = mapper.readValue(list, List.class);

        List<Mail> mails = trashService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
