package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.StoreDaoImpl;
import inf.unideb.hu.dao.StoreRepository;
import inf.unideb.hu.dao.entity.AddressEntity;
import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.dao.entity.StoreEntity;
import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Address;
import inf.unideb.hu.model.Store;
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
class StoreDaoImplTest {

    @InjectMocks
    private StoreDaoImpl dao;
    @Mock
    private StoreRepository StoreRepository;

    @Test
    void createStore() throws StoreAlreadyExistsException, UnknownStoreException, UnknownAddressException {

        when(StoreRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createStore(getStore());
        verify(StoreRepository, times(1)).save(getStoreEntity());


        when(StoreRepository.findById(anyInt())).thenReturn(Optional.of(getStoreEntity()));

        assertThrows(StoreAlreadyExistsException.class,() -> {
            dao.createStore(getStore());
        });
    }

    @Test
    void readAll() throws UnknownStoreException {
        when(StoreRepository.findAll()).thenReturn(getDefaultStoreEntities());

        assertEquals(dao.readAll(),getDefaultStores());
    }

    @Test
    void readById() throws UnknownStoreException {
        when(StoreRepository.findById(anyInt())).thenReturn(Optional.of(getStoreEntity()));

        assertEquals(dao.readById(1),getStore());
    }

    @Test
    void deleteById() throws UnknownStoreException {
        when(StoreRepository.findById(anyInt())).thenReturn(Optional.of(getStoreEntity()));

        dao.deleteById(1);
        verify(StoreRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownStoreException, UnknownAddressException {
        when(StoreRepository.findById(anyInt())).thenReturn(Optional.of(getStoreEntity()));
        dao.update(getStore());
        verify(StoreRepository, times(1)).save(getStoreEntity());

    }



    private Store getStore() {
        return new Store(
                1,
                1,
                1,
                new Date(2006,02,15)
        );
    }

    private StoreEntity getStoreEntity() {
        return new StoreEntity(
                1,
                1,
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
                new Date(2006,02,15)
        );
    }



    private Collection<StoreEntity> getDefaultStoreEntities() {
        return Arrays.asList(

                new StoreEntity(
                        1,
                        1,
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
                        new Date(2006,02,15)
                ),
                new StoreEntity(
                        2,
                        2,
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
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<Store> getDefaultStores() {
        return Arrays.asList(

                new Store(
                        1,
                        1,
                        1,
                        new Date(2006,02,15)
                ),
                new Store(
                        2,
                        2,
                        2,
                        new Date(2006,02,15)
                )
        );

    }
}