package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;

import java.util.Collection;

public interface CountryService {

    Collection<Country> getAllCountry();
    void recordCountry(Country Country) throws CountryAlreadyExistsException;
    void deleteCountryById(int id) throws UnknownCountryException;
    void updateCountry(Country Country) throws UnknownCountryException;
    Country getCountryById(int id) throws UnknownCountryException;
}
