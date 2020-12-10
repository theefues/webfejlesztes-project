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
@Table(name="city", schema="sakila")
public class CityEntity {

    @Id
    @Column(name = "city_id")
    private int id;
    @Column(name = "city")
    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryId;
    @Column(name = "last_update")
    private Date lastUpdate;
}