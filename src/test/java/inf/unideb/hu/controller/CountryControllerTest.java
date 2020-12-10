package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.CountryDto;
import inf.unideb.hu.controller.dto.CountryRequestDto;
import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;
import inf.unideb.hu.service.CountryService;
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
class CountryControllerTest {

    @InjectMocks
    private CountryController CountryController;

    @Mock
    private CountryService CountryService;

    @Test
    void listCountrys() {
        when(CountryService.getAllCountry()).thenReturn(getCountrys());

        CountryController.listCountrys();
        verify(CountryService, times(1)).getAllCountry();
        assertEquals(getCountryDtos(),CountryController.listCountrys());
    }

    @Test
    void record() throws CountryAlreadyExistsException {
        CountryController.record(getDefaultCountryRequestDto());
        verify(CountryService, times(1)).recordCountry(getDefaultCountry());

        doThrow(CountryAlreadyExistsException.class).when(CountryService).recordCountry(getDefaultCountry());
        assertThrows(ResponseStatusException.class,() -> {
            CountryController.record(getDefaultCountryRequestDto());
        });
    }

    @Test
    void getCountryId() throws UnknownCountryException {
        when(CountryService.getCountryById(anyInt())).thenReturn(getDefaultCountry());

        CountryController.getCountryId(anyInt());
        verify(CountryService, times(1)).getCountryById(anyInt());
        assertEquals(getDefaultCountryDto(),CountryController.getCountryId(anyInt()));



        when(CountryService.getCountryById(anyInt())).thenThrow(UnknownCountryException.class);
        assertThrows(ResponseStatusException.class,() -> {
            CountryController.getCountryId(1);
        });
    }



    @Test
    void deleteCountry() throws UnknownCountryException {
        CountryController.deleteCountry(anyInt());
        verify(CountryService, times(1)).deleteCountryById(anyInt());


        doThrow(UnknownCountryException.class).when(CountryService).deleteCountryById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            CountryController.deleteCountry(anyInt());
        });
    }

    @Test
    void update() throws UnknownCountryException {
        CountryController.update(getDefaultCountryRequestDto());
        verify(CountryService, times(1)).updateCountry(getDefaultCountry());

        doThrow(UnknownCountryException.class).when(CountryService).updateCountry(getDefaultCountry());
        assertThrows(ResponseStatusException.class,() -> {
            CountryController.update(getDefaultCountryRequestDto());
        });
    }

    private Country getDefaultCountry() {

        return new Country(
                1,
                "Afghanistan",
                new Date(2006,02,15)
        );
    }

    private CountryDto getDefaultCountryDto() {

        return new CountryDto(
                1,
                "Afghanistan",
                new Date(2006,02,15)
        );

    }

    private CountryRequestDto getDefaultCountryRequestDto() {

        return new CountryRequestDto(
                1,
                "Afghanistan",
                new Date(2006,02,15)
        );

    }


    private Collection<Country> getCountrys() {
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

    private Collection<CountryDto> getCountryDtos() {
        return Arrays.asList(

                new CountryDto(
                        1,
                        "Afghanistan",
                        new Date(2006,02,15)
                ),
                new CountryDto(
                        2,
                        "Algeria",
                        new Date(2006,02,15)
                ),
                new CountryDto(
                        3,
                        "American Samoa",
                        new Date(2006,02,15)
                )
        );

    }
}