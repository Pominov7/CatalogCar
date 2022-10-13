package org.top.catalogcar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.top.catalogcar.entity.CarsTEntity;
import org.top.catalogcar.model.IControlCar;

import java.util.List;

public class CarDAO implements IControlCar {

    // CREATE
    @Override
    public void insertCar(CarsTEntity car) {
        // 1. создаем фабрику
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        // 2. manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 3. объект транзакции
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // выполнение самой операции создания записи в БД
            transaction.begin();
            entityManager.persist(car);   // создание новой записи на основе объекта
            transaction.commit();
        } finally {
            // все закрыть и откатить транзакцию, если нужно
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public CarsTEntity getCarById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // переменная для хранения объекта-результата поиска
        CarsTEntity result = null;
        try {
            transaction.begin();
            // операция
            result = entityManager.find(CarsTEntity.class, id);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return result;  // вернуть результат
    }

    // READ
    @Override
    public List<CarsTEntity> getAllCars() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<CarsTEntity> cars = null;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createQuery("SELECT e FROM CarsTEntity e").getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return cars;
    }

    // UPDATE
    @Override
    public void updateCar(CarsTEntity car) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // 1. получаю обновляемый объект по Id
            CarsTEntity updated = entityManager.find(CarsTEntity.class, car.getId());
            // 2. обновляю поля обновляемого объекта
            updated.updateFields(car);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    // DELETE
    @Override
    public void deleteCarById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // 1. получить удаляемый объект
            CarsTEntity deleted = entityManager.find(CarsTEntity.class, id);
            // 2. удалить
            entityManager.remove(deleted);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}