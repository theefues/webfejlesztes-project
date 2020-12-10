package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.CountryDaoImpl;
import inf.unideb.hu.dao.CountryRepository;
import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;
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
class CountryDaoImplTest {

    @InjectMocks
    private CountryDaoImpl dao;
    @Mock
    private CountryRepository CountryRepository;

    @Test
    void createCountry() throws CountryAlreadyExistsException, UnknownCountryException {

        when(CountryRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createCountry(getCountry());
        verify(CountryRepository, times(1)).save(getCountryEntity());


        when(CountryRepository.findById(anyInt())).thenReturn(Optional.of(getCountryEntity()));

        assertThrows(CountryAlreadyExistsException.class,() -> {
            dao.createCountry(getCountry());
        });
    }

    @Test
    void readAll() throws UnknownCountryException {
        when(CountryRepository.findAll()).thenReturn(getDefaultCountryEntities());

        assertEquals(dao.readAll(),getDefaultCountrys());
    }

    @Test
    void readById() throws UnknownCountryException {
        when(CountryRepository.findById(anyInt())).thenReturn(Optional.of(getCountryEntity()));

        assertEquals(dao.readById(1),getCountry());
    }

    @Test
    void deleteById() throws UnknownCountryException {
        when(CountryRepository.findById(anyInt())).thenReturn(Optional.of(getCountryEntity()));

        dao.deleteById(1);
        verify(CountryRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownCountryException {
        when(CountryRepository.findById(anyInt())).thenReturn(Optional.of(getCountryEntity()));
        dao.update(getCountry());
        verify(CountryRepository, times(1)).save(getCountryEntity());

    }



    private Country getCountry() {
        return new Country(
                1,
                "Afghanistan",
                new Date(2006,02,15)
        );
    }

    private CountryEntity getCountryEntity() {
        return new CountryEntity(
                1,
                "Afghanistan",
                new Date(2006,02,15)
        );
    }



    private Collection<CountryEntity> getDefaultCountryEntities() {
        return Arrays.asList(

                new CountryEntity(
                        1,
                        "Afghanistan",
                        new Date(2006,02,15)
                ),
                new CountryEntity(
                        2,
                        "Algeria",
                        new Date(2006,02,15)
                ),
                new CountryEntity(
                        3,
                        "American Samoa",
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<Country> getDefaultCountrys() {
        return Arrays.asList(

                new Country(
                        1,
                        "Afghanistan",
                        new Date(2006,02,15)
                ),
                new Country(
                        2,
                        "Algeria",
                        new Date(2006,02,15)
                ),
                new Country(
                        3,
                        "American Samoa",
                        new Date(2006,02,15)
                )
        );

    }
}