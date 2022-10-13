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
        new CarDAO().deleteCarById(21L);
    }
}