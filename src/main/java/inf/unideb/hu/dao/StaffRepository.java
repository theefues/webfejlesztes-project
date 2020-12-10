package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StaffRepository extends CrudRepository<StaffEntity,Integer> {

    @Override
    Optional<StaffEntity> findById(Integer integer);
}
