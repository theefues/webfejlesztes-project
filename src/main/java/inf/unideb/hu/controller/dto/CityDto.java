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
public class CityDto {
    private int id;
    private String city;
    private int countryId;
    private Date lastUpdate;
}
