package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.CityDto;
import inf.unideb.hu.controller.dto.CityRequestDto;
import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;
import inf.unideb.hu.service.CityService;
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
class CityControllerTest {

    @InjectMocks
    private CityController CityController;

    @Mock
    private CityService CityService;

    @Test
    void listCitys() {
        when(CityService.getAllCity()).thenReturn(getCitys());

        CityController.listCitys();
        verify(CityService, times(1)).getAllCity();
        assertEquals(getCityDtos(),CityController.listCitys());
    }

    @Test
    void record() throws CityAlreadyExistsException, UnknownCountryException {
        CityController.record(getDefaultCityRequestDto());
        verify(CityService, times(1)).recordCity(getDefaultCity());

        doThrow(CityAlreadyExistsException.class).when(CityService).recordCity(getDefaultCity());
        assertThrows(ResponseStatusException.class,() -> {
            CityController.record(getDefaultCityRequestDto());
        });
    }

    @Test
    void getCityId() throws UnknownCityException {
        when(CityService.getCityById(anyInt())).thenReturn(getDefaultCity());

        CityController.getCityId(anyInt());
        verify(CityService, times(1)).getCityById(anyInt());
        assertEquals(getDefaultCityDto(),CityController.getCityId(anyInt()));



        when(CityService.getCityById(anyInt())).thenThrow(UnknownCityException.class);
        assertThrows(ResponseStatusException.class,() -> {
            CityController.getCityId(1);
        });
    }



    @Test
    void deleteCity() throws UnknownCityException {
        CityController.deleteCity(anyInt());
        verify(CityService, times(1)).deleteCityById(anyInt());


        doThrow(UnknownCityException.class).when(CityService).deleteCityById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            CityController.deleteCity(anyInt());
        });
    }

    @Test
    void update() throws UnknownCityException, UnknownCountryException {
        CityController.update(getDefaultCityRequestDto());
        verify(CityService, times(1)).updateCity(getDefaultCity());

        doThrow(UnknownCityException.class).when(CityService).updateCity(getDefaultCity());
        assertThrows(ResponseStatusException.class,() -> {
            CityController.update(getDefaultCityRequestDto());
        });
    }

    private City getDefaultCity() {
        return new City(
                1,
                "A Corua (La Corua)",
                87,
                new Date(2006,02,15)
        );
    }

    private CityDto getDefaultCityDto() {

        return new CityDto(
                1,
                "A Corua (La Corua)",
                87,
                new Date(2006,02,15)
        );

    }

    private CityRequestDto getDefaultCityRequestDto() {

        return new CityRequestDto(
                1,
                "A Corua (La Corua)",
                87,
                new Date(2006,02,15)
        );

    }


    private Collection<City> getCitys() {
        return Arrays.asList(

                new City(
                        1,
                        "A Corua (La Corua)",
                        87,
                        new Date(2006,02,15)
                ),
                new City(
                        2,
                        "Abha",
                        82,
                        new Date(2006,02,15)
                ),
                new City(
                        3,
                        "Abu Dhabi",
                        101,
                        new Date(2006,02,15)
                )
        );

    }

    private Collection<CityDto> getCityDtos() {
        return Arrays.asList(

                new CityDto(
                        1,
                        "A Corua (La Corua)",
                        87,
                        new Date(2006,02,15)

                ),
                new CityDto(
                        2,
                        "Abha",
                        82,
                        new Date(2006,02,15)
                ),
                new CityDto(
                        3,
                        "Abu Dhabi",
                        101,
                        new Date(2006,02,15)
                )
        );

    }
}