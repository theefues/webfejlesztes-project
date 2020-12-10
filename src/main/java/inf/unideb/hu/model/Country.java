package inf.unideb.hu.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class Country {

    private int id;
    private String country;
    private Date lastUpdate;
}
