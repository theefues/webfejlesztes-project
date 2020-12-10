package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.LanguageEntity;
import inf.unideb.hu.exceptions.LanguageAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownLanguageException;
import inf.unideb.hu.model.Language;
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
public class LanguageDaoImpl implements LanguageDao {

    private final LanguageRepository LanguageRepository;

    @Override
    public void createLanguage(Language Language)throws LanguageAlreadyExistsException {
        LanguageEntity LanguageEntity = null;
        try {
             LanguageEntity = queryLanguage(Language.getId());

        }
        catch (UnknownLanguageException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            LanguageEntity = LanguageEntity.builder()
                    .id(Language.getId())
                    .name(Language.getName())
                    .lastUpdate(Language.getLastUpdate())
                    .build();
            LanguageRepository.save(LanguageEntity);
            log.trace("Recorded new Language: {}",LanguageEntity);
            return;
        }
        LanguageAlreadyExistsException LanguageException = new LanguageAlreadyExistsException("Language with id: "+Language.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+LanguageException.getMessage(),LanguageException.getClass());
        throw LanguageException;

    }

    @Override
    public Collection<Language> readAll() {
        log.trace("Read all Languages");
        return StreamSupport.stream(LanguageRepository.findAll().spliterator(),false)
                .map(entity -> new Language(
                        entity.getId(),
                        entity.getName(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Language readById(int id) throws UnknownLanguageException {

        LanguageEntity LanguageEntity = queryLanguage(id);
        log.trace("Language found by id: {} Language: {}", id,LanguageEntity);
        return  new Language(
                LanguageEntity.getId(),
                LanguageEntity.getName(),
                LanguageEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownLanguageException {

        LanguageEntity LanguageEntity = queryLanguage(id);
        LanguageRepository.deleteById(id);
        log.trace("Language deleted: {}", LanguageEntity);

    }

    @Override
    public void update( Language Language) throws UnknownLanguageException {

        LanguageEntity LanguageEntity = queryLanguage(Language.getId());
        LanguageEntity.setId(Language.getId());
                LanguageEntity.setName(Language.getName());
                LanguageEntity.setLastUpdate(Language.getLastUpdate());
        LanguageRepository.save(LanguageEntity);
        log.trace("Language updated: {}", LanguageEntity);
    }

    private LanguageEntity queryLanguage(int id) throws UnknownLanguageException {
        Optional<LanguageEntity> LanguageEntity = LanguageRepository.findById(id);

        if (!LanguageEntity.isPresent()) {
            UnknownLanguageException LanguageException = new UnknownLanguageException("Language with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+LanguageException.getMessage(),LanguageException.getClass());
            throw LanguageException;
        }
        return LanguageEntity.get();
    }
}
