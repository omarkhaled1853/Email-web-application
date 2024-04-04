package com.omarkhaled.simple.webbased.email.program.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MailTest {

    private Mail mail;

    @BeforeEach
    public void setUp() {
        mail = new Mail();
    }



    @Test
    public void testBuilder() {
        Attachment attachment;
        attachment = new Attachment();
        attachment.setId("123");
        attachment.setAttachmentName("example.txt");
        attachment.setContentType("text/plain");
        byte[] testData = { 1, 2, 3, 4, 5 };
        attachment.setData(testData);
//        ArrayList<Attachment> arrattachments;
//        arrattachments=new ArrayList<>();
//        arrattachments.add(attachment);
//        arrattachments.add(attachment);
        Mail.Builder builder = new Mail.Builder();
        builder.setId("123")
                .setSender("john@example.com")
                .setReceiver("jane@example.com")
                .setContent("Hello, Jane!")
                .setSubject("Greetings")
                .setDate("2023-01-01")
                .setPriority(2)
//                .setAttachments(arrattachments)
                .setCreateOn(1640995200000L) // January 1, 2022
                .setDestroyOn(1641081600000L); // January 2, 2022

        mail = builder.build();

        assertPropertyValues();
    }

    private void assertNullValues() {
        assertNull(mail.getId());
        assertNull(mail.getSender());
        assertNull(mail.getReceiver());
        assertNull(mail.getContent());
        assertNull(mail.getSubject());
        assertNull(mail.getDate());
        assertNull(mail.getAttachments());
    }

    private void assertPropertyValues() {
        assertEquals("123", mail.getId());
        assertEquals("john@example.com", mail.getSender());
        assertEquals("jane@example.com", mail.getReceiver());
        assertEquals("Hello, Jane!", mail.getContent());
        assertEquals("Greetings", mail.getSubject());
        assertEquals("2023-01-01", mail.getDate());
        assertEquals(2, mail.getPriority());
        assertNotNull(mail.getAttachments());
        assertEquals(2, mail.getAttachments().size());
        assertEquals(1640995200000L, mail.getCreateOn());
        assertEquals(1641081600000L, mail.getDestroyOn());
    }
}
