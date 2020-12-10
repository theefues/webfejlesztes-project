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
@Table(name="film", schema="sakila")
public class FilmEntity {

    @Id
    @Column(name = "film_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private int releaseYear;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity languageId;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private LanguageEntity originalLanguageId;
    @Column(name = "rental_duration")
    private int rentalDuration;
    @Column(name = "rental_rate")
    private double rentalRate;
    @Column(name = "length")
    private int length;
    @Column(name = "replacement_cost")
    private double replacementCost;
    @Column(name = "rating")
    private String rating;
    @Column(name = "special_features")
    private String specialFeatures;
    @Column(name = "last_update")
    private Date lastUpdate;
}