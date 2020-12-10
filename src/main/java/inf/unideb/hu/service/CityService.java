package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;

import java.util.Collection;

public interface CityService {

    Collection<City> getAllCity();
    void recordCity(City City) throws CityAlreadyExistsException, UnknownCountryException;
    void deleteCityById(int id) throws UnknownCityException;
    void updateCity(City City) throws UnknownCityException, UnknownCountryException;
    City getCityById(int id) throws UnknownCityException;
}
