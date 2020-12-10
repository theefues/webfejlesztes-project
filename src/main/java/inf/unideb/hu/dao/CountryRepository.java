package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<CountryEntity,Integer> {

    @Override
    Optional<CountryEntity> findById(Integer integer);
}
