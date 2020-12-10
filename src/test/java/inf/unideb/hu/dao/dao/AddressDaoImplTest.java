package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.AddressDaoImpl;
import inf.unideb.hu.dao.AddressRepository;
import inf.unideb.hu.dao.entity.AddressEntity;
import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;
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
class AddressDaoImplTest {

    @InjectMocks
    private AddressDaoImpl dao;
    @Mock
    private AddressRepository AddressRepository;

    @Test
    void createAddress() throws AddressAlreadyExistsException, UnknownCityException {

        when(AddressRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createAddress(getAddress());
        verify(AddressRepository, times(1)).save(getAddressEntity());


        when(AddressRepository.findById(anyInt())).thenReturn(Optional.of(getAddressEntity()));

        assertThrows(AddressAlreadyExistsException.class,() -> {
            dao.createAddress(getAddress());
        });
    }

    @Test
    void readAll() throws UnknownAddressException {
        when(AddressRepository.findAll()).thenReturn(getDefaultAddressEntities());

        assertEquals(dao.readAll(),getDefaultAddresss());
    }

    @Test
    void readById() throws UnknownAddressException {
        when(AddressRepository.findById(anyInt())).thenReturn(Optional.of(getAddressEntity()));

        assertEquals(dao.readById(1),getAddress());
    }

    @Test
    void deleteById() throws UnknownAddressException {
        when(AddressRepository.findById(anyInt())).thenReturn(Optional.of(getAddressEntity()));

        dao.deleteById(1);
        verify(AddressRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownAddressException, UnknownCityException {
        when(AddressRepository.findById(anyInt())).thenReturn(Optional.of(getAddressEntity()));
        dao.update(getAddress());
        verify(AddressRepository, times(1)).save(getAddressEntity());

    }



    private Address getAddress() {
        return new Address(
                1,
                "47 MySakila Drive",
                null,
                "Alberta",
                1,
                "",
                "",
                new Date(2014,9,25)
        );
    }

    private AddressEntity getAddressEntity() {
        return new AddressEntity(
                1,
                "47 MySakila Drive",
                null,
                "Alberta",
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
                "",
                "",
                new Date(2014,9,25)
        );
    }



    private Collection<AddressEntity> getDefaultAddressEntities() {
        return Arrays.asList(

                new AddressEntity(
                        1,
                        "47 MySakila Drive",
                        null,
                        "Alberta",
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
                        "",
                        "",
                        new Date(2014,9,25)

                ),
                new AddressEntity(
                        2,
                        "28 MySQL Boulevard",
                        null,
                        "QLD",
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
                        "",
                        "",
                        new Date(2014,9,25)
                ),
                new AddressEntity(
                        3,
                        "23 Workhaven Lane",
                        null,
                        "Alberta",
                        new CityEntity(
                                3,
                                "Abu Dhabi",
                                new CountryEntity(
                                        3,
                                        "American Samoa",
                                        new Date(2006,02,15)
                                ),
                                new Date(2006,02,15)
                        ),
                        "",
                        "14033335568",
                        new Date(2014,9,25)
                )
        );

    }


    private Collection<Address> getDefaultAddresss() {
        return Arrays.asList(

                new Address(
                        1,
                        "47 MySakila Drive",
                        null,
                        "Alberta",
                        1,
                        "",
                        "",
                        new Date(2014,9,25)
                ),
                new Address(
                        2,
                        "28 MySQL Boulevard",
                        null,
                        "QLD",
                        2,
                        "",
                        "",
                        new Date(2014,9,25)
                ),
                new Address(
                        3,
                        "23 Workhaven Lane",
                        null,
                        "Alberta",
                        3,
                        "",
                        "14033335568",
                        new Date(2014,9,25)
                )
        );

    }
}