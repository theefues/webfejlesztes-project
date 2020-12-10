package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;

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
public interface CityDao {

    void createCity(City City) throws CityAlreadyExistsException, UnknownCountryException;
    Collection<City> readAll();
    City readById(int id) throws UnknownCityException;
    void deleteById(int id) throws UnknownCityException;
    void update(City City) throws UnknownCityException, UnknownCountryException;
}
