package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.AddressEntity;
import inf.unideb.hu.dao.entity.StaffEntity;
import inf.unideb.hu.dao.entity.StoreEntity;
import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;
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
public class StaffDaoImpl implements StaffDao {

    private final StaffRepository StaffRepository;
    private final AddressRepository AddressRepository;
    private final StoreRepository StoreRepository;
    @Override
    public void createStaff(Staff Staff) throws StaffAlreadyExistsException, UnknownAddressException, UnknownStoreException {
        StaffEntity StaffEntity = null;
        try {
             StaffEntity = queryStaff(Staff.getId());

        }
        catch (UnknownStaffException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            StaffEntity = StaffEntity.builder()
                    .id(Staff.getId())
                    .firstName(Staff.getFirstName())
                    .lastName(Staff.getLastName())
                    .addressId(queryAddress(Staff.getAddressId()))
                    .email(Staff.getEmail())
                    .storeId(queryStore(Staff.getStoreId()))
                    .active(Staff.getActive())
                    .username(Staff.getUsername())
                    .password(Staff.getPassword())
                    .lastUpdate(Staff.getLastUpdate())
                    .build();
            StaffRepository.save(StaffEntity);
            log.trace("Recorded new Staff: {}",StaffEntity);
            return;
        }
        StaffAlreadyExistsException StaffException = new StaffAlreadyExistsException("Staff with id: "+Staff.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+StaffException.getMessage(),StaffException.getClass());
        throw StaffException;

    }

    @Override
    public Collection<Staff> readAll() {
        log.trace("Read all Staffs");
        return StreamSupport.stream(StaffRepository.findAll().spliterator(),false)
                .map(entity -> new Staff(
                        entity.getId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getAddressId().getId(),
                        entity.getEmail(),
                        entity.getStoreId().getId(),
                        entity.getActive(),
                        entity.getUsername(),
                        entity.getPassword(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Staff readById(int id) throws UnknownStaffException {

        StaffEntity StaffEntity = queryStaff(id);
        log.trace("Staff found by id: {} Staff: {}", id,StaffEntity);
        return  new Staff(
                StaffEntity.getId(),
                StaffEntity.getFirstName(),
                StaffEntity.getLastName(),
                StaffEntity.getAddressId().getId(),
                StaffEntity.getEmail(),
                StaffEntity.getStoreId().getId(),
                StaffEntity.getActive(),
                StaffEntity.getUsername(),
                StaffEntity.getPassword(),
                StaffEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownStaffException {

        StaffEntity StaffEntity = queryStaff(id);
        StaffRepository.deleteById(id);
        log.trace("Staff deleted: {}", StaffEntity);

    }

    @Override
    public void update( Staff Staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException {

        StaffEntity StaffEntity = queryStaff(Staff.getId());
        StaffEntity.setId(Staff.getId());
                StaffEntity.setFirstName(Staff.getFirstName());
                StaffEntity.setLastName(Staff.getLastName());
                StaffEntity.setAddressId(queryAddress(Staff.getAddressId()));
                StaffEntity.setEmail(Staff.getEmail());
                StaffEntity.setStoreId(queryStore(Staff.getStoreId()));
                StaffEntity.setActive(Staff.getActive());
                StaffEntity.setUsername(Staff.getUsername());
                StaffEntity.setPassword(Staff.getPassword());
                StaffEntity.setLastUpdate(Staff.getLastUpdate());
        StaffRepository.save(StaffEntity);
        log.trace("Staff updated: {}", StaffEntity);
    }

    protected StoreEntity queryStore(int StoreId) throws UnknownStoreException {
        Optional<StoreEntity> StoreEntity = StoreRepository.findById(StoreId);

        if (!StoreEntity.isPresent()) {
            UnknownStoreException StoreException = new UnknownStoreException("Store with id: " + Integer.toString(StoreId)+" not exists");
            log.error("Exception: {} thrown with message: "+StoreException.getMessage(),StoreException.getClass());
            throw StoreException;
        }
        return StoreEntity.get();
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

    private StaffEntity queryStaff(int id) throws UnknownStaffException {
        Optional<StaffEntity> StaffEntity = StaffRepository.findById(id);

        if (!StaffEntity.isPresent()) {
            UnknownStaffException StaffException = new UnknownStaffException("Staff with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+StaffException.getMessage(),StaffException.getClass());
            throw StaffException;
        }
        return StaffEntity.get();
    }
}
