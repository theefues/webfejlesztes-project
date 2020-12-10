package inf.unideb.hu.model;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private int addressId;
    private String email;
    private int storeId;
    private int active;
    private String username;
    private String password;
    private Date lastUpdate;
}
