package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.AddressDto;
import inf.unideb.hu.controller.dto.AddressRequestDto;
import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;
import inf.unideb.hu.service.AddressService;
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
@RequestMapping(path = "/address")
public class AddressController {

    private final AddressService service;

    @ApiOperation("Get all Addresss")
    @GetMapping("/getAll")
    public Collection<AddressDto> listAddresss(){

        return service.getAllAddress().stream()
                .map(model -> AddressDto.builder()
                        .id(model.getId())
                        .address(model.getAddress())
                        .address2(model.getAddress2())
                        .district(model.getDistrict())
                        .cityId(model.getCityId())
                        .postalCode(model.getPostalCode())
                        .phone(model.getPhone())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Address")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody AddressRequestDto AddressDto)  {
        try{
            service.recordAddress(new Address(
                    AddressDto.getId(),
                    AddressDto.getAddress(),
                    AddressDto.getAddress2(),
                    AddressDto.getDistrict(),
                    AddressDto.getCityId(),
                    AddressDto.getPostalCode(),
                    AddressDto.getPhone(),
                    AddressDto.getLastUpdate()
            ));
        }catch (AddressAlreadyExistsException | UnknownCityException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Address successfully created";
    }

    @ApiOperation("Get a Address by id")
    @GetMapping("/getById/{id}")
    public AddressDto getAddressId(@PathVariable("id") int id)  {
        try{

            Address address = service.getAddressById(id);
            AddressDto AddressDto = new AddressDto(
                    address.getId(),
                    address.getAddress(),
                    address.getAddress2(),
                    address.getDistrict(),
                    address.getCityId(),
                    address.getPostalCode(),
                    address.getPhone(),
                    address.getLastUpdate());

            return AddressDto;

        }catch (UnknownAddressException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Address")
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") int id)  {
        try{
            service.deleteAddressById(id);
        }catch (UnknownAddressException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Address successfully deleted";
    }

    @ApiOperation("Update Address")
    @PostMapping("/update")
    public String update(@Valid @RequestBody AddressRequestDto AddressDto) {
        try{
            service.updateAddress(new Address(
                    AddressDto.getId(),
                    AddressDto.getAddress(),
                    AddressDto.getAddress2(),
                    AddressDto.getDistrict(),
                    AddressDto.getCityId(),
                    AddressDto.getPostalCode(),
                    AddressDto.getPhone(),
                    AddressDto.getLastUpdate())
            );
        }catch (UnknownAddressException | UnknownCityException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Address successfully updated";
    }

}
