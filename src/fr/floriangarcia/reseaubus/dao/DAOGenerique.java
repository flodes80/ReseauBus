package fr.floriangarcia.reseaubus.dao;

import java.util.LinkedList;

public abstract class DAOGenerique<T> {
    public abstract T delete(T obj);
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void saveAll(LinkedList<T> linkedListObj);
    public abstract T findById(int cle);
    public abstract LinkedList<T> findByName(String name);
    public abstract LinkedList<T> findAll();
}
