package inf.unideb.hu.dao;

import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface StaffDao {

    void createStaff(Staff Staff) throws StaffAlreadyExistsException, UnknownAddressException, UnknownStoreException;
    Collection<Staff> readAll();
    Staff readById(int id) throws UnknownStaffException;
    void deleteById(int id) throws UnknownStaffException;
    void update(Staff Staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;
}
