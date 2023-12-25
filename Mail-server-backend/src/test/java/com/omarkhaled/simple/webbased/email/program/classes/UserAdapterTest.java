package com.omarkhaled.simple.webbased.email.program.classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserAdapterTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private InputStream inputStream;

    private UserAdapter userAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userAdapter = new UserAdapter(objectMapper);
    }

    @Test
    public void testSave() throws IOException {
        User user = new User.Builder().setEmail("test@example.com").setUserName("testUser").build();
        String fileName = "testUser.json";

        userAdapter.save(user, fileName);

        // Verify that the writeValue method is called with the correct arguments
        verify(objectMapper).writeValue(Paths.get("D:\\Mail Server\\testUser.json").toFile(), user);
    }

    @Test
    public void testLoad() throws IOException {
        String fileName = "testUser.json";
        User expectedUser = new User.Builder().setEmail("test@example.com").setUserName("testUser").build();

        // Mock the behavior of FileInputStream and ObjectMapper
        when(inputStream.read()).thenReturn(-1);
        when(objectMapper.readValue(inputStream, new TypeReference<User>(){})).thenReturn(expectedUser);

        // Mock the behavior of Paths and FileInputStream creation
        when(Paths.get("D:\\Mail Server\\testUser.json").toFile()).thenReturn(mock(File.class));
        when(new FileInputStream(String.valueOf(any()))).thenReturn((FileInputStream) inputStream);

        User loadedUser = userAdapter.load(fileName);

        // Verify that the readValue method is called with the correct arguments
        verify(objectMapper).readValue(inputStream, new TypeReference<User>(){});

        // Verify that the InputStream is closed
        verify(inputStream).close();

        // Verify that the loaded user is equal to the expected user
        assertEquals(expectedUser, loadedUser);
    }

}