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
public class CityRequestDto {
    @Min(1)
    private int id;
    private String city;
    @Min(1)
    private int countryId;
    private Date lastUpdate;
}
