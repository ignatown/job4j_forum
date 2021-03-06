package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String post(@RequestParam(value = "id", required = false) int id,
                       Model model) {
        Post post = postService.getById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("addComment")
    public String addComment(@RequestParam(value = "id", required = false) int id,
                             @ModelAttribute("comment") String comment) {
        comment = "[" + SecurityContextHolder.getContext().getAuthentication().getName() + "]: " + comment;
        postService.addCommentToPost(id, comment);
        return "redirect:/post?id=" + id;
    }
}
