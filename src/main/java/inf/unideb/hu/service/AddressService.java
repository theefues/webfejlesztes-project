package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.AddressAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownCityException;
import inf.unideb.hu.model.Address;

import java.util.Collection;

public interface AddressService {

    Collection<Address> getAllAddress();
    void recordAddress(Address Address) throws AddressAlreadyExistsException, UnknownCityException;
    void deleteAddressById(int id) throws UnknownAddressException;
    void updateAddress(Address Address) throws UnknownAddressException, UnknownCityException;
    Address getAddressById(int id) throws UnknownAddressException;
}
