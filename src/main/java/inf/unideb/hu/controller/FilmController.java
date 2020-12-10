package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.FilmDto;
import inf.unideb.hu.controller.dto.FilmRequestDto;
import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;
import inf.unideb.hu.service.FilmService;
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
@RequestMapping(path = "/film")
public class FilmController {

    private final FilmService service;

    @ApiOperation("Get all Films")
    @GetMapping("/getAll")
    public Collection<FilmDto> listFilms(){

        return service.getAllFilm().stream()
                .map(model -> FilmDto.builder()
                        .id(model.getId())
                        .title(model.getTitle())
                        .description(model.getDescription())
                        .releaseYear(model.getReleaseYear())
                        .languageId(model.getLanguageId())
                        .originalLanguageId(0)
                        .rentalDuration(model.getRentalDuration())
                        .rentalRate(model.getRentalRate())
                        .length(model.getLength())
                        .replacementCost(model.getReplacementCost())
                        .rating(model.getRating())
                        .specialFeatures(model.getSpecialFeatures())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Film")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody FilmRequestDto FilmDto)  {
        try{
            service.recordFilm(new Film(
                    FilmDto.getId(),
                    FilmDto.getTitle(),
                    FilmDto.getDescription(),
                    FilmDto.getReleaseYear(),
                    FilmDto.getLanguageId(),
                    0,
                    FilmDto.getRentalDuration(),
                    FilmDto.getRentalRate(),
                    FilmDto.getLength(),
                    FilmDto.getReplacementCost(),
                    FilmDto.getRating(),
                    FilmDto.getSpecialFeatures(),
                    FilmDto.getLastUpdate()
            ));
        }catch (FilmAlreadyExistsException | UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Film successfully created";
    }

    @ApiOperation("Get a Film by id")
    @GetMapping("/getById/{id}")
    public FilmDto getFilmId(@PathVariable("id") int id)  {
        try{

            Film Film = service.getFilmById(id);
            FilmDto FilmDto = new FilmDto(
                    Film.getId(),
                    Film.getTitle(),
                    Film.getDescription(),
                    Film.getReleaseYear(),
                    Film.getLanguageId(),
                    0,
                    Film.getRentalDuration(),
                    Film.getRentalRate(),
                    Film.getLength(),
                    Film.getReplacementCost(),
                    Film.getRating(),
                    Film.getSpecialFeatures(),
                    Film.getLastUpdate());

            return FilmDto;

        }catch (UnknownFilmException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Film")
    @DeleteMapping("/delete/{id}")
    public String deleteFilm(@PathVariable("id") int id)  {
        try{
            service.deleteFilmById(id);
        }catch (UnknownFilmException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Film successfully deleted";
    }

    @ApiOperation("Update Film")
    @PostMapping("/update")
    public String update(@Valid @RequestBody FilmRequestDto FilmDto) {
        try{
            service.updateFilm(new Film(
                    FilmDto.getId(),
                    FilmDto.getTitle(),
                    FilmDto.getDescription(),
                    FilmDto.getReleaseYear(),
                    FilmDto.getLanguageId(),
                    0,
                    FilmDto.getRentalDuration(),
                    FilmDto.getRentalRate(),
                    FilmDto.getLength(),
                    FilmDto.getReplacementCost(),
                    FilmDto.getRating(),
                    FilmDto.getSpecialFeatures(),
                    FilmDto.getLastUpdate()
            ));
        }catch (UnknownFilmException | UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Film successfully updated";
    }

}
