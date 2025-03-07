package com.example.carservice.mapper;


import com.example.carservice.data.entity.Car;
import com.example.carservice.web.model.CarRespModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarRespModel mapToCarRespModel(Car model){}
}
