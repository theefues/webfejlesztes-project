package inf.unideb.hu.dao;

import inf.unideb.hu.dao.entity.AddressEntity;
import inf.unideb.hu.dao.entity.CityEntity;
import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;
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
public class AddressDaoImpl implements AddressDao {

    private final AddressRepository AddressRepository;
    private final CityRepository CityRepository;

    @Override
    public void createAddress(Address Address) throws AddressAlreadyExistsException, UnknownCityException {
        AddressEntity AddressEntity = null;
        try {
             AddressEntity = queryAddress(Address.getId());

        }
        catch (UnknownAddressException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            AddressEntity = AddressEntity.builder()
                    .id(Address.getId())
                    .address(Address.getAddress())
                    .address2(Address.getAddress2())
                    .district(Address.getDistrict())
                    .cityId(queryCity(Address.getCityId()))
                    .postalCode(Address.getPostalCode())
                    .phone(Address.getPhone())
                    .lastUpdate(Address.getLastUpdate())
                    .build();
            AddressRepository.save(AddressEntity);
            log.trace("Recorded new Address: {}",AddressEntity);
            return;
        }
        AddressAlreadyExistsException AddressException = new AddressAlreadyExistsException("Address with id: "+Address.getId()+" already exists.");
        log.error("Exception: {} thrown with message: "+AddressException.getMessage(),AddressException.getClass());
        throw AddressException;

    }

    protected CityEntity queryCity(int CityId) throws UnknownCityException {
        Optional<CityEntity> CityEntity = CityRepository.findById(CityId);

        if (!CityEntity.isPresent()) {
            UnknownCityException CityException = new UnknownCityException("City with id: " + Integer.toString(CityId)+" not exists");
            log.error("Exception: {} thrown with message: "+CityException.getMessage(),CityException.getClass());
            throw CityException;
        }
        return CityEntity.get();
    }

    @Override
    public Collection<Address> readAll() {
        log.trace("Read all Addresss");
        return StreamSupport.stream(AddressRepository.findAll().spliterator(),false)
                .map(entity -> new Address(
                        entity.getId(),
                        entity.getAddress(),
                        entity.getAddress2(),
                        entity.getDistrict(),
                        entity.getCityId().getId(),
                        entity.getPostalCode(),
                        entity.getPhone(),
                        entity.getLastUpdate()
                )).collect(Collectors.toList());
    }

    @Override
    public Address readById(int id) throws UnknownAddressException {

        AddressEntity AddressEntity = queryAddress(id);
        log.trace("Address found by id: {} Address: {}", id,AddressEntity);
        return  new Address(
                AddressEntity.getId(),
                AddressEntity.getAddress(),
                AddressEntity.getAddress2(),
                AddressEntity.getDistrict(),
                AddressEntity.getCityId().getId(),
                AddressEntity.getPostalCode(),
                AddressEntity.getPhone(),
                AddressEntity.getLastUpdate());

    }




    @Override
    public void deleteById(int id) throws UnknownAddressException {

        AddressEntity AddressEntity = queryAddress(id);
        AddressRepository.deleteById(id);
        log.trace("Address deleted: {}", AddressEntity);

    }

    @Override
    public void update( Address address) throws UnknownAddressException, UnknownCityException {

        AddressEntity addressEntity = queryAddress(address.getId());
        addressEntity.setId(address.getId());
        addressEntity.setAddress(address.getAddress());
        addressEntity.setAddress2(address.getAddress2());
        addressEntity.setDistrict(address.getDistrict());
        addressEntity.setCityId(queryCity(address.getCityId()));
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setPhone(address.getPhone());
        AddressRepository.save(addressEntity);
        log.trace("Address updated: {}", addressEntity);
    }

    private AddressEntity queryAddress(int id) throws UnknownAddressException {
        Optional<AddressEntity> AddressEntity = AddressRepository.findById(id);

        if (!AddressEntity.isPresent()) {
            UnknownAddressException AddressException = new UnknownAddressException("Address with id: " + Integer.toString(id)+" not exists.");
            log.error("Exception: {} thrown with message: "+AddressException.getMessage(),AddressException.getClass());
            throw AddressException;
        }
        return AddressEntity.get();
    }
}
