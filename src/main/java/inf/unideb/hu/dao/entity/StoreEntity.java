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
@Table(name="store", schema="sakila")
public class StoreEntity {

    @Id
    @Column(name = "store_id")
    private int id;
    @Column(name = "manager_staff_id")
    private int managerStaffId;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;
    @Column(name = "last_update")
    private Date lastUpdate;
}