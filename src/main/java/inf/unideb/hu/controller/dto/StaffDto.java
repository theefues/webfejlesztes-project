package inf.unideb.hu.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    private int id;
    private String firstName;
    private String lastName;
    private int addressId;
    private String email;
    private int storeId;
    private int active;
    private String username;
    private String password;
    private Date lastUpdate;
}
