package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;

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
public interface LanguageDao {

    void createLanguage(Language Language) throws LanguageAlreadyExistsException;
    Collection<Language> readAll();
    Language readById(int id) throws UnknownLanguageException;
    void deleteById(int id) throws UnknownLanguageException;
    void update(Language Language) throws UnknownLanguageException;
}
