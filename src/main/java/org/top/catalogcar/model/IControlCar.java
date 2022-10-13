package org.top.catalogcar.model;

import org.top.catalogcar.entity.CarsTEntity;

import java.util.List;

public interface IControlCar {

    void insertCar(CarsTEntity car); // добавить автомобиль

    CarsTEntity getCarById(Long id); // получить автомобиль по id

    List<CarsTEntity> getAllCars(); //  получить все автомобили

    void updateCar(CarsTEntity car); // обновить данные автомобиля

    void deleteCarById(Long id);  // удалить автомобиль по id
}
