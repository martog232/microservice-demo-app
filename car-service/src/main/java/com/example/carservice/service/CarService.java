package com.example.carservice.service;

import com.example.carservice.data.entity.Car;
import com.example.carservice.web.model.CarRespModel;

import java.util.List;

public interface CarService {

    List<CarRespModel> findAll();

    CarRespModel findById(Long id);

    void deleteById(Long id);

    CarRespModel create(Car car);

    CarRespModel update(Car car);
}
