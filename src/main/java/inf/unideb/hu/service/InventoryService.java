package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;

import java.util.Collection;

public interface InventoryService {

    Collection<Inventory> getAllInventory();
    void recordInventory(Inventory inventory) throws InventoryAlreadyExistsException, UnknownStoreException, UnknownFilmException;
    void deleteInventoryById(int id) throws UnknownInventoryException;
    void updateInventory(Inventory inventory) throws UnknownInventoryException, UnknownFilmException, UnknownStoreException;
    Inventory getInventoryById(int id) throws UnknownInventoryException;
}
