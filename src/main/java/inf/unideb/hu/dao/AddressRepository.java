package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<AddressEntity,Integer> {

    @Override
    Optional<AddressEntity> findById(Integer integer);
}
