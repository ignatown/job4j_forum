package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private AtomicInteger id = new AtomicInteger(2);

    public boolean addUser(User user) {
        if (findUserByName(user.getUsername()) == null) {
            user.setId(id.getAndIncrement());
            users.add(user);
            return true;
        }
        return false;
    }

    public User findUserByName(String name) {
        User user = null;
        for (User u:users) {
            if (u.getUsername().equals(name)) {
                user = u;
            }
        }
        return user;
    }

}
