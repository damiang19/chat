package com.dagoreca.chat.repository;

import com.dagoreca.chat.service.dto.MessagesDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends MongoRepository<MessagesDTO,Long> {
}
