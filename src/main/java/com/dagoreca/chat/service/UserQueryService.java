package com.dagoreca.chat.service;

import com.dagoreca.chat.domain.User;
import com.dagoreca.chat.domain.User_;
import com.dagoreca.chat.repository.UserRepository;
import com.dagoreca.chat.service.dto.UserCriteria;
import com.dagoreca.chat.service.dto.UserDTO;
import com.dagoreca.chat.service.mapper.UserMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserQueryService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public List<UserDTO> findByCriteria(UserCriteria userCriteria){
        Specification<User> specification = createSpecification(userCriteria);
        return userMapper.toDto(userRepository.findAll(specification));
    }


    protected Specification<User> createSpecification(UserCriteria userCriteria){
        Specification<User> specification = Specification.where(null);

        if(userCriteria.getNameStartsWith() != null){
            specification = specification.and((root, query, criteriaBuilder) -> {
                Predicate userName = criteriaBuilder.like(criteriaBuilder.upper(root.get(User_.firstName)),
                        (userCriteria.getNameStartsWith() + "%").toUpperCase());
               return criteriaBuilder.and(userName);
            });
        }
        return specification;
    }
}
