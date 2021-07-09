package coba.daily.you.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name= Movies.TABLE_NAME)
@Data
public class Movies {
    public static final String TABLE_NAME = "t_movies";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_movies_seq")
    private Integer id;

    private String moviesName;

    private Integer year;

    private String description;

    private String pictureUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private MoviesCategory moviesCategory;
    @Column(name = "id_category", nullable = false)
    private Integer idCategory;


}

