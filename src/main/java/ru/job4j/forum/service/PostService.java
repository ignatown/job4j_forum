package ru.job4j.forum.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.*;

@Service
public class PostService {

    private static final Log logger = LogFactory.getLog(
            PostService.class);
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
        logger.info("IN getById get Post by id:" + id);
        return getAll().stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public void addPost(Post post) {
        post.setName(post.getName());
        logger.info("IN addPost add new Post: " + post.getName());
        posts.save(post);
    }

    @Transactional
    public void addCommentToPost(int postId, String comment) {
        Post post = getById(postId);
        post.addComm(Comment.of(comment));
        logger.info("IN addCommentToPost add new Comment to post with id: " + postId);
        posts.save(post);
    }
}