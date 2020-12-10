package inf.unideb.hu.service;

import inf.unideb.hu.dao.FilmDao;
import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService{

    private final FilmDao FilmDao;
    @Override
    public Collection<Film> getAllFilm() {
        return FilmDao.readAll();
    }

    @Override
    public void recordFilm(Film film) throws FilmAlreadyExistsException, UnknownLanguageException {
        FilmDao.createFilm(film);
    }

    @Override
    public Film getFilmById(int id) throws UnknownFilmException {
        return FilmDao.readById(id);
    }

    @Override
    public void deleteFilmById(int id) throws UnknownFilmException {
        FilmDao.deleteById(id);
    }

    @Override
    public void updateFilm(Film film) throws UnknownFilmException, UnknownLanguageException {
        FilmDao.update(film);
    }
}
