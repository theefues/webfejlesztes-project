package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.exceptions.CountryAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.Country;
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
public class CountryDaoImpl implements CountryDao {

    private final CountryRepository CountryRepository;

    @Override
    public void createCountry(Country Country)throws CountryAlreadyExistsException {
        CountryEntity CountryEntity = null;
        try {
             CountryEntity = queryCountry(Country.getId());

        }
        catch (UnknownCountryException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            CountryEntity = CountryEntity.builder()
                    .id(Country.getId())
                    .country(Country.getCountry())
                    .lastUpdate(Country.getLastUpdate())
                    .build();
            CountryRepository.save(CountryEntity);
            log.trace("Recorded new Country: {}",CountryEntity);
            return;
        }
        CountryAlreadyExistsException CountryException = new CountryAlreadyExistsException("Country with id: "+Country.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+CountryException.getMessage(),CountryException.getClass());
        throw CountryException;

    }

    @Override
    public Collection<Country> readAll() {
        log.trace("Read all Countrys");
        return StreamSupport.stream(CountryRepository.findAll().spliterator(),false)
                .map(entity -> new Country(
                        entity.getId(),
                        entity.getCountry(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Country readById(int id) throws UnknownCountryException {

        CountryEntity CountryEntity = queryCountry(id);
        log.trace("Country found by id: {} Country: {}", id,CountryEntity);
        return  new Country(
                CountryEntity.getId(),
                CountryEntity.getCountry(),
                CountryEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownCountryException {

        CountryEntity CountryEntity = queryCountry(id);
        CountryRepository.deleteById(id);
        log.trace("Country deleted: {}", CountryEntity);

    }

    @Override
    public void update( Country Country) throws UnknownCountryException {

        CountryEntity CountryEntity = queryCountry(Country.getId());
        CountryEntity.setId(Country.getId());
        CountryEntity.setCountry(Country.getCountry());
        CountryEntity.setLastUpdate(Country.getLastUpdate());
        CountryRepository.save(CountryEntity);
        log.trace("Country updated: {}", CountryEntity);
    }

    private CountryEntity queryCountry(int id) throws UnknownCountryException {
        Optional<CountryEntity> CountryEntity = CountryRepository.findById(id);

        if (!CountryEntity.isPresent()) {
            UnknownCountryException CountryException = new UnknownCountryException("Country with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+CountryException.getMessage(),CountryException.getClass());
            throw CountryException;
        }
        return CountryEntity.get();
    }
}
