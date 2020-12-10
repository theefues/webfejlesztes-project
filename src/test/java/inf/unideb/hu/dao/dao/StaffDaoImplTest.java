package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.StaffDaoImpl;
import inf.unideb.hu.dao.StaffRepository;
import inf.unideb.hu.dao.entity.*;
import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;
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
class StaffDaoImplTest {

    @InjectMocks
    private StaffDaoImpl dao;
    @Mock
    private StaffRepository StaffRepository;

    @Test
    void createStaff() throws StaffAlreadyExistsException, UnknownStaffException, UnknownAddressException, UnknownStoreException {

        when(StaffRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createStaff(getStaff());
        verify(StaffRepository, times(1)).save(getStaffEntity());


        when(StaffRepository.findById(anyInt())).thenReturn(Optional.of(getStaffEntity()));

        assertThrows(StaffAlreadyExistsException.class,() -> {
            dao.createStaff(getStaff());
        });
    }

    @Test
    void readAll() throws UnknownStaffException {
        when(StaffRepository.findAll()).thenReturn(getDefaultStaffEntities());

        assertEquals(dao.readAll(),getDefaultStaffs());
    }

    @Test
    void readById() throws UnknownStaffException {
        when(StaffRepository.findById(anyInt())).thenReturn(Optional.of(getStaffEntity()));

        assertEquals(dao.readById(1),getStaff());
    }

    @Test
    void deleteById() throws UnknownStaffException {
        when(StaffRepository.findById(anyInt())).thenReturn(Optional.of(getStaffEntity()));

        dao.deleteById(1);
        verify(StaffRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        when(StaffRepository.findById(anyInt())).thenReturn(Optional.of(getStaffEntity()));
        dao.update(getStaff());
        verify(StaffRepository, times(1)).save(getStaffEntity());

    }



    private Staff getStaff() {
        return new Staff(
                1,
                "Mike",
                "Hillyer",
                3,
                "Mike.Hillyer@sakilastaff.com",
                1,
                1,
                "Mike",
                "8cb2237d0679ca88db6464eac60da96345513964",
                new Date(2006,02,15)
        );
    }

    private StaffEntity getStaffEntity() {
        return new StaffEntity(
                1,
                "Mike",
                "Hillyer",
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
                ),
                "Mike.Hillyer@sakilastaff.com",
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
                1,
                "Mike",
                "8cb2237d0679ca88db6464eac60da96345513964",
                new Date(2006,02,15)
        );
    }



    private Collection<StaffEntity> getDefaultStaffEntities() {
        return Arrays.asList(

                new StaffEntity(
                        1,
                        "Mike",
                        "Hillyer",
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
                        ),
                        "Mike.Hillyer@sakilastaff.com",
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
                        1,
                        "Mike",
                        "8cb2237d0679ca88db6464eac60da96345513964",
                        new Date(2006,02,15)
                ),
                new StaffEntity(
                        2,
                        "Jon",
                        "Stephens",
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
                        ),
                        "Jon.Stephens@sakilastaff.com",
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
                        1,
                        "Jon",
                        "null",
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<Staff> getDefaultStaffs() {
        return Arrays.asList(

                new Staff(
                        1,
                        "Mike",
                        "Hillyer",
                        3,
                        "Mike.Hillyer@sakilastaff.com",
                        1,
                        1,
                        "Mike",
                        "8cb2237d0679ca88db6464eac60da96345513964",
                        new Date(2006,02,15)
                ),
                new Staff(
                        2,
                        "Jon",
                        "Stephens",
                        3,
                        "Jon.Stephens@sakilastaff.com",
                        1,
                        1,
                        "Jon",
                        "null",
                        new Date(2006,02,15)
                )
        );

    }
}