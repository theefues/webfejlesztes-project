package inf.unideb.hu.controller;


import inf.unideb.hu.controller.dto.FilmDto;
import inf.unideb.hu.controller.dto.FilmRequestDto;
import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;
import inf.unideb.hu.service.FilmService;
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
class FilmControllerTest {

    @InjectMocks
    private FilmController FilmController;

    @Mock
    private FilmService FilmService;

    @Test
    void listFilms() {
        when(FilmService.getAllFilm()).thenReturn(getFilms());

        FilmController.listFilms();
        verify(FilmService, times(1)).getAllFilm();
        assertEquals(getFilmDtos(),FilmController.listFilms());
    }

    @Test
    void record() throws FilmAlreadyExistsException, UnknownLanguageException {
        FilmController.record(getDefaultFilmRequestDto());
        verify(FilmService, times(1)).recordFilm(getDefaultFilm());

        doThrow(FilmAlreadyExistsException.class).when(FilmService).recordFilm(getDefaultFilm());
        assertThrows(ResponseStatusException.class,() -> {
            FilmController.record(getDefaultFilmRequestDto());
        });
    }

    @Test
    void getFilmId() throws UnknownFilmException {
        when(FilmService.getFilmById(anyInt())).thenReturn(getDefaultFilm());

        FilmController.getFilmId(anyInt());
        verify(FilmService, times(1)).getFilmById(anyInt());
        assertEquals(getDefaultFilmDto(),FilmController.getFilmId(anyInt()));



        when(FilmService.getFilmById(anyInt())).thenThrow(UnknownFilmException.class);
        assertThrows(ResponseStatusException.class,() -> {
            FilmController.getFilmId(1);
        });
    }



    @Test
    void deleteFilm() throws UnknownFilmException {
        FilmController.deleteFilm(anyInt());
        verify(FilmService, times(1)).deleteFilmById(anyInt());


        doThrow(UnknownFilmException.class).when(FilmService).deleteFilmById(anyInt());
        assertThrows(ResponseStatusException.class,() -> {
            FilmController.deleteFilm(anyInt());
        });
    }

    @Test
    void update() throws UnknownFilmException, UnknownLanguageException {
        FilmController.update(getDefaultFilmRequestDto());
        verify(FilmService, times(1)).updateFilm(getDefaultFilm());

        doThrow(UnknownFilmException.class).when(FilmService).updateFilm(getDefaultFilm());
        assertThrows(ResponseStatusException.class,() -> {
            FilmController.update(getDefaultFilmRequestDto());
        });
    }

    private Film getDefaultFilm() {


        return new Film(
                1,
     		"ACADEMY DINOSAUR",
     		"A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
     		2006,
     		1,
     	    0,
                6,
      		0.99,
      		86,
      		20.99,
            "PG",
      		"Deleted Scenes,Behind the Scenes",
      		new Date(2006,02,15)
        );
    }

    private FilmDto getDefaultFilmDto() {

        return new FilmDto(
                1,
                "ACADEMY DINOSAUR",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                2006,
                1,
                0,
                6,
                0.99,
                86,
                20.99,
                "PG",
                "Deleted Scenes,Behind the Scenes",
                new Date(2006,02,15)
        );

    }

    private FilmRequestDto getDefaultFilmRequestDto() {

        return new FilmRequestDto(
                1,
                "ACADEMY DINOSAUR",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                2006,
                1,
                0,
                6,
                0.99,
                86,
                20.99,
                "PG",
                "Deleted Scenes,Behind the Scenes",
                new Date(2006,02,15)
        );

    }


    private Collection<Film> getFilms() {
        return Arrays.asList(

                new Film(
                        1,
                        "ACADEMY DINOSAUR",
                        "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                        2006,
                        1,
                        0,
                        6,
                        0.99,
                        86,
                        20.99,
                        "PG",
                        "Deleted Scenes,Behind the Scenes",
                        new Date(2006,02,15)
                ),
                new Film(
                        2,
                        "ACE GOLDFINGER",
                        "A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China",
                        2006,
                        1,
                        0,
                        3,
                        4.99,
                        48,
                        12.99,
                        "G",
                        "Trailers,Deleted Scenes",
                        new Date(2006,02,15)
                ),
                new Film(
                        3,
                        "ADAPTATION HOLES",
                        "A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory",
                        2006,
                        1,
                        0,
                        7,
                        2.99,
                        50,
                        18.99,
                        "NC-17",
                        "Trailers,Deleted Scenes",
                        new Date(2006,02,15)
                )
        );

    }

    private Collection<FilmDto> getFilmDtos() {
        return Arrays.asList(

                new FilmDto(
                        1,
                        "ACADEMY DINOSAUR",
                        "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                        2006,
                        1,
                        0,
                        6,
                        0.99,
                        86,
                        20.99,
                        "PG",
                        "Deleted Scenes,Behind the Scenes",
                        new Date(2006,02,15)
                ),
                new FilmDto(
                        2,
                        "ACE GOLDFINGER",
                        "A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China",
                        2006,
                        1,
                        0,
                        3,
                        4.99,
                        48,
                        12.99,
                        "G",
                        "Trailers,Deleted Scenes",
                        new Date(2006,02,15)
                ),
                new FilmDto(
                        3,
                        "ADAPTATION HOLES",
                        "A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory",
                        2006,
                        1,
                        0,
                        7,
                        2.99,
                        50,
                        18.99,
                        "NC-17",
                        "Trailers,Deleted Scenes",
                        new Date(2006,02,15)
                )
        );

    }
}