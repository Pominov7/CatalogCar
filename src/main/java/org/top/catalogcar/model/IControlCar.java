package org.top.catalogcar.model;

import org.top.catalogcar.entity.CarsTEntity;

import java.util.List;

public interface IControlCar {

    void insertCar(CarsTEntity car); // добавить автомобиль

    CarsTEntity getCarById(Long id); // получить автомобиль по id

    List<CarsTEntity> getAllCars(); //  получить все автомобили

    void updateCar(CarsTEntity car); // обновить данные автомобиля

    void deleteCarById(Long id);  // удалить автомобиль по id

    List<CarsTEntity> getStartsWith(String str); // получить автомобили, название которых начинается с...

    List<CarsTEntity> getAllCarsYoungerThan(int year); // получить все автомобили, младше чем...


    List<CarsTEntity> getCarsMoreExpensiveThan(double price); //получить автомобили дороже чем...

    List<CarsTEntity> getCarsByModel(String model); // получить автомобили по модели

    List<CarsTEntity> sortingByYearOfManufacture(); // сортировка автомобилей по году выпуска

    List<CarsTEntity> getTheCarByName(String nameCar); // получить автомобиль по производителю
}

