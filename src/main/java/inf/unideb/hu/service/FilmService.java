package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;

import java.util.Collection;

public interface FilmService {

    Collection<Film> getAllFilm();
    void recordFilm(Film film) throws FilmAlreadyExistsException, UnknownLanguageException;
    void deleteFilmById(int id) throws UnknownFilmException;
    void updateFilm(Film film) throws UnknownFilmException, UnknownLanguageException;
    Film getFilmById(int id) throws UnknownFilmException;
}
