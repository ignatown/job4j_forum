package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    private final Map<Integer, Post> posts = new HashMap<>();
    AtomicInteger postId = new AtomicInteger(2);

    public PostService() {
        Post post = Post.of("Продаю машину ладу 01.");
        post.setId(1);
        posts.put(1, post);
    }

    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post getById(int id) {
        return posts.get(id);
    }

    public void addPost(Post post) {
            if (post.getId() == 0) {
                post.setId(postId.getAndIncrement());
            }
            posts.put(post.getId(), post);
    }

    public Post findPostByName(String name) {
        Post post = null;
        for (Post p: getAll()) {
            if (p.getName().equals(name)) {
                post = p;
            }
        }
        return post;
    }
}