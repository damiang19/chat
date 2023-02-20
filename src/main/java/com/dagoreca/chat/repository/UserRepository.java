package com.dagoreca.chat.repository;

import com.dagoreca.chat.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByLogin(String login);

    List<User> findAllByLoginStartingWith(String login);
}
