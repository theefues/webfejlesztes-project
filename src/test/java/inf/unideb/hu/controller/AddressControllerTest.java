package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.AddressDto;
import inf.unideb.hu.controller.dto.AddressRequestDto;
import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;
import inf.unideb.hu.service.AddressService;
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
class AddressControllerTest {

    @InjectMocks
    private AddressController AddressController;

    @Mock
    private AddressService AddressService;

    @Test
    void listAddresss() {
        when(AddressService.getAllAddress()).thenReturn(getAddresss());

        AddressController.listAddresss();
        verify(AddressService, times(1)).getAllAddress();
        assertEquals(getAddressDtos(),AddressController.listAddresss());
    }

    @Test
    void record() throws AddressAlreadyExistsException, UnknownCityException {
        AddressController.record(getDefaultAddressRequestDto());
        verify(AddressService, times(1)).recordAddress(getDefaultAddress());

        doThrow(AddressAlreadyExistsException.class).when(AddressService).recordAddress(getDefaultAddress());
        assertThrows(ResponseStatusException.class,() -> {
            AddressController.record(getDefaultAddressRequestDto());
        });
    }

    @Test
    void getAddressId() throws UnknownAddressException {
        when(AddressService.getAddressById(anyInt())).thenReturn(getDefaultAddress());

        AddressController.getAddressId(anyInt());
        verify(AddressService, times(1)).getAddressById(anyInt());
        assertEquals(getDefaultAddressDto(),AddressController.getAddressId(anyInt()));



        when(AddressService.getAddressById(anyInt())).thenThrow(UnknownAddressException.class);
        assertThrows(ResponseStatusException.class,() -> {
            AddressController.getAddressId(1);
        });
    }



    @Test
    void deleteAddress() throws UnknownAddressException {
        AddressController.deleteAddress(anyInt());
        verify(AddressService, times(1)).deleteAddressById(anyInt());


        doThrow(UnknownAddressException.class).when(AddressService).deleteAddressById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            AddressController.deleteAddress(anyInt());
        });
    }

    @Test
    void update() throws UnknownAddressException, UnknownCityException {
        AddressController.update(getDefaultAddressRequestDto());
        verify(AddressService, times(1)).updateAddress(getDefaultAddress());

        doThrow(UnknownAddressException.class).when(AddressService).updateAddress(getDefaultAddress());
        assertThrows(ResponseStatusException.class,() -> {
            AddressController.update(getDefaultAddressRequestDto());
        });
    }

    private Address getDefaultAddress() {


        return new Address(
                1,
                "47 MySakila Drive",
                null,
                "Alberta",
                300,
                "",
                "",
                new Date(2014,9,25)
        );

    }

    private AddressDto getDefaultAddressDto() {

        return new AddressDto(
                1,
                "47 MySakila Drive",
                null,
                "Alberta",
                300,
                "",
                "",
                new Date(2014,9,25)
        );

    }

    private AddressRequestDto getDefaultAddressRequestDto() {

        return new AddressRequestDto(
                1,
                "47 MySakila Drive",
                null,
                "Alberta",
                300,
                "",
                "",
                new Date(2014,9,25)
        );

    }


    private Collection<Address> getAddresss() {
        return Arrays.asList(

                new Address(
                        1,
                        "47 MySakila Drive",
                        null,
                        "Alberta",
                        300,
                        "",
                        "",
                        new Date(2014,9,25)
                ),
        new Address(
                2,
                "28 MySQL Boulevard",
                null,
                "QLD",
                576,
                "",
                "",
                new Date(2014,9,25)
        ),
        new Address(
                3,
                "23 Workhaven Lane",
                null,
                "Alberta",
                300,
                "",
                "14033335568",
                new Date(2014,9,25)
        )
        );

    }

    private Collection<AddressDto> getAddressDtos() {
        return Arrays.asList(

                new AddressDto(
                        1,
                        "47 MySakila Drive",
                        null,
                        "Alberta",
                        300,
                        "",
                        "",
                        new Date(2014,9,25)
                ),
                new AddressDto(
                        2,
                        "28 MySQL Boulevard",
                        null,
                        "QLD",
                        576,
                        "",
                        "",
                        new Date(2014,9,25)
                ),
                new AddressDto(
                        3,
                        "23 Workhaven Lane",
                        null,
                        "Alberta",
                        300,
                        "",
                        "14033335568",
                        new Date(2014,9,25)
                )

        );

    }
}