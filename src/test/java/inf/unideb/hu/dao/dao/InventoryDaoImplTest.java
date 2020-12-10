package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.InventoryDaoImpl;
import inf.unideb.hu.dao.InventoryRepository;
import inf.unideb.hu.dao.entity.*;
import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;
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
class InventoryDaoImplTest {

    @InjectMocks
    private InventoryDaoImpl dao;
    @Mock
    private InventoryRepository InventoryRepository;

    @Test
    void createInventory() throws InventoryAlreadyExistsException, UnknownInventoryException, UnknownFilmException, UnknownStoreException {

        when(InventoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createInventory(getInventory());
        verify(InventoryRepository, times(1)).save(getInventoryEntity());


        when(InventoryRepository.findById(anyInt())).thenReturn(Optional.of(getInventoryEntity()));

        assertThrows(InventoryAlreadyExistsException.class,() -> {
            dao.createInventory(getInventory());
        });
    }

    @Test
    void readAll() throws UnknownInventoryException {
        when(InventoryRepository.findAll()).thenReturn(getDefaultInventoryEntities());

        assertEquals(dao.readAll(),getDefaultInventorys());
    }

    @Test
    void readById() throws UnknownInventoryException {
        when(InventoryRepository.findById(anyInt())).thenReturn(Optional.of(getInventoryEntity()));

        assertEquals(dao.readById(1),getInventory());
    }

    @Test
    void deleteById() throws UnknownInventoryException {
        when(InventoryRepository.findById(anyInt())).thenReturn(Optional.of(getInventoryEntity()));

        dao.deleteById(1);
        verify(InventoryRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownInventoryException, UnknownFilmException, UnknownStoreException {
        when(InventoryRepository.findById(anyInt())).thenReturn(Optional.of(getInventoryEntity()));
        dao.update(getInventory());
        verify(InventoryRepository, times(1)).save(getInventoryEntity());

    }



    private Inventory getInventory() {
        return new Inventory(

                1,
                1,
                1,
                new Date(2006,02,15)
        );
    }

    private InventoryEntity getInventoryEntity() {
        return new InventoryEntity(

                1,
                new FilmEntity(
                        1,
                        "ACADEMY DINOSAUR",
                        "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                        2006,
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        6,
                        0.99,
                        86,
                        20.99,
                        "PG",
                        "Deleted Scenes,Behind the Scenes",
                        new Date(2006,02,15)
                ),
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
                new Date(2006,02,15)
        );
    }



    private Collection<InventoryEntity> getDefaultInventoryEntities() {
        return Arrays.asList(

                new InventoryEntity(

                        1,
                        new FilmEntity(
                                1,
                                "ACADEMY DINOSAUR",
                                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                                2006,
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                6,
                                0.99,
                                86,
                                20.99,
                                "PG",
                                "Deleted Scenes,Behind the Scenes",
                                new Date(2006,02,15)
                        ),
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
                        new Date(2006,02,15)
                ),
                new InventoryEntity(
                        2,
                        new FilmEntity(
                                1,
                                "ACADEMY DINOSAUR",
                                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                                2006,
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                6,
                                0.99,
                                86,
                                20.99,
                                "PG",
                                "Deleted Scenes,Behind the Scenes",
                                new Date(2006,02,15)
                        ),
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
                        new Date(2006,02,15)
                ),
                new InventoryEntity(
                        3,
                        new FilmEntity(
                                1,
                                "ACADEMY DINOSAUR",
                                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                                2006,
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                new LanguageEntity(
                                        1,
                                        "English",
                                        new Date(2006,02,15)
                                ),
                                6,
                                0.99,
                                86,
                                20.99,
                                "PG",
                                "Deleted Scenes,Behind the Scenes",
                                new Date(2006,02,15)
                        ),
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
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<Inventory> getDefaultInventorys() {
        return Arrays.asList(

                new Inventory(

                        1,
                        1,
                        1,
                        new Date(2006,02,15)
                ),
                new Inventory(
                        2,
                        1,
                        1,
                        new Date(2006,02,15)
                ),
                new Inventory(
                        3,
                        1,
                        1,
                        new Date(2006,02,15)
                )
        );

    }
}