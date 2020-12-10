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
@Table(name="language", schema="sakila")
public class LanguageEntity {

    @Id
    @Column(name = "language_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update")
    private Date lastUpdate;
}