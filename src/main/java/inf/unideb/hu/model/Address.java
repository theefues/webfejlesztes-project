package inf.unideb.hu.model;

import lombok.*;
import org.springframework.data.geo.Shape;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class Address {
    private int id;
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private String phone;
    private Date lastUpdate;

}
