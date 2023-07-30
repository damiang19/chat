package com.dagoreca.chat.repository;

import com.dagoreca.chat.domain.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation,Long> {
}
