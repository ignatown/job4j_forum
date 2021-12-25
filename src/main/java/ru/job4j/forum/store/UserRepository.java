package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Integer> {
    Collection<User> findAllByUsername(String username);
}