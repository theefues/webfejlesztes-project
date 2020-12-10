package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.InventoryDto;
import inf.unideb.hu.controller.dto.InventoryRequestDto;
import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;
import inf.unideb.hu.service.InventoryService;
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
class InventoryControllerTest {

    @InjectMocks
    private InventoryController InventoryController;

    @Mock
    private InventoryService InventoryService;

    @Test
    void listInventorys() {
        when(InventoryService.getAllInventory()).thenReturn(getInventorys());

        InventoryController.listInventorys();
        verify(InventoryService, times(1)).getAllInventory();
        assertEquals(getInventoryDtos(),InventoryController.listInventorys());
    }

    @Test
    void record() throws InventoryAlreadyExistsException, UnknownStoreException, UnknownFilmException {
        InventoryController.record(getDefaultInventoryRequestDto());
        verify(InventoryService, times(1)).recordInventory(getDefaultInventory());

        doThrow(InventoryAlreadyExistsException.class).when(InventoryService).recordInventory(getDefaultInventory());
        assertThrows(ResponseStatusException.class,() -> {
            InventoryController.record(getDefaultInventoryRequestDto());
        });
    }

    @Test
    void getInventoryId() throws UnknownInventoryException {
        when(InventoryService.getInventoryById(anyInt())).thenReturn(getDefaultInventory());

        InventoryController.getInventoryId(anyInt());
        verify(InventoryService, times(1)).getInventoryById(anyInt());
        assertEquals(getDefaultInventoryDto(),InventoryController.getInventoryId(anyInt()));



        when(InventoryService.getInventoryById(anyInt())).thenThrow(UnknownInventoryException.class);
        assertThrows(ResponseStatusException.class,() -> {
            InventoryController.getInventoryId(1);
        });
    }



    @Test
    void deleteInventory() throws UnknownInventoryException {
        InventoryController.deleteInventory(anyInt());
        verify(InventoryService, times(1)).deleteInventoryById(anyInt());


        doThrow(UnknownInventoryException.class).when(InventoryService).deleteInventoryById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            InventoryController.deleteInventory(anyInt());
        });
    }

    @Test
    void update() throws UnknownInventoryException, UnknownFilmException, UnknownStoreException {
        InventoryController.update(getDefaultInventoryRequestDto());
        verify(InventoryService, times(1)).updateInventory(getDefaultInventory());

        doThrow(UnknownInventoryException.class).when(InventoryService).updateInventory(getDefaultInventory());
        assertThrows(ResponseStatusException.class,() -> {
            InventoryController.update(getDefaultInventoryRequestDto());
        });
    }

    private Inventory getDefaultInventory() {


        return new Inventory(
                1,
                1,
                1,
                new Date(2006,02,15)
        );


    }

    private InventoryDto getDefaultInventoryDto() {

        return new InventoryDto(
                1,
                1,
                1,
                new Date(2006,02,15)
        );

    }

    private InventoryRequestDto getDefaultInventoryRequestDto() {

        return new InventoryRequestDto(
                1,
                1,
                1,
                new Date(2006,02,15)
        );

    }


    private Collection<Inventory> getInventorys() {
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

    private Collection<InventoryDto> getInventoryDtos() {
        return Arrays.asList(

                new InventoryDto(
                        1,
                        1,
                        1,
                        new Date(2006,02,15)
                ),
                new InventoryDto(
                        2,
                        1,
                        1,
                        new Date(2006,02,15)
                ),
                new InventoryDto(
                        3,
                        1,
                        1,
                        new Date(2006,02,15)
                )
        );

    }
}