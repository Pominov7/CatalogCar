package org.top.catalogcar.dao;

import org.junit.jupiter.api.Test;
import org.top.catalogcar.entity.CarsTEntity;

import java.util.List;

class CarDAOTest {

    @Test
    void insertCar() {

        CarsTEntity car = new CarsTEntity();
        car.setNameF("ЗИЛ");
        car.setModelF("133");
        car.setYearF(1990);
        car.setPriceF(77000.0);
        new CarDAO().insertCar(car);
    }

    @Test
    void getCarById() {
        CarsTEntity result = new CarDAO().getCarById(13L);
        System.out.println(result);
    }

    @Test
    void getAllCars() {
        List<CarsTEntity> cars = new CarDAO().getAllCars();
        for (CarsTEntity car : cars) {
            System.out.println(car);
        }
    }

    @Test
    void updateCar() {
        CarsTEntity result = new CarDAO().getCarById(13L);
        result.setYearF(2000);
        System.out.println(result);
    }


    @Test
    void deleteCarById() {
        new CarDAO().deleteCarById(31L);
    }

    // получить автомобили, название которых начинается с "To"
    @Test
    void getStartsWith() {
        List<CarsTEntity> cars = new CarDAO().getStartsWith("To");
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // получить все автомобили, младше 2010 года выпуска
    @Test
    void getAllCarsYoungerThan() {
        List<CarsTEntity> cars = new CarDAO().getAllCarsYoungerThan(2010);
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // получить автомобили дороже 1 000 000
    @Test
    void getCarsMoreExpensiveThan() {
        List<CarsTEntity> cars = new CarDAO().getCarsMoreExpensiveThan(1000000.0);
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }

    }

    // получить автомобили по модели
    @Test
    void getCarsByModel() {
        List<CarsTEntity> cars = new CarDAO().getCarsByModel("Vista");
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // сортировка автомобилей по году выпуска
    @Test
    void sortingByYearOfManufacture() {
        List<CarsTEntity> cars = new CarDAO().sortingByYearOfManufacture();
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }
    }

    // получить автомобиль по производителю
    @Test
    void getTheCarByName() {
        List<CarsTEntity> cars = new CarDAO().getTheCarByName("SUZUKI");
        for (CarsTEntity auto : cars) {
            System.out.println(auto);
        }
    }
}