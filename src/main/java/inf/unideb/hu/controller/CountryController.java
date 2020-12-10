package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.CountryDto;
import inf.unideb.hu.controller.dto.CountryRequestDto;
import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;
import inf.unideb.hu.service.CountryService;
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
@RequestMapping(path = "/country")
public class CountryController {

    private final CountryService service;

    @ApiOperation("Get all Countrys")
    @GetMapping("/getAll")
    public Collection<CountryDto> listCountrys(){

        return service.getAllCountry().stream()
                .map(model -> CountryDto.builder()
                        .id(model.getId())
                        .country(model.getCountry())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Country")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody CountryRequestDto CountryDto)  {
        try{
            service.recordCountry(new Country(
                    CountryDto.getId(),
                    CountryDto.getCountry(),
                    CountryDto.getLastUpdate()
            ));
        }catch ( CountryAlreadyExistsException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Country successfully created";
    }

    @ApiOperation("Get a Country by id")
    @GetMapping("/getById/{id}")
    public CountryDto getCountryId(@PathVariable("id") int id)  {
        try{

            Country Country = service.getCountryById(id);
            CountryDto CountryDto = new CountryDto(
                    Country.getId(),
                    Country.getCountry(),
                    Country.getLastUpdate());

            return CountryDto;

        }catch (UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Country")
    @DeleteMapping("/delete/{id}")
    public String deleteCountry(@PathVariable("id") int id)  {
        try{
            service.deleteCountryById(id);
        }catch (UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Country successfully deleted";
    }

    @ApiOperation("Update Country")
    @PostMapping("/update")
    public String update(@Valid @RequestBody CountryRequestDto CountryDto) {
        try{
            service.updateCountry(new Country(
                    CountryDto.getId(),
                    CountryDto.getCountry(),
                    CountryDto.getLastUpdate()
            ));
        }catch (UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Country successfully updated";
    }

}
