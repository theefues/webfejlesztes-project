package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<CityEntity,Integer> {

    @Override
    Optional<CityEntity> findById(Integer integer);
}
