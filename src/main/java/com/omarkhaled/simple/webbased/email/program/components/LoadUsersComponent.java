package com.omarkhaled.simple.webbased.email.program.components;


import com.omarkhaled.simple.webbased.email.program.saveLoad.JsonLoad;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadUsersComponent implements CommandLineRunner{

    private JsonLoad jsonLoad;

    @Autowired
    public LoadUsersComponent(JsonLoad jsonLoad) {
        this.jsonLoad = jsonLoad;
    }

    @Override
    public void run(String... args) throws Exception {
        jsonLoad.load();
    }
}
