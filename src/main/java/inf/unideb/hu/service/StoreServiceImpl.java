package inf.unideb.hu.service;

import inf.unideb.hu.dao.StoreDao;
import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreDao StoreDao;

    @Override
    public Collection<Store> getAllStore() {
        return StoreDao.readAll();
    }

    @Override
    public void recordStore(Store Store) throws StoreAlreadyExistsException, UnknownAddressException {
        StoreDao.createStore(Store);
    }

    @Override
    public Store getStoreById(int id) throws UnknownStoreException {
        return StoreDao.readById(id);
    }

    @Override
    public void deleteStoreById(int id) throws UnknownStoreException {
        StoreDao.deleteById(id);
    }

    @Override
    public void updateStore(Store Store) throws UnknownStoreException, UnknownAddressException {
        StoreDao.update(Store);
    }
}
