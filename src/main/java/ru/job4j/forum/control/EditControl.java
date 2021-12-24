package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class EditControl {
    private final PostService postService;

    public EditControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam(value = "id", required = false) int id,
                           Model model) {
          Post post = Post.of("");
          if (id != 0) {
            post = postService.getById(id);
          }
          model.addAttribute("post", post);
          return "edit";
    }

    @PostMapping("/edit")
    public String edit(Post post) {
            postService.addPost(post);
            return "redirect:/index?login=true";
    }
}
