package com.omarkhaled.simple.webbased.email.program.classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuilderTest {

    @Test
    public void testuserbuildind(){
        var user=new User.Builder()
                .setEmail("test@CSED.com")
                .setPassword("23122002")
                .setUserName("mohamed")
                .setInbox(new HashMap<>())
                .setFolders(new HashMap<>())
                .setContacts(new HashMap<>())
                .setDraft(new HashMap<>())
                .setTrash(new HashMap<>())
                .setSent(new HashMap<>())
                .build();
        assertNotNull(user);
        assertEquals("test@CSED.com",user.getEmail());
        assertEquals("23122002",user.getPassword());
        assertEquals("mohamed",user.getUserName());
        assertEquals("test@CSED.com",user.getEmail());
        assertEquals("test@CSED.com",user.getEmail());
        assertNotNull(user.getInbox());
        assertNotNull(user.getSent());
        assertNotNull(user.getDraft());
        assertNotNull(user.getTrash());
        assertNotNull(user.getFolders());
        assertNotNull(user.getContacts());

    }
    @Test
    public void getmailsusinguseremail()
    {
        var mailBuilder = new Mail.Builder();
        List<String> attachments = new ArrayList<>();
        attachments.add("img1");
        attachments.add("img2");
        attachments.add("img3");

        Map<String, Mail> inbox = new HashMap<>();
        inbox.put("moahmed", mailBuilder.setId("1").setPriority(5).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build());
        inbox.put("omar", mailBuilder.setId("5").setPriority(5).setSender("ali@CSED.com").setReceiver("yousef@CSED.com").setContent("test mail builder").build());

        User user = new User.Builder()
                .setInbox(inbox)
                .build();

        Mail expectedMail = mailBuilder.setId("8").setPriority(10).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build();
        System.out.println(inbox.get("moahmed"));
        ///OUTPUT Mail{id='1', priority=5, sender='mohamed@CSED.com', receiver='omar@CSED.com', content='test mail builder', attachments=[img1, img2, img3]}
        assertEquals(expectedMail, inbox.get("moahmed"));
    }
    }



