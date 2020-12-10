package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.CityDaoImpl;
import inf.unideb.hu.dao.CityRepository;
import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityDaoImplTest {

    @InjectMocks
    private CityDaoImpl dao;
    @Mock
    private CityRepository CityRepository;

    @Test
    void createCity() throws CityAlreadyExistsException, UnknownCityException, UnknownCountryException {

        when(CityRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createCity(getCity());
        verify(CityRepository, times(1)).save(getCityEntity());


        when(CityRepository.findById(anyInt())).thenReturn(Optional.of(getCityEntity()));

        assertThrows(CityAlreadyExistsException.class,() -> {
            dao.createCity(getCity());
        });
    }

    @Test
    void readAll() throws UnknownCityException {
        when(CityRepository.findAll()).thenReturn(getDefaultCityEntities());

        assertEquals(dao.readAll(),getDefaultCitys());
    }

    @Test
    void readById() throws UnknownCityException {
        when(CityRepository.findById(anyInt())).thenReturn(Optional.of(getCityEntity()));

        assertEquals(dao.readById(1),getCity());
    }

    @Test
    void deleteById() throws UnknownCityException {
        when(CityRepository.findById(anyInt())).thenReturn(Optional.of(getCityEntity()));

        dao.deleteById(1);
        verify(CityRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownCityException, UnknownCountryException {
        when(CityRepository.findById(anyInt())).thenReturn(Optional.of(getCityEntity()));
        dao.update(getCity());
        verify(CityRepository, times(1)).save(getCityEntity());

    }



    private City getCity() {
        return new City(
                1,
                "A Corua (La Corua)",
                1,
                new Date(2006,02,15)
        );
    }

    private CityEntity getCityEntity() {
        return new CityEntity(
                1,
                "A Corua (La Corua)",
                new CountryEntity(
                        1,
                        "Afghanistan",
                        new Date(2006,02,15)
                ),
                new Date(2006,02,15)

        );
    }



    private Collection<CityEntity> getDefaultCityEntities() {
        return Arrays.asList(

                new CityEntity(
                        1,
                        "A Corua (La Corua)",
                        new CountryEntity(
                                1,
                                "Afghanistan",
                                new Date(2006,02,15)
                        ),
                        new Date(2006,02,15)

                ),
                new CityEntity(
                        2,
                        "Abha",
                        new CountryEntity(
                                2,
                                "Algeria",
                                new Date(2006,02,15)
                        ),
                        new Date(2006,02,15)
                ),
                new CityEntity(
                        3,
                        "Abu Dhabi",
                        new CountryEntity(
                                3,
                                "American Samoa",
                                new Date(2006,02,15)
                        ),
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<City> getDefaultCitys() {
        return Arrays.asList(

                new City(
                        1,
                        "A Corua (La Corua)",
                        1,
                        new Date(2006,02,15)
                ),
                new City(
                        2,
                        "Abha",
                        2,
                        new Date(2006,02,15)
                ),
                new City(
                        3,
                        "Abu Dhabi",
                        3,
                        new Date(2006,02,15)
                )
        );

    }
}