package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.LanguageDto;
import inf.unideb.hu.controller.dto.LanguageRequestDto;

import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;

import inf.unideb.hu.exceptions.UnknownLanguageException;

import inf.unideb.hu.model.Language;

import inf.unideb.hu.service.LanguageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageControllerTest {

    @InjectMocks
    private LanguageController LanguageController;

    @Mock
    private LanguageService LanguageService;

    @Test
    void listLanguages() {
        when(LanguageService.getAllLanguage()).thenReturn(getLanguages());

        LanguageController.listLanguages();
        verify(LanguageService, times(1)).getAllLanguage();
        assertEquals(getLanguageDtos(),LanguageController.listLanguages());
    }

    @Test
    void record() throws LanguageAlreadyExistsException {
        LanguageController.record(getDefaultLanguageRequestDto());
        verify(LanguageService, times(1)).recordLanguage(getDefaultLanguage());

        doThrow(LanguageAlreadyExistsException.class).when(LanguageService).recordLanguage(getDefaultLanguage());
        assertThrows(ResponseStatusException.class,() -> {
            LanguageController.record(getDefaultLanguageRequestDto());
        });
    }

    @Test
    void getLanguageId() throws UnknownLanguageException {
        when(LanguageService.getLanguageById(anyInt())).thenReturn(getDefaultLanguage());

        LanguageController.getLanguageId(anyInt());
        verify(LanguageService, times(1)).getLanguageById(anyInt());
        assertEquals(getDefaultLanguageDto(),LanguageController.getLanguageId(anyInt()));



        when(LanguageService.getLanguageById(anyInt())).thenThrow(UnknownLanguageException.class);
        assertThrows(ResponseStatusException.class,() -> {
            LanguageController.getLanguageId(1);
        });
    }



    @Test
    void deleteLanguage() throws UnknownLanguageException {
        LanguageController.deleteLanguage(anyInt());
        verify(LanguageService, times(1)).deleteLanguageById(anyInt());


        doThrow(UnknownLanguageException.class).when(LanguageService).deleteLanguageById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            LanguageController.deleteLanguage(anyInt());
        });
    }

    @Test
    void update() throws UnknownLanguageException {
        LanguageController.update(getDefaultLanguageRequestDto());
        verify(LanguageService, times(1)).updateLanguage(getDefaultLanguage());

        doThrow(UnknownLanguageException.class).when(LanguageService).updateLanguage(getDefaultLanguage());
        assertThrows(ResponseStatusException.class,() -> {
            LanguageController.update(getDefaultLanguageRequestDto());
        });
    }

    private Language getDefaultLanguage() {


        return new Language(
                1,
                "English",
                new Date(2006,02,15)
        );


    }

    private LanguageDto getDefaultLanguageDto() {

        return new LanguageDto(
                1,
                "English",
                new Date(2006,02,15)
        );

    }

    private LanguageRequestDto getDefaultLanguageRequestDto() {

        return new LanguageRequestDto(
                1,
                "English",
                new Date(2006,02,15)
        );

    }


    private Collection<Language> getLanguages() {
        return Arrays.asList(

                new Language(
                        1,
                        "English",
                        new Date(2006,02,15)
                ),
                new Language(
                        2,
                        "Italian",
                        new Date(2006,02,15)
                ),
                new Language(
                        3,
                        "Japanese",
                        new Date(2006,02,15)
                )
        );

    }

    private Collection<LanguageDto> getLanguageDtos() {
        return Arrays.asList(

                new LanguageDto(
                        1,
                        "English",
                        new Date(2006,02,15)
                ),
                new LanguageDto(
                        2,
                        "Italian",
                        new Date(2006,02,15)
                ),
                new LanguageDto(
                        3,
                        "Japanese",
                        new Date(2006,02,15)
                )
        );

    }
}