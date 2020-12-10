package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.StaffDto;
import inf.unideb.hu.controller.dto.StaffRequestDto;
import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;
import inf.unideb.hu.service.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StaffControllerTest {

    @InjectMocks
    private StaffController StaffController;

    @Mock
    private StaffService StaffService;

    @Test
    void listStaffs() {
        when(StaffService.getAllStaff()).thenReturn(getStaffs());

        StaffController.listStaffs();
        verify(StaffService, times(1)).getAllStaff();
        assertEquals(getStaffDtos(),StaffController.listStaffs());
    }

    @Test
    void record() throws StaffAlreadyExistsException, UnknownAddressException, UnknownStoreException {
        StaffController.record(getDefaultStaffRequestDto());
        verify(StaffService, times(1)).recordStaff(getDefaultStaff());

        doThrow(StaffAlreadyExistsException.class).when(StaffService).recordStaff(getDefaultStaff());
        assertThrows(ResponseStatusException.class,() -> {
            StaffController.record(getDefaultStaffRequestDto());
        });
    }

    @Test
    void getStaffId() throws UnknownStaffException {
        when(StaffService.getStaffById(anyInt())).thenReturn(getDefaultStaff());

        StaffController.getStaffId(anyInt());
        verify(StaffService, times(1)).getStaffById(anyInt());
        assertEquals(getDefaultStaffDto(),StaffController.getStaffId(anyInt()));



        when(StaffService.getStaffById(anyInt())).thenThrow(UnknownStaffException.class);
        assertThrows(ResponseStatusException.class,() -> {
            StaffController.getStaffId(1);
        });
    }



    @Test
    void deleteStaff() throws UnknownStaffException {
        StaffController.deleteStaff(anyInt());
        verify(StaffService, times(1)).deleteStaffById(anyInt());


        doThrow(UnknownStaffException.class).when(StaffService).deleteStaffById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            StaffController.deleteStaff(anyInt());
        });
    }

    @Test
    void update() throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        StaffController.update(getDefaultStaffRequestDto());
        verify(StaffService, times(1)).updateStaff(getDefaultStaff());

        doThrow(UnknownStaffException.class).when(StaffService).updateStaff(getDefaultStaff());
        assertThrows(ResponseStatusException.class,() -> {
            StaffController.update(getDefaultStaffRequestDto());
        });
    }

    private Staff getDefaultStaff() {


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

    private StaffDto getDefaultStaffDto() {

        return new StaffDto(
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

    private StaffRequestDto getDefaultStaffRequestDto() {

        return new StaffRequestDto(
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


    private Collection<Staff> getStaffs() {
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
    4,
    "Jon.Stephens@sakilastaff.com",
    2,
    1,
    "Jon",
    "null",
                new Date(2006,02,15)
                )
                
        );

    }

    private Collection<StaffDto> getStaffDtos() {
        return Arrays.asList(

                new StaffDto(
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
                new StaffDto(
                            2,
    "Jon",
    "Stephens",
    4,
    "Jon.Stephens@sakilastaff.com",
    2,
    1,
    "Jon",
    "null",
                new Date(2006,02,15)
                )
        );

    }
}