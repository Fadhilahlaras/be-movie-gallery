package coba.daily.you.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= TvshowsCategory.TABLE_NAME)
@Data
public class TvshowsCategory {
    public static final String TABLE_NAME = "t_tvshows_category";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_tvshows_seq")
    @Column(name="id_category")
    private Integer id;

    @Column(name = "category_tvshows")
    private String categoryTvshows;

}

