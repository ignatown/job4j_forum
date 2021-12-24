package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("select distinct p from Post p left join fetch p.comments order by p.id")
    public Post findAll(String name);
}
