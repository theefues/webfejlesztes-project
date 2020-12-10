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
@Table(name="inventory", schema="sakila")
public class InventoryEntity {

    @Id
    @Column(name ="inventory_id")
    private int id;
    @ManyToOne
    @JoinColumn(name ="film_id")
    private FilmEntity filmId;
    @ManyToOne
    @JoinColumn(name ="store_id")
    private StoreEntity storeId;
    @Column(name ="last_update")
    private Date lastUpdate;
}
