package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
@SessionAttributes(value = "username")
public class LoginControl {
    private final UserService userService;

    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        if (userService.findUserByName(user.getUsername()).getPassword().equals(user.getPassword())) {
            return "redirect:/index?login=true&username=" + user.getUsername();
        }
        return "redirect:/login";
    }

}
