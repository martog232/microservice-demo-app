package com.example.carservice.service;

import com.example.carservice.data.entity.Car;
import com.example.carservice.data.repository.CarRepository;
import com.example.carservice.mapper.CarMapper;
import com.example.carservice.web.model.CarRespModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarRespModel> findAll() {
        return carRepository.findAll().stream().map(carMapper::mapToCarRespModel).collect(Collectors.toList());
    }

    @Override
    public CarRespModel findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return carMapper.mapToCarRespModel(car.get());
        } else throw new EntityNotFoundException("Car with id %d not found".formatted(id));
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarRespModel create(Car car) {
        return null;
    }

    @Override
    public CarRespModel update(Car car) {
        return null;
    }
}
