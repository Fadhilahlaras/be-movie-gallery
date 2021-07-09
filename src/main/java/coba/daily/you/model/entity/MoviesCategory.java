package coba.daily.you.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= MoviesCategory.TABLE_NAME)
@Data
public class MoviesCategory {
    public static final String TABLE_NAME = "t_movies_category";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_movies_seq")
    @Column(name="id_category")
    private Integer id;

    @Column(name = "category_movies")
    private String categoryMovies;

}

