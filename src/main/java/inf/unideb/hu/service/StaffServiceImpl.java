package inf.unideb.hu.service;

import inf.unideb.hu.dao.StaffDao;
import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffDao StaffDao;

    @Override
    public Collection<Staff> getAllStaff() {
        return StaffDao.readAll();
    }

    @Override
    public void recordStaff(Staff Staff) throws StaffAlreadyExistsException, UnknownAddressException, UnknownStoreException {
        StaffDao.createStaff(Staff);
    }

    @Override
    public Staff getStaffById(int id) throws UnknownStaffException {
        return StaffDao.readById(id);
    }

    @Override
    public void deleteStaffById(int id) throws UnknownStaffException {
        StaffDao.deleteById(id);
    }

    @Override
    public void updateStaff(Staff Staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        StaffDao.update(Staff);
    }
}
