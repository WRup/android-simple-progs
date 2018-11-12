package com.example.wrup.lab04.model.iDao;


public interface IDaoWrite<T> {

    void insert(T model);
    void update(T model);
    void delete(int id);
}
