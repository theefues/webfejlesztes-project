package inf.unideb.hu.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="country", schema="sakila")
public class CountryEntity {

    @Id
    @Column(name = "country_id")
    private int id;
    @Column(name = "country")
    private String country;
    @Column(name = "last_update")
    private Date lastUpdate;
}