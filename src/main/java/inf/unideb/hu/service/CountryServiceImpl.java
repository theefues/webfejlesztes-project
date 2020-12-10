package inf.unideb.hu.service;

import inf.unideb.hu.dao.CountryDao;
import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{

    private final CountryDao CountryDao;

    @Override
    public Collection<Country> getAllCountry() {
        return CountryDao.readAll();
    }

    @Override
    public void recordCountry(Country Country) throws CountryAlreadyExistsException {
        CountryDao.createCountry(Country);
    }

    @Override
    public Country getCountryById(int id) throws UnknownCountryException {
        return CountryDao.readById(id);
    }

    @Override
    public void deleteCountryById(int id) throws UnknownCountryException {
        CountryDao.deleteById(id);
    }

    @Override
    public void updateCountry(Country Country) throws UnknownCountryException {
        CountryDao.update(Country);
    }
}
