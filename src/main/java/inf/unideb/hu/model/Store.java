package inf.unideb.hu.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class Store {
    private int id;
    private int managerStaffId;
    private int addressId;
    private Date lastUpdate;
}
