package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.ContactService;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin
public class ContactController {
    private final UsersService usersService;
    private final ContactService contactService;

    @Autowired
    public ContactController(UsersService usersService, ContactService contactService) {
        this.usersService = usersService;
        this.contactService = contactService;
    }

    //get contacts
    @GetMapping ("/contacts")
    public Map<String, List<String>> getContacts(@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return contactService.getContacts(id, usersService.getUsersDB());
    }

    //create contact
    @PostMapping ("/contact/create")
    public void createContact(@RequestParam String id, @RequestParam String name, @RequestParam String email){
        List<String> emails = contactService.getContact(id, name, usersService.getUsersDB());

        if(emails != null) throw new ResponseStatusException(HttpStatus.CREATED);

        contactService.createName(id, email, name, usersService.getUsersDB());
        contactService.addEmail(id, email, name, usersService.getUsersDB());
    }

    //add email
    @PostMapping ("/contact/add")
    public void addEmail(@RequestParam String id, @RequestParam String name, @RequestParam String email){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(user.getContacts().get(name).contains(email)) throw new ResponseStatusException(HttpStatus.CREATED);

        contactService.addEmail(id, email, name, usersService.getUsersDB());
    }

    @PutMapping ("/contact/rename")
    public void contactRename(@RequestParam String id, @RequestParam String oldContactName, @RequestParam String newContactName){
        Map<String, List<String>> contacts = contactService.getContacts(id, usersService.getUsersDB());
        if (contacts.containsKey(newContactName)) throw new ResponseStatusException(HttpStatus.CREATED);
        contactService.renameContact(id, oldContactName, newContactName, usersService.getUsersDB());
    }

    //delete contact
    @DeleteMapping ("/contact/delete")
    public void delete(@RequestParam String id, @RequestParam List<String> ids){
        List<List<String>> contacts = contactService.deleteMails(id, ids, usersService.getUsersDB());

        for(List<String> contact: contacts)
            if(contact == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
