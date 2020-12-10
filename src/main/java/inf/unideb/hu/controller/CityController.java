package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.CityDto;
import inf.unideb.hu.controller.dto.CityRequestDto;
import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;
import inf.unideb.hu.service.CityService;
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
@RequestMapping(path = "/city")
public class CityController {

    private final CityService service;

    @ApiOperation("Get all Citys")
    @GetMapping("/getAll")
    public Collection<CityDto> listCitys(){

        return service.getAllCity().stream()
                .map(model -> CityDto.builder()
                        .id(model.getId())
                        .city(model.getCity())
                        .countryId(model.getCountryId())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create City")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody CityRequestDto CityDto)  {
        try{
            service.recordCity(new City(
                    CityDto.getId(),
                    CityDto.getCity(),
                    CityDto.getCountryId(),
                    CityDto.getLastUpdate()
            ));
        }catch (CityAlreadyExistsException | UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "City successfully created";
    }

    @ApiOperation("Get a City by id")
    @GetMapping("/getById/{id}")
    public CityDto getCityId(@PathVariable("id") int id)  {
        try{

            City City = service.getCityById(id);
            CityDto CityDto = new CityDto(
                    City.getId(),
                    City.getCity(),
                    City.getCountryId(),
                    City.getLastUpdate());

            return CityDto;

        }catch (UnknownCityException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete City")
    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") int id)  {
        try{
            service.deleteCityById(id);
        }catch (UnknownCityException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "City successfully deleted";
    }

    @ApiOperation("Update City")
    @PostMapping("/update")
    public String update(@Valid @RequestBody CityRequestDto CityDto) {
        try{
            service.updateCity(new City(
                    CityDto.getId(),
                    CityDto.getCity(),
                    CityDto.getCountryId(),
                    CityDto.getLastUpdate()
            ));
        }catch (UnknownCityException | UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "City successfully updated";
    }

}
