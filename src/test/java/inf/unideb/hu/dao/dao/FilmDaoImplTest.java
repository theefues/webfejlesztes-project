package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.FilmDaoImpl;
import inf.unideb.hu.dao.FilmRepository;
import inf.unideb.hu.dao.entity.FilmEntity;
import inf.unideb.hu.dao.entity.LanguageEntity;
import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmDaoImplTest {

    @InjectMocks
    private FilmDaoImpl dao;
    @Mock
    private FilmRepository FilmRepository;

    @Test
    void createFilm() throws FilmAlreadyExistsException, UnknownFilmException, UnknownLanguageException {

        when(FilmRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createFilm(getFilm());
        verify(FilmRepository, times(1)).save(getFilmEntity());


        when(FilmRepository.findById(anyInt())).thenReturn(Optional.of(getFilmEntity()));

        assertThrows(FilmAlreadyExistsException.class,() -> {
            dao.createFilm(getFilm());
        });
    }

    @Test
    void readAll() throws UnknownFilmException {
        when(FilmRepository.findAll()).thenReturn(getDefaultFilmEntities());

        assertEquals(dao.readAll(),getDefaultFilms());
    }

    @Test
    void readById() throws UnknownFilmException {
        when(FilmRepository.findById(anyInt())).thenReturn(Optional.of(getFilmEntity()));

        assertEquals(dao.readById(1),getFilm());
    }

    @Test
    void deleteById() throws UnknownFilmException {
        when(FilmRepository.findById(anyInt())).thenReturn(Optional.of(getFilmEntity()));

        dao.deleteById(1);
        verify(FilmRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownFilmException, UnknownLanguageException {
        when(FilmRepository.findById(anyInt())).thenReturn(Optional.of(getFilmEntity()));
        dao.update(getFilm());
        verify(FilmRepository, times(1)).save(getFilmEntity());

    }



    private Film getFilm() {
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

    private FilmEntity getFilmEntity() {
        return new FilmEntity(
                1,
                "ACADEMY DINOSAUR",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                2006,
                new LanguageEntity(
                        1,
                        "English",
                        new Date(2006,02,15)
                ),
                new LanguageEntity(
                        1,
                        "English",
                        new Date(2006,02,15)
                ),
                6,
                0.99,
                86,
                20.99,
                "PG",
                "Deleted Scenes,Behind the Scenes",
                new Date(2006,02,15)
        );
    }



    private Collection<FilmEntity> getDefaultFilmEntities() {
        return Arrays.asList(

                new FilmEntity(
                        1,
                        "ACADEMY DINOSAUR",
                        "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                        2006,
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        6,
                        0.99,
                        86,
                        20.99,
                        "PG",
                        "Deleted Scenes,Behind the Scenes",
                        new Date(2006,02,15)
                ),
                new FilmEntity(
                        2,
                        "ACE GOLDFINGER",
                        "A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China",
                        2006,
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        3,
                        4.99,
                        48,
                        12.99,
                        "G",
                        "Trailers,Deleted Scenes",
                        new Date(2006,02,15)
                ),
                new FilmEntity(
                        3,
                        "ADAPTATION HOLES",
                        "A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory",
                        2006,
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
                        new LanguageEntity(
                                1,
                                "English",
                                new Date(2006,02,15)
                        ),
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


    private Collection<Film> getDefaultFilms() {
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
}