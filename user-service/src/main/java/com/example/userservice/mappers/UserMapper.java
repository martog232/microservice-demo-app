package com.example.userservice.mappers;

import com.example.userservice.data.entity.User;
import com.example.userservice.web.model.UserSimpleRespModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "countryName", source = "country.name")
    UserSimpleRespModel mapToUserSimpleRespModel(User user);
}
