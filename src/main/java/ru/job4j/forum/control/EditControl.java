package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class EditControl {
    PostService postService;
    UserService userService;

    public EditControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam(value = "id", required = false) int id,
                           Model model) {
            Post post = postService.getById(id);
            model.addAttribute("post", post);
            return "edit";
    }

    @PostMapping("/edit")
    public String edit(Post post) {
            postService.addPost(post);
            return "redirect:/index?login=true";
    }
}
