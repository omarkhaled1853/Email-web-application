package com.omarkhaled.simple.webbased.email.program.components;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.classes.UsersAdapter;
import com.omarkhaled.simple.webbased.email.program.interfaces.Adapter;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class SaveUsersController implements HandlerInterceptor {
    private final UsersService usersService;
    private final Adapter<Map<String, User>> adapter;

    @Autowired
    public SaveUsersController(UsersService usersService, UsersAdapter adapter) {
        this.usersService = usersService;
        this.adapter = adapter;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Map<String, User> usersDB = usersService.getUsersDB();
        adapter.save(usersDB, "users.json");
    }
}
