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
@Table(name="staff", schema="sakila")
public class StaffEntity {

    @Id
    @Column(name = "staff_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;
    @Column(name = "active")
    private int active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    private Date lastUpdate;
}