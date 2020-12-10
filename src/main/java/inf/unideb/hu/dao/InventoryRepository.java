package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.InventoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface InventoryRepository extends CrudRepository<InventoryEntity,Integer> {

    @Override
    Optional<InventoryEntity> findById(Integer integer);
}
