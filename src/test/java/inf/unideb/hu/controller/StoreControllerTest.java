package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.StoreDto;
import inf.unideb.hu.controller.dto.StoreRequestDto;
import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;
import inf.unideb.hu.service.StoreService;
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
class StoreControllerTest {

    @InjectMocks
    private StoreController StoreController;

    @Mock
    private StoreService StoreService;

    @Test
    void listStores() {
        when(StoreService.getAllStore()).thenReturn(getStores());

        StoreController.listStores();
        verify(StoreService, times(1)).getAllStore();
        assertEquals(getStoreDtos(),StoreController.listStores());
    }

    @Test
    void record() throws StoreAlreadyExistsException, UnknownAddressException {
        StoreController.record(getDefaultStoreRequestDto());
        verify(StoreService, times(1)).recordStore(getDefaultStore());

        doThrow(StoreAlreadyExistsException.class).when(StoreService).recordStore(getDefaultStore());
        assertThrows(ResponseStatusException.class,() -> {
            StoreController.record(getDefaultStoreRequestDto());
        });
    }

    @Test
    void getStoreId() throws UnknownStoreException {
        when(StoreService.getStoreById(anyInt())).thenReturn(getDefaultStore());

        StoreController.getStoreId(anyInt());
        verify(StoreService, times(1)).getStoreById(anyInt());
        assertEquals(getDefaultStoreDto(),StoreController.getStoreId(anyInt()));



        when(StoreService.getStoreById(anyInt())).thenThrow(UnknownStoreException.class);
        assertThrows(ResponseStatusException.class,() -> {
            StoreController.getStoreId(1);
        });
    }



    @Test
    void deleteStore() throws UnknownStoreException {
        StoreController.deleteStore(anyInt());
        verify(StoreService, times(1)).deleteStoreById(anyInt());


        doThrow(UnknownStoreException.class).when(StoreService).deleteStoreById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            StoreController.deleteStore(anyInt());
        });
    }

    @Test
    void update() throws UnknownStoreException, UnknownAddressException {
        StoreController.update(getDefaultStoreRequestDto());
        verify(StoreService, times(1)).updateStore(getDefaultStore());

        doThrow(UnknownStoreException.class).when(StoreService).updateStore(getDefaultStore());
        assertThrows(ResponseStatusException.class,() -> {
            StoreController.update(getDefaultStoreRequestDto());
        });
    }

    private Store getDefaultStore() {


        return new Store(
                1,
                1,
                1,
                new Date(2006,02,15)
        );


    }

    private StoreDto getDefaultStoreDto() {

        return new StoreDto(
                1,
                1,
                1,
                new Date(2006,02,15)
        );

    }

    private StoreRequestDto getDefaultStoreRequestDto() {

        return new StoreRequestDto(
                1,
                1,
                1,
                new Date(2006,02,15)
        );

    }


    private Collection<Store> getStores() {
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

    private Collection<StoreDto> getStoreDtos() {
        return Arrays.asList(

                new StoreDto(
                1,
                1,
                1,
                new Date(2006,02,15)
                ),
                new StoreDto(
                2,
                2,
                2,
                new Date(2006,02,15)
                )
        );

    }
}