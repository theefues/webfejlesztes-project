package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;

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
public interface AddressDao {

    void createAddress(Address address) throws AddressAlreadyExistsException, UnknownCityException;
    Collection<Address> readAll();
    Address readById(int id) throws UnknownAddressException;
    void deleteById(int id) throws UnknownAddressException;
    void update(Address Address) throws UnknownAddressException, UnknownCityException;
}
