package inf.unideb.hu.service;

import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaff();
    void recordStaff(Staff Staff) throws StaffAlreadyExistsException, UnknownAddressException, UnknownStoreException;
    void deleteStaffById(int id) throws UnknownStaffException;
    void updateStaff(Staff Staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;
    Staff getStaffById(int id) throws UnknownStaffException;
}
