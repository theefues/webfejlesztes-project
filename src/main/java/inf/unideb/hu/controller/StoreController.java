package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.StoreDto;
import inf.unideb.hu.controller.dto.StoreRequestDto;
import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;
import inf.unideb.hu.service.StoreService;
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
@RequestMapping(path = "/store")
public class StoreController {

    private final StoreService service;

    @ApiOperation("Get all Stores")
    @GetMapping("/getAll")
    public Collection<StoreDto> listStores(){

        return service.getAllStore().stream()
                .map(model -> StoreDto.builder()
                        .id(model.getId())
                        .managerStaffId(model.getManagerStaffId())
                        .addressId(model.getAddressId())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Store")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody StoreRequestDto StoreDto)  {
        try{
            service.recordStore(new Store(
                    StoreDto.getId(),
                    StoreDto.getManagerStaffId(),
                    StoreDto.getAddressId(),
                    StoreDto.getLastUpdate()
            ));
        }catch (StoreAlreadyExistsException | UnknownAddressException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Store successfully created";
    }

    @ApiOperation("Get a Store by id")
    @GetMapping("/getById/{id}")
    public StoreDto getStoreId(@PathVariable("id") int id)  {
        try{

            Store Store = service.getStoreById(id);
            StoreDto StoreDto = new StoreDto(
                    Store.getId(),
                    Store.getManagerStaffId(),
                    Store.getAddressId(),
                    Store.getLastUpdate());

            return StoreDto;

        }catch (UnknownStoreException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Store")
    @DeleteMapping("/delete/{id}")
    public String deleteStore(@PathVariable("id") int id)  {
        try{
            service.deleteStoreById(id);
        }catch (UnknownStoreException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Store successfully deleted";
    }

    @ApiOperation("Update Store")
    @PostMapping("/update")
    public String update(@Valid @RequestBody StoreRequestDto StoreDto) {
        try{
            service.updateStore(new Store(
                    StoreDto.getId(),
                    StoreDto.getManagerStaffId(),
                    StoreDto.getAddressId(),
                    StoreDto.getLastUpdate()
            ));
        }catch (UnknownStoreException | UnknownAddressException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Store successfully updated";
    }

}
