package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;

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
public interface InventoryDao {

    void createInventory(Inventory inventory) throws InventoryAlreadyExistsException, UnknownStoreException, UnknownFilmException;
    Collection<Inventory> readAll();
    Inventory readById(int id) throws UnknownInventoryException;
    void deleteById(int id) throws UnknownInventoryException;
    void update(Inventory inventory) throws UnknownInventoryException, UnknownFilmException, UnknownStoreException;
}
