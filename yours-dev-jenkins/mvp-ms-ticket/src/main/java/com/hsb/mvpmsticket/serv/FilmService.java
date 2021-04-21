package com.hsb.mvpmsticket.serv;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsticket.model.entity.Film;
import com.hsb.mvpmsticket.model.payload.MovieDetailResponseData;
import com.hsb.mvpmsticket.model.payload.MovieResponseData;

public interface FilmService extends IService<Film> {

	List<MovieResponseData> getAllFilms();

	MovieDetailResponseData getFilmDetailById(Integer movieId);

	List<MovieResponseData> getAllUpComingFilms();

	void loadInitData();

}
