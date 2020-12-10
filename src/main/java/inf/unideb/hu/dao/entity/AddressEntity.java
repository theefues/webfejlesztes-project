package inf.unideb.hu.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="address", schema="sakila")
public class AddressEntity {

    @Id
    @Column(name ="address_id")
    private int id;
    @Column(name ="address")
    private String address;
    @Column(name ="address2")
    private String address2;
    @Column(name ="district")
    private String district;
    @ManyToOne
    @JoinColumn(name ="city_id")
    private CityEntity cityId;
    @Column(name ="postal_code")
    private String postalCode;
    @Column(name ="phone")
    private String phone;
    @Column(name ="last_update")
    private Date lastUpdate;
}
