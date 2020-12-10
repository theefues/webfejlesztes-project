package inf.unideb.hu.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private int id;
    private int filmId;
    private int storeId;
    private Date lastUpdate;
}
