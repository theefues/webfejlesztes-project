package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.FilmEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FilmRepository extends CrudRepository<FilmEntity,Integer> {

    @Override
    Optional<FilmEntity> findById(Integer integer);
}
