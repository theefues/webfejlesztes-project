package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.dao.entity.FilmEntity;
import inf.unideb.hu.dao.entity.LanguageEntity;
import inf.unideb.hu.exceptions.FilmAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownFilmException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Film;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmDaoImpl implements FilmDao {

    private final FilmRepository FilmRepository;
    private final LanguageRepository LanguageRepository;

    @Override
    public void createFilm(Film Film) throws FilmAlreadyExistsException, UnknownLanguageException {
        FilmEntity FilmEntity = null;
        try {
             FilmEntity = queryFilm(Film.getId());

        }
        catch (UnknownFilmException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            FilmEntity = FilmEntity.builder()
                    .id(Film.getId())
                    .title(Film.getTitle())
                    .description(Film.getDescription())
                    .releaseYear(Film.getReleaseYear())
                    .languageId(queryLanguage(Film.getLanguageId()))
                    .originalLanguageId(queryLanguage(Film.getOriginalLanguageId()))
                    .rentalDuration(Film.getRentalDuration())
                    .rentalRate(Film.getRentalRate())
                    .length(Film.getLength())
                    .replacementCost(Film.getReplacementCost())
                    .rating(Film.getRating())
                    .specialFeatures(Film.getSpecialFeatures())
                    .lastUpdate(Film.getLastUpdate())
                    .build();
            FilmRepository.save(FilmEntity);
            log.trace("Recorded new Film: {}",FilmEntity);
            return;
        }
        FilmAlreadyExistsException FilmException = new FilmAlreadyExistsException("Film with id: "+Film.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+FilmException.getMessage(),FilmException.getClass());
        throw FilmException;

    }

    @Override
    public Collection<Film> readAll() {
        log.trace("Read all Films");
        return StreamSupport.stream(FilmRepository.findAll().spliterator(),false)
                .map(entity -> new Film(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getReleaseYear(),
                        entity.getLanguageId().getId(),
                        0,
                        entity.getRentalDuration(),
                        entity.getRentalRate(),
                        entity.getLength(),
                        entity.getReplacementCost(),
                        entity.getRating(),
                        entity.getSpecialFeatures(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Film readById(int id) throws UnknownFilmException {

        FilmEntity FilmEntity = queryFilm(id);
        log.trace("Film found by id: {} Film: {}", id,FilmEntity);
        return  new Film(
                FilmEntity.getId(),
                FilmEntity.getTitle(),
                FilmEntity.getDescription(),
                FilmEntity.getReleaseYear(),
                FilmEntity.getLanguageId().getId(),
                0,
                FilmEntity.getRentalDuration(),
                FilmEntity.getRentalRate(),
                FilmEntity.getLength(),
                FilmEntity.getReplacementCost(),
                FilmEntity.getRating(),
                FilmEntity.getSpecialFeatures(),
                FilmEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownFilmException {

        FilmEntity FilmEntity = queryFilm(id);
        FilmRepository.deleteById(id);
        log.trace("Film deleted: {}", FilmEntity);

    }

    @Override
    public void update( Film Film) throws UnknownFilmException, UnknownLanguageException {

        FilmEntity FilmEntity = queryFilm(Film.getId());
        FilmEntity.setId(Film.getId());
                FilmEntity.setTitle(Film.getTitle());
                FilmEntity.setDescription(Film.getDescription());
                FilmEntity.setReleaseYear(Film.getReleaseYear());
                FilmEntity.setLanguageId(queryLanguage(Film.getLanguageId()));
        FilmEntity.setOriginalLanguageId(queryLanguage(0));
                FilmEntity.setRentalDuration(Film.getRentalDuration());
                FilmEntity.setRentalRate(Film.getRentalRate());
                FilmEntity.setLength(Film.getLength());
                FilmEntity.setReplacementCost(Film.getReplacementCost());
                FilmEntity.setRating(Film.getRating());
                FilmEntity.setSpecialFeatures(Film.getSpecialFeatures());
                FilmEntity.setLastUpdate(Film.getLastUpdate());
        FilmRepository.save(FilmEntity);
        log.trace("Film updated: {}", FilmEntity);
    }
    protected LanguageEntity queryLanguage(int LanguageId) throws UnknownLanguageException {
        Optional<LanguageEntity> LanguageEntity = LanguageRepository.findById(LanguageId);

        if (!LanguageEntity.isPresent()) {
            UnknownLanguageException LanguageException = new UnknownLanguageException("Language with id: " + Integer.toString(LanguageId)+" not exists");
            log.error("Exception: {} thrown with message: "+LanguageException.getMessage(),LanguageException.getClass());
            throw LanguageException;
        }
        return LanguageEntity.get();
    }
    private FilmEntity queryFilm(int id) throws UnknownFilmException {
        Optional<FilmEntity> FilmEntity = FilmRepository.findById(id);

        if (!FilmEntity.isPresent()) {
            UnknownFilmException FilmException = new UnknownFilmException("Film with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+FilmException.getMessage(),FilmException.getClass());
            throw FilmException;
        }
        return FilmEntity.get();
    }
}
