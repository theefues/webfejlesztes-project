package inf.unideb.hu.service;

import inf.unideb.hu.dao.InventoryDao;
import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryDao inventoryDao;

    @Override
    public Collection<Inventory> getAllInventory() {
        return inventoryDao.readAll();
    }

    @Override
    public void recordInventory(Inventory inventory) throws InventoryAlreadyExistsException, UnknownStoreException, UnknownFilmException {
        inventoryDao.createInventory(inventory);
    }

    @Override
    public Inventory getInventoryById(int id) throws UnknownInventoryException {
        return inventoryDao.readById(id);
    }

    @Override
    public void deleteInventoryById(int id) throws UnknownInventoryException {
        inventoryDao.deleteById(id);
    }

    @Override
    public void updateInventory(Inventory inventory) throws UnknownInventoryException, UnknownFilmException, UnknownStoreException {
        inventoryDao.update(inventory);
    }
}
