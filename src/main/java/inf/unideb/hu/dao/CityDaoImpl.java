package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.dao.entity.CountryEntity;
import inf.unideb.hu.exceptions.CityAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.exceptions.UnknownCountryException;
import inf.unideb.hu.model.City;
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
public class CityDaoImpl implements CityDao {

    private final CityRepository CityRepository;
    private final CountryRepository CountryRepository;
    @Override
    public void createCity(City City) throws CityAlreadyExistsException, UnknownCountryException {
        CityEntity CityEntity = null;
        try {
             CityEntity = queryCity(City.getId());

        }
        catch (UnknownCityException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            CityEntity = CityEntity.builder()
                    .id(City.getId())
                    .city(City.getCity())
                    .countryId(queryCountry(City.getId()))
                    .lastUpdate(City.getLastUpdate())
                    .build();
            CityRepository.save(CityEntity);
            log.trace("Recorded new City: {}",CityEntity);
            return;
        }
        CityAlreadyExistsException CityException = new CityAlreadyExistsException("City with id: "+City.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+CityException.getMessage(),CityException.getClass());
        throw CityException;

    }

    @Override
    public Collection<City> readAll() {
        log.trace("Read all Citys");
        return StreamSupport.stream(CityRepository.findAll().spliterator(),false)
                .map(entity -> new City(
                        entity.getId(),
                        entity.getCity(),
                        entity.getCountryId().getId(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public City readById(int id) throws UnknownCityException {

        CityEntity CityEntity = queryCity(id);
        log.trace("City found by id: {} City: {}", id,CityEntity);
        return  new City(
                CityEntity.getId(),
                CityEntity.getCity(),
                CityEntity.getCountryId().getId(),
                CityEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownCityException {

        CityEntity CityEntity = queryCity(id);
        CityRepository.deleteById(id);
        log.trace("City deleted: {}", CityEntity);

    }

    @Override
    public void update( City City) throws UnknownCityException, UnknownCountryException {

        CityEntity CityEntity = queryCity(City.getId());
        CityEntity.setId(City.getId());
        CityEntity.setCity(City.getCity());
        CityEntity.setCountryId(queryCountry(City.getCountryId()));
        CityEntity.setLastUpdate(City.getLastUpdate());
        CityRepository.save(CityEntity);
        log.trace("City updated: {}", CityEntity);
    }

    protected CountryEntity queryCountry(int CountryId) throws UnknownCountryException {
        Optional<CountryEntity> CountryEntity = CountryRepository.findById(CountryId);

        if (!CountryEntity.isPresent()) {
            UnknownCountryException CountryException = new UnknownCountryException("Country with id: " + Integer.toString(CountryId)+" not exists");
            log.error("Exception: {} thrown with message: "+CountryException.getMessage(),CountryException.getClass());
            throw CountryException;
        }
        return CountryEntity.get();
    }

    private CityEntity queryCity(int id) throws UnknownCityException {
        Optional<CityEntity> CityEntity = CityRepository.findById(id);

        if (!CityEntity.isPresent()) {
            UnknownCityException CityException = new UnknownCityException("City with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+CityException.getMessage(),CityException.getClass());
            throw CityException;
        }
        return CityEntity.get();
    }
}
