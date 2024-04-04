package com.omarkhaled.simple.webbased.email.program.classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuilderTest {

//    @Test
//    public void testuserbuildind() {
//        var user = new User.Builder()
//                .setEmail("test@CSED.com")
//                .setPassword("23122002")
//                .setUserName("mohamed")
//                .setInbox(new HashMap<>())
//                .setFolders(new HashMap<>())
//                .setContacts(new HashMap<>())
//                .setDraft(new HashMap<>())
//                .setTrash(new HashMap<>())
//                .setSent(new HashMap<>())
//                .build();
//        assertNotNull(user);
//        assertEquals("test@CSED.com", user.getEmail());
//        assertEquals("23122002", user.getPassword());
//        assertEquals("mohamed", user.getUserName());
//        assertEquals("test@CSED.com", user.getEmail());
//        assertEquals("test@CSED.com", user.getEmail());
//        assertNotNull(user.getInbox());
//        assertNotNull(user.getSent());
//        assertNotNull(user.getDraft());
//        assertNotNull(user.getTrash());
//        assertNotNull(user.getFolders());
//        assertNotNull(user.getContacts());
//
//    }
//
//    @Test
//    public void getmailsusinguseremailtestinbox() {
//        var mailBuilder = new Mail.Builder();
//        List<String> attachments = new ArrayList<>();
//        attachments.add("img1");
//        attachments.add("img2");
//        attachments.add("img3");
//
//        Map<String, Mail> inbox = new HashMap<>();
//        inbox.put("mohamed", mailBuilder.setId("1").setPriority(5).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build());
//        inbox.put("omar", mailBuilder.setId("5").setPriority(5).setSender("ali@CSED.com").setReceiver("yousef@CSED.com").setContent("test mail builder").build());
//
//        User user = new User.Builder()
//                .setInbox(inbox)
//                .build();
//        Mail expectedMail = mailBuilder.setId("8").setPriority(10).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build();
//        assertEquals(expectedMail.getReceiver(), inbox.get("mohamed").getReceiver());
//        assertEquals(expectedMail.getSender(), inbox.get("mohamed").getSender());
//        assertEquals(expectedMail.getId(), inbox.get("mohamed").getId());
//        assertEquals(expectedMail.getContent(), inbox.get("mohamed").getContent());
//        assertEquals(expectedMail.getAttachments(), inbox.get("mohamed").getAttachments());
//        assertEquals(expectedMail.getPriority(), inbox.get("mohamed").getPriority());
//        assertEquals(expectedMail.getPriority(), inbox.get("mohamed").getPriority());
//    }
//
//    @Test
//    public void getmailsusinguseremailtestSent() {
//        var mailBuilder = new Mail.Builder();
//        List<String> attachments = new ArrayList<>();
//        attachments.add("img1");
//        attachments.add("img2");
//        attachments.add("img3");
//
//        Map<String, Mail> sent = new HashMap<>();
//        sent.put("mohamed", mailBuilder.setId("1").setPriority(5).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build());
//        sent.put("omar", mailBuilder.setId("5").setPriority(5).setSender("ali@CSED.com").setReceiver("yousef@CSED.com").setContent("test mail builder").build());
//
//        User user = new User.Builder()
//                .setSent(sent)
//                .build();
//        System.out.println(sent.get("mohamed").getId());
//        System.out.println("-------------------------------");
//        Mail expectedMail = mailBuilder.setId("8").setPriority(10).setSender("mohamed@CSED.com").setReceiver("omar@CSED.com").setContent("test mail builder").build();
//        System.out.println(expectedMail.getId());
//        System.out.println(sent.get("mohamed").getId());
//        assertEquals(expectedMail.getReceiver(), sent.get("mohamed").getReceiver());
//        assertEquals(expectedMail.getSender(), sent.get("mohamed").getSender());
//        assertEquals(expectedMail.getId(), sent.get("mohamed").getId());
//        assertEquals(expectedMail.getContent(), sent.get("mohamed").getContent());
//        assertEquals(expectedMail.getAttachments(), sent.get("mohamed").getAttachments());
//        assertEquals(expectedMail.getPriority(), sent.get("mohamed").getPriority());
//        assertEquals(expectedMail.getPriority(), sent.get("mohamed").getPriority());
//
//    }
}



