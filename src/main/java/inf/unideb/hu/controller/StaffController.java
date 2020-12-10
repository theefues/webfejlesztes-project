package inf.unideb.hu.controller;

import inf.unideb.hu.controller.dto.StaffDto;
import inf.unideb.hu.controller.dto.StaffRequestDto;
import inf.unideb.hu.exceptions.StaffAlreadyExistsException;
import inf.unideb.hu.exceptions.UnknownAddressException;
import inf.unideb.hu.exceptions.UnknownStaffException;
import inf.unideb.hu.exceptions.UnknownStoreException;
import inf.unideb.hu.model.Staff;
import inf.unideb.hu.service.StaffService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/staff")
public class StaffController {

    private final StaffService service;

    @ApiOperation("Get all Staffs")
    @GetMapping("/getAll")
    public Collection<StaffDto> listStaffs(){

        return service.getAllStaff().stream()
                .map(model -> StaffDto.builder()
                        .id(model.getId())
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .addressId(model.getAddressId())
                        .email(model.getEmail())
                        .storeId(model.getStoreId())
                        .active(model.getActive())
                        .username(model.getUsername())
                        .password(model.getPassword())
                        .lastUpdate(model.getLastUpdate())
                        .build()).collect(Collectors.toList());

    }

    @ApiOperation("Create Staff")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String record(@Valid @RequestBody StaffRequestDto StaffDto)  {
        try{
            service.recordStaff(new Staff(
                    StaffDto.getId(),
                    StaffDto.getFirstName(),
                    StaffDto.getLastName(),
                    StaffDto.getAddressId(),
                    StaffDto.getEmail(),
                    StaffDto.getStoreId(),
                    StaffDto.getActive(),
                    StaffDto.getUsername(),
                    StaffDto.getPassword(),
                    StaffDto.getLastUpdate()
            ));
        }catch ( StaffAlreadyExistsException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (UnknownAddressException e) {
            e.printStackTrace();
        } catch (UnknownStoreException e) {
            e.printStackTrace();
        }
        return "Staff successfully created";
    }

    @ApiOperation("Get a Staff by id")
    @GetMapping("/getById/{id}")
    public StaffDto getStaffId(@PathVariable("id") int id)  {
        try{

            Staff Staff = service.getStaffById(id);
            StaffDto StaffDto = new StaffDto(
                    Staff.getId(),
                    Staff.getFirstName(),
                    Staff.getLastName(),
                    Staff.getAddressId(),
                    Staff.getEmail(),
                    Staff.getStoreId(),
                    Staff.getActive(),
                    Staff.getUsername(),
                    Staff.getPassword(),
                    Staff.getLastUpdate());

            return StaffDto;

        }catch (UnknownStaffException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiOperation("Delete Staff")
    @DeleteMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") int id)  {
        try{
            service.deleteStaffById(id);
        }catch (UnknownStaffException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Staff successfully deleted";
    }

    @ApiOperation("Update Staff")
    @PostMapping("/update")
    public String update(@Valid @RequestBody StaffRequestDto StaffDto) {
        try{
            service.updateStaff(new Staff(
                    StaffDto.getId(),
                    StaffDto.getFirstName(),
                    StaffDto.getLastName(),
                    StaffDto.getAddressId(),
                    StaffDto.getEmail(),
                    StaffDto.getStoreId(),
                    StaffDto.getActive(),
                    StaffDto.getUsername(),
                    StaffDto.getPassword(),
                    StaffDto.getLastUpdate()
            ));
        }catch (UnknownStaffException | UnknownAddressException | UnknownStoreException e){
            log.error("Exception: {} handled with message: "+e.getMessage(),e.getClass());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return "Staff successfully updated";
    }

}
