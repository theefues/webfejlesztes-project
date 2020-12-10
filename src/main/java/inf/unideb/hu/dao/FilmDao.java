package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface FilmDao {

    void createFilm(Film Film) throws FilmAlreadyExistsException, UnknownLanguageException;
    Collection<Film> readAll();
    Film readById(int id) throws UnknownFilmException;
    void deleteById(int id) throws UnknownFilmException;
    void update(Film Film) throws UnknownFilmException, UnknownLanguageException;
}
