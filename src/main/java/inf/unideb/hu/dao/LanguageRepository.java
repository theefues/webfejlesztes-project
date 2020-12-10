package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<LanguageEntity,Integer> {

    @Override
    Optional<LanguageEntity> findById(Integer integer);
}
