package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;

import java.util.Collection;

public interface LanguageService {

    Collection<Language> getAllLanguage();
    void recordLanguage(Language Language) throws LanguageAlreadyExistsException;
    void deleteLanguageById(int id) throws UnknownLanguageException;
    void updateLanguage(Language Language) throws UnknownLanguageException;
    Language getLanguageById(int id) throws UnknownLanguageException;
}
