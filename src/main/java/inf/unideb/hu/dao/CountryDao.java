package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;

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
public interface CountryDao {

    void createCountry(Country Country) throws CountryAlreadyExistsException;
    Collection<Country> readAll();
    Country readById(int id) throws UnknownCountryException;
    void deleteById(int id) throws UnknownCountryException;
    void update(Country Country) throws UnknownCountryException;
}
