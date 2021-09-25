package com.example.GiveLove.services.impl;

import com.example.GiveLove.converter.UserConverter;
import com.example.GiveLove.dto.PageDTO;
import com.example.GiveLove.entity.Role;
import com.example.GiveLove.entity.Users;
import com.example.GiveLove.repository.UsersRepository;
import com.example.GiveLove.repository.specification.SearchCriteria;
import com.example.GiveLove.repository.specification.UserSpecification;
import com.example.GiveLove.responseCode.ErrorCode;
import com.example.GiveLove.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserConverter userConverter;


    @Override
    public Users save(Users users) {

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(users.getUsername()));

        if(user.isPresent()){
            throw new DuplicateKeyException(ErrorCode.USERNAME_NOT_AVAILABLE);
        }
        Users savedUser = usersRepository.save(users);
        savedUser.setPassword("");
        return savedUser;
    }

    @Override
    public Users findByUsername(String username) {

       Optional<Users>user =Optional.ofNullable( usersRepository.findByUsername(username));
        if(!user.isPresent()){
            throw new DuplicateKeyException(ErrorCode.USERNAME_NOT_AVAILABLE);
        }
        return  user.get();
    }

    @Override
    public PageDTO getAllUser(Specification specification, Pageable pageable) {
try{

}catch (Exception ex){
    throw new DataAccessResourceFailureException(ErrorCode.GET_USER_FAIL);
}
        return userConverter.convertPageToDTO(usersRepository.findAll(specification , pageable));
    }

    @Override
    public void updateRole(Long userId) {

        UserSpecification specification = new UserSpecification(new SearchCriteria("id" , ":" , userId));

        Optional<Users> usersOptional = usersRepository.findOne(specification);

        if(usersOptional.isPresent()){
            Users updatedUser = usersOptional.get();
            updatedUser.setRole(Role.builder().id(3L).build());
            usersRepository.save(updatedUser);
        }
        else{
            throw new IllegalIdentifierException(ErrorCode.USER_NOT_FOUND);
        }
    }


}
