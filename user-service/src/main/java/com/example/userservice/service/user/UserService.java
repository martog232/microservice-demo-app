package com.example.userservice.service.user;

import com.example.userservice.data.entity.User;
import com.example.userservice.web.model.UserReqModel;
import com.example.userservice.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {

    User findById(Long id) throws EntityNotFoundException;

    List<User> findAll();

    List<UserSimpleRespModel> findByName(String name);

    List<UserSimpleRespModel> findByCountry(String term);

    UserSimpleRespModel createUser(UserReqModel user);


    void deleteUserById(Long id);
}
