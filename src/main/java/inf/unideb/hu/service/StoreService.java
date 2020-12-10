package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;

import java.util.Collection;

public interface StoreService {

    Collection<Store> getAllStore();
    void recordStore(Store Store) throws StoreAlreadyExistsException, UnknownAddressException;
    void deleteStoreById(int id) throws UnknownStoreException;
    void updateStore(Store Store) throws UnknownStoreException, UnknownAddressException;
    Store getStoreById(int id) throws UnknownStoreException;
}
