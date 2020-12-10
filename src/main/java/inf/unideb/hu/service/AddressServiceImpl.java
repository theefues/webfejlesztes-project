package inf.unideb.hu.service;

import inf.unideb.hu.dao.AddressDao;
import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressDao addressDao;

    @Override
    public Collection<Address> getAllAddress() {
        return addressDao.readAll();
    }

    @Override
    public void recordAddress(Address address) throws AddressAlreadyExistsException, UnknownCityException {
        addressDao.createAddress(address);
    }

    @Override
    public Address getAddressById(int id) throws UnknownAddressException {
        return addressDao.readById(id);
    }

    @Override
    public void deleteAddressById(int id) throws UnknownAddressException {
        addressDao.deleteById(id);
    }

    @Override
    public void updateAddress(Address address) throws UnknownAddressException, UnknownCityException {
        addressDao.update(address);
    }
}
