package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.*;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public Collection<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post getById(Integer id) {
        return getAll().stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public void addPost(Post post) {
           posts.save(post);
    }

    @Transactional
    public void addCommentToPost(int postId, String comment) {
        Post post = getById(postId);
        post.addComm(Comment.of(comment));
        posts.save(post);
    }
}