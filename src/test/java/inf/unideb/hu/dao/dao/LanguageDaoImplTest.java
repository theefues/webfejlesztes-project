package inf.unideb.hu.dao.dao;

import inf.unideb.hu.dao.LanguageDaoImpl;
import inf.unideb.hu.dao.LanguageRepository;
import inf.unideb.hu.dao.entity.LanguageEntity;
import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;
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
class LanguageDaoImplTest {

    @InjectMocks
    private LanguageDaoImpl dao;
    @Mock
    private LanguageRepository LanguageRepository;

    @Test
    void createLanguage() throws LanguageAlreadyExistsException, UnknownLanguageException {

        when(LanguageRepository.findById(anyInt())).thenReturn(Optional.empty());

        dao.createLanguage(getLanguage());
        verify(LanguageRepository, times(1)).save(getLanguageEntity());


        when(LanguageRepository.findById(anyInt())).thenReturn(Optional.of(getLanguageEntity()));

        assertThrows(LanguageAlreadyExistsException.class,() -> {
            dao.createLanguage(getLanguage());
        });
    }

    @Test
    void readAll() throws UnknownLanguageException {
        when(LanguageRepository.findAll()).thenReturn(getDefaultLanguageEntities());

        assertEquals(dao.readAll(),getDefaultLanguages());
    }

    @Test
    void readById() throws UnknownLanguageException {
        when(LanguageRepository.findById(anyInt())).thenReturn(Optional.of(getLanguageEntity()));

        assertEquals(dao.readById(1),getLanguage());
    }

    @Test
    void deleteById() throws UnknownLanguageException {
        when(LanguageRepository.findById(anyInt())).thenReturn(Optional.of(getLanguageEntity()));

        dao.deleteById(1);
        verify(LanguageRepository, times(1)).deleteById(1);

    }

    @Test
    void update() throws UnknownLanguageException {
        when(LanguageRepository.findById(anyInt())).thenReturn(Optional.of(getLanguageEntity()));
        dao.update(getLanguage());
        verify(LanguageRepository, times(1)).save(getLanguageEntity());

    }



    private Language getLanguage() {
        return new Language(
                1,
                "English",
                new Date(2006,02,15)
        );
    }

    private LanguageEntity getLanguageEntity() {
        return new LanguageEntity(
                1,
                "English",
                new Date(2006,02,15)
        );
    }



    private Collection<LanguageEntity> getDefaultLanguageEntities() {
        return Arrays.asList(

                new LanguageEntity(
                        1,
                        "English",
                        new Date(2006,02,15)
                ),
                new LanguageEntity(
                        2,
                        "Italian",
                        new Date(2006,02,15)
                ),
                new LanguageEntity(
                        3,
                        "Japanese",
                        new Date(2006,02,15)
                )
        );

    }


    private Collection<Language> getDefaultLanguages() {
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
}