package inf.unideb.hu.service;

import inf.unideb.hu.dao.CityDao;
import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityDao CityDao;

    @Override
    public Collection<City> getAllCity() {
        return CityDao.readAll();
    }

    @Override
    public void recordCity(City City) throws CityAlreadyExistsException, UnknownCountryException {
        CityDao.createCity(City);
    }

    @Override
    public City getCityById(int id) throws UnknownCityException {
        return CityDao.readById(id);
    }

    @Override
    public void deleteCityById(int id) throws UnknownCityException {
        CityDao.deleteById(id);
    }

    @Override
    public void updateCity(City City) throws UnknownCityException, UnknownCountryException {
        CityDao.update(City);
    }
}
