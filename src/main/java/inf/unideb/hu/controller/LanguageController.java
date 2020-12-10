package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.LanguageDto;
import inf.unideb.hu.controller.dto.LanguageRequestDto;
import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;
import inf.unideb.hu.service.LanguageService;
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
@RequestMapping(path = "/language")
public class LanguageController {

    private final LanguageService service;

    @ApiOperation("Get all Languages")
    @GetMapping("/getAll")
    public Collection<LanguageDto> listLanguages(){

        return service.getAllLanguage().stream()
                .map(model -> LanguageDto.builder()
                        .id(model.getId())
                        .name(model.getName())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Language")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody LanguageRequestDto LanguageDto)  {
        try{
            service.recordLanguage(new Language(
                    LanguageDto.getId(),
                    LanguageDto.getName(),
                    LanguageDto.getLastUpdate()
            ));
        }catch ( LanguageAlreadyExistsException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Language successfully created";
    }

    @ApiOperation("Get a Language by id")
    @GetMapping("/getById/{id}")
    public LanguageDto getLanguageId(@PathVariable("id") int id)  {
        try{

            Language Language = service.getLanguageById(id);
            LanguageDto LanguageDto = new LanguageDto(
                    Language.getId(),
                    Language.getName(),
                    Language.getLastUpdate());

            return LanguageDto;

        }catch (UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Language")
    @DeleteMapping("/delete/{id}")
    public String deleteLanguage(@PathVariable("id") int id)  {
        try{
            service.deleteLanguageById(id);
        }catch (UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Language successfully deleted";
    }

    @ApiOperation("Update Language")
    @PostMapping("/update")
    public String update(@Valid @RequestBody LanguageRequestDto LanguageDto) {
        try{
            service.updateLanguage(new Language(
                    LanguageDto.getId(),
                    LanguageDto.getName(),
                    LanguageDto.getLastUpdate()
            ));
        }catch (UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Language successfully updated";
    }

}
