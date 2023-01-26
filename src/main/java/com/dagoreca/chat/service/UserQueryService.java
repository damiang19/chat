package com.dagoreca.chat.service;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.dto.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserQueryService  {

    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> findByCriteria(UserCriteria userCriteria){
        Specification<User> specification = createSpecification(userCriteria);
        return userRepository.findAll(specification);
    }


    protected Specification<User> createSpecification(UserCriteria userCriteria){
        Specification<User> specification = Specification.where(null);
//
//        if(userCriteria.getNameStartsWith() != null){
//            specification = specification.and((root, query, criteriaBuilder) -> {
//                Predicate userName = criteriaBuilder.like(criteriaBuilder.upper(root.get(User_.firstName)),
//                        (userCriteria.getNameStartsWith() + "%").toUpperCase());
//               return criteriaBuilder.and(userName);
//            });
//        }
        return specification;
    }
}
