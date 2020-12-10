package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.AddressEntity;
import inf.unideb.hu.dao.entity.StoreEntity;
import inf.unideb.hu.exceptions.StoreAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Store;
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
public class StoreDaoImpl implements StoreDao {

    private final StoreRepository StoreRepository;
    private final AddressRepository AddressRepository;

    @Override
    public void createStore(Store Store) throws StoreAlreadyExistsException, UnknownAddressException {
        StoreEntity StoreEntity = null;
        try {
             StoreEntity = queryStore(Store.getId());

        }
        catch (UnknownStoreException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            StoreEntity = StoreEntity.builder()
                    .id(Store.getId())
                    .managerStaffId(Store.getManagerStaffId())
                    .addressId(queryAddress(Store.getAddressId()))
                    .lastUpdate(Store.getLastUpdate())
                    .build();
            StoreRepository.save(StoreEntity);
            log.trace("Recorded new Store: {}",StoreEntity);
            return;
        }
        StoreAlreadyExistsException StoreException = new StoreAlreadyExistsException("Store with id: "+Store.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+StoreException.getMessage(),StoreException.getClass());
        throw StoreException;

    }

    @Override
    public Collection<Store> readAll() {
        log.trace("Read all Stores");
        return StreamSupport.stream(StoreRepository.findAll().spliterator(),false)
                .map(entity -> new Store(
                        entity.getId(),
                        entity.getManagerStaffId(),
                        entity.getAddressId().getId(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Store readById(int id) throws UnknownStoreException {

        StoreEntity StoreEntity = queryStore(id);
        log.trace("Store found by id: {} Store: {}", id,StoreEntity);
        return  new Store(
                StoreEntity.getId(),
                StoreEntity.getManagerStaffId(),
                StoreEntity.getAddressId().getId(),
                StoreEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownStoreException {

        StoreEntity StoreEntity = queryStore(id);
        StoreRepository.deleteById(id);
        log.trace("Store deleted: {}", StoreEntity);

    }

    @Override
    public void update( Store Store) throws UnknownStoreException, UnknownAddressException {

        StoreEntity StoreEntity = queryStore(Store.getId());
        StoreEntity.setId(Store.getId());
                StoreEntity.setManagerStaffId(Store.getManagerStaffId());
                StoreEntity.setAddressId(queryAddress(Store.getAddressId()));
                StoreEntity.setLastUpdate(Store.getLastUpdate());
        StoreRepository.save(StoreEntity);
        log.trace("Store updated: {}", StoreEntity);
    }

    protected AddressEntity queryAddress(int AddressId) throws UnknownAddressException {
        Optional<AddressEntity> AddressEntity = AddressRepository.findById(AddressId);

        if (!AddressEntity.isPresent()) {
            UnknownAddressException AddressException = new UnknownAddressException("Address with id: " + Integer.toString(AddressId)+" not exists");
            log.error("Exception: {} thrown with message: "+AddressException.getMessage(),AddressException.getClass());
            throw AddressException;
        }
        return AddressEntity.get();
    }

    private StoreEntity queryStore(int id) throws UnknownStoreException {
        Optional<StoreEntity> StoreEntity = StoreRepository.findById(id);

        if (!StoreEntity.isPresent()) {
            UnknownStoreException StoreException = new UnknownStoreException("Store with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+StoreException.getMessage(),StoreException.getClass());
            throw StoreException;
        }
        return StoreEntity.get();
    }
}
