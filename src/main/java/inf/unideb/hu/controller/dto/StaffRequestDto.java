package inf.unideb.hu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequestDto {
    @Min(1)
    private int id;
    private String firstName;
    private String lastName;
    @Min(1)
    private int addressId;
    private String email;
    @Min(1)
    private int storeId;
    private int active;
    private String username;
    private String password;
    private Date lastUpdate;
}
