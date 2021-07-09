package coba.daily.you.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name= Tvshows.TABLE_NAME)
@Data
public class Tvshows {
    public static final String TABLE_NAME = "t_tvshows";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_tvshows_seq")

    private Integer id;

    private String tvshowsName;

    private String year;

    private String description;

    private String pictureUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private TvshowsCategory tvshowsCategory;
    @Column(name = "id_category", nullable = false)
    private Integer idCategory;


}

