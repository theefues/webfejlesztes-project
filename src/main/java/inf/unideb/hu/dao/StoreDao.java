package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface StoreDao {

    void createStore(Store Store) throws StoreAlreadyExistsException, UnknownAddressException;
    Collection<Store> readAll();
    Store readById(int id) throws UnknownStoreException;
    void deleteById(int id) throws UnknownStoreException;
    void update(Store Store) throws UnknownStoreException, UnknownAddressException;
}
