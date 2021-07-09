package coba.daily.you.model.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TvshowsDto {
    private Integer id;
    private @NotNull String tvshowsName;
    private @NotNull String year;
    private @NotNull String description;
    private @NotNull String pictureUrl;
    private @NotNull Integer idCategory;
    private @NotNull String categoryTvshows;


}
