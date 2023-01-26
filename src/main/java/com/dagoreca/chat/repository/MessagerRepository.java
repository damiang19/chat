package com.dagoreca.chat.repository;

import com.dagoreca.chat.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagerRepository extends JpaRepository<Messages,Long>, JpaSpecificationExecutor<Messages> {
}
