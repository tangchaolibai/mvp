package com.hsb.mvpmsticket.serv;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsticket.model.entity.Cinema;
import com.hsb.mvpmsticket.model.payload.CinemaResponseData;
import com.hsb.mvpmsticket.model.payload.CinemaScheduleResponseData;

public interface CinemaService extends IService<Cinema> {

	List<CinemaResponseData> listAllCinemas(String city);

	CinemaScheduleResponseData getCinemaDetail(Integer cinemaId);

	List<CinemaResponseData> listAllCinemasForOneFilm(String city, Integer filmId);

	void loadInitData();

}
