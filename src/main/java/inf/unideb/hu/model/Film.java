package inf.unideb.hu.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class Film {

    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;
    private int originalLanguageId;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;
}
