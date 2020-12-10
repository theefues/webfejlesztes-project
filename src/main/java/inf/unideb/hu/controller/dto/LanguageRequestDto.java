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
public class LanguageRequestDto {
    @Min(1)
    private int id;
    private String name;
    private Date lastUpdate;
}
