package inf.unideb.hu.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class Inventory {
    private int id;
    private int filmId;
    private int storeId;
    private Date lastUpdate;
}
