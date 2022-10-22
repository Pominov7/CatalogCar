package org.top.catalogcar.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.top.catalogcar.entity.CarsTEntity;
import org.top.catalogcar.model.IControlCar;

import java.util.Arrays;
import java.util.Collections;
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

    @Override
    public List<CarsTEntity> getStartsWith(String str) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<CarsTEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createQuery("SELECT e FROM CarsTEntity e where e.nameF " +
                            "like :name", CarsTEntity.class)
                    .setParameter("name", str + "%")
                    .getResultList();
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

    @Override
    public List<CarsTEntity> getAllCarsYoungerThan(int year) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<CarsTEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createNamedQuery("get_all_cars_younger_than", CarsTEntity.class)
                    .setParameter("param", year).getResultList();
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

    @Override
    public List<CarsTEntity> getCarsMoreExpensiveThan(double price) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<CarsTEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createNamedQuery("get_cars_more_expensive_than", CarsTEntity.class)
                    .setParameter("param", "+" + price).getResultList();
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

    @Override
    public List<CarsTEntity> getCarsByModel(String model) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<CarsTEntity> cars;

        try {
            transaction.begin();
            // операция
            cars = entityManager.createNamedQuery("get_cars_by_model", CarsTEntity.class)
                    .setParameter("model", model).getResultList();
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

    // Запросы с помощью Criteria
    @Override
    public List<CarsTEntity> sortingByYearOfManufacture() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        List<CarsTEntity> cars;
        try {
            transaction.begin();

            CriteriaQuery<CarsTEntity> criteria = builder.createQuery(CarsTEntity.class);
            Root<CarsTEntity> root = criteria.from(CarsTEntity.class);
            criteria.distinct(true);
            criteria.orderBy(builder.asc(root.get("yearF")));
            CriteriaQuery<CarsTEntity> select = criteria.select(root);
            TypedQuery<CarsTEntity> query = entityManager.createQuery(select);
            cars = query.getResultList();

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

    @Override
    public List<CarsTEntity> getTheCarByName(String nameCar) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        List<CarsTEntity> cars;
        try {
            transaction.begin();

            CriteriaQuery<CarsTEntity> query = builder.createQuery(CarsTEntity.class);
            Root<CarsTEntity> from = query.from(CarsTEntity.class);
            List nameList = Collections.singletonList(nameCar);

            Expression<String> exp = from.get("nameF");

            Predicate in = exp.in(nameList);
            query.where(in);
            CriteriaQuery<CarsTEntity> select = query.select(from);
            TypedQuery<CarsTEntity> query1 = entityManager.createQuery(select);
            cars = query1.getResultList();
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
}