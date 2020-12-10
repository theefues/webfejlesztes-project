package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.InventoryDto;
import inf.unideb.hu.controller.dto.InventoryRequestDto;
import inf.unideb.hu.exceptions.InventoryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownInventoryException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Inventory;
import inf.unideb.hu.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/inventory")
public class InventoryController {

    private final InventoryService service;

    @ApiOperation("Get all Inventorys")
    @GetMapping("/getAll")
    public Collection<InventoryDto> listInventorys(){

        return service.getAllInventory().stream()
                .map(model -> InventoryDto.builder()
                        .id(model.getId())
                        .storeId(model.getStoreId())
                        .filmId(model.getFilmId())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Inventory")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody InventoryRequestDto InventoryDto)  {
        try{
            service.recordInventory(new Inventory(
                    InventoryDto.getId(),
                    InventoryDto.getStoreId(),
                    InventoryDto.getFilmId(),
                    InventoryDto.getLastUpdate()
            ));
        }catch (InventoryAlreadyExistsException | UnknownStoreException | UnknownFilmException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Inventory successfully created";
    }

    @ApiOperation("Get a Inventory by id")
    @GetMapping("/getById/{id}")
    public InventoryDto getInventoryId(@PathVariable("id") int id)  {
        try{

            Inventory Inventory = service.getInventoryById(id);
            InventoryDto InventoryDto = new InventoryDto(
                    Inventory.getId(),
                    Inventory.getStoreId(),
                    Inventory.getFilmId(),
                    Inventory.getLastUpdate());

            return InventoryDto;

        }catch (UnknownInventoryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Inventory")
    @DeleteMapping("/delete/{id}")
    public String deleteInventory(@PathVariable("id") int id)  {
        try{
            service.deleteInventoryById(id);
        }catch (UnknownInventoryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Inventory successfully deleted";
    }

    @ApiOperation("Update Inventory")
    @PostMapping("/update")
    public String update(@Valid @RequestBody InventoryRequestDto InventoryDto) {
        try{
            service.updateInventory(new Inventory(
                    InventoryDto.getId(),
                    InventoryDto.getStoreId(),
                    InventoryDto.getFilmId(),
                    InventoryDto.getLastUpdate()
            ));
        }catch (UnknownInventoryException | UnknownFilmException | UnknownStoreException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Inventory successfully updated";
    }

}
