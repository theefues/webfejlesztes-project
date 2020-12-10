package inf.unideb.hu.service;

import inf.unideb.hu.dao.LanguageDao;
import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService{

    private final LanguageDao LanguageDao;

    @Override
    public Collection<Language> getAllLanguage() {
        return LanguageDao.readAll();
    }

    @Override
    public void recordLanguage(Language Language) throws LanguageAlreadyExistsException {
        LanguageDao.createLanguage(Language);
    }

    @Override
    public Language getLanguageById(int id) throws UnknownLanguageException {
        return LanguageDao.readById(id);
    }

    @Override
    public void deleteLanguageById(int id) throws UnknownLanguageException {
        LanguageDao.deleteById(id);
    }

    @Override
    public void updateLanguage(Language Language) throws UnknownLanguageException {
        LanguageDao.update(Language);
    }
}
