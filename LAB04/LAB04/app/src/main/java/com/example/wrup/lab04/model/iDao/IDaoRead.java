package com.example.wrup.lab04.model.iDao;

import java.util.ArrayList;



public interface IDaoRead<T> {
    T findById(int id);
    ArrayList<T> findAll();
    ArrayList<T> findAllByType(String type);
}
