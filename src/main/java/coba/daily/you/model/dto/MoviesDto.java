package coba.daily.you.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MoviesDto {
    private Integer id;
    private @NotNull String moviesName;
    private @NotNull Integer year;
    private @NotNull String description;
    private @NotNull String pictureUrl;
    private @NotNull Integer idCategory;
    private @NotNull String categoryMovies;


}