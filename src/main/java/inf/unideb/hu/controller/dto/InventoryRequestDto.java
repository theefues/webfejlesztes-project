package inf.unideb.hu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import java.sql.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDto {
    @Min(1)
    private int id;
    @Min(1)
    private int filmId;
    @Min(1)
    private int storeId;
    private Date lastUpdate;
}
