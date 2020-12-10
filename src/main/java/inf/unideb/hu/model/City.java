package inf.unideb.hu.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class City {
    private int id;
    private String city;
    private int countryId;
    private Date lastUpdate;
}
