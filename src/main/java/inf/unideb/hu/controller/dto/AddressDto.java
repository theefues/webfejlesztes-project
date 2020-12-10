package inf.unideb.hu.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.geo.Shape;

import javax.persistence.Column;
import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private int id;
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private String phone;
    private Date lastUpdate;
}
