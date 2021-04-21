package com.hsb.mvpmsticket.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsticket.api.MvpMsTicketModuleApi;
import com.hsb.mvpmsticket.exception.MvpMsTicketException;
import com.hsb.mvpmsticket.model.payload.BuyTicketsResponseData;
import com.hsb.mvpmsticket.model.payload.CinemaResponseData;
import com.hsb.mvpmsticket.model.payload.CinemaScheduleResponseData;
import com.hsb.mvpmsticket.model.payload.CityListResponseListData;
import com.hsb.mvpmsticket.model.payload.MovieDetailResponseData;
import com.hsb.mvpmsticket.model.payload.MovieResponseData;
import com.hsb.mvpmsticket.model.payload.response.BaseResponse;
import com.hsb.mvpmsticket.model.payload.response.BuyTicketsResponse;
import com.hsb.mvpmsticket.model.payload.response.CinemaDetailResponse;
import com.hsb.mvpmsticket.model.payload.response.CityListResponse;
import com.hsb.mvpmsticket.model.payload.response.GetCinemasResponse;
import com.hsb.mvpmsticket.model.payload.response.GetMovieDetailResponse;
import com.hsb.mvpmsticket.model.payload.response.GetMoviesResponse;
import com.hsb.mvpmsticket.model.payload.response.ResponseResult;
import com.hsb.mvpmsticket.serv.CinemaService;
import com.hsb.mvpmsticket.serv.CitySerialNumberService;
import com.hsb.mvpmsticket.serv.FilmScheduleService;
import com.hsb.mvpmsticket.serv.FilmService;

@RestController
public class MvpMsTicketModuleApiController implements MvpMsTicketModuleApi {

	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private FilmScheduleService filmScheduleService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CitySerialNumberService citySerialNumberService;

	@Override
	public ResponseEntity<GetMoviesResponse> getFilms() {
		List<MovieResponseData> data = filmService.getAllFilms();
		GetMoviesResponse response = new GetMoviesResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		response.total(data.size());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetMovieDetailResponse> getMovieDetail(Integer id) {
		MovieDetailResponseData data = filmService.getFilmDetailById(id);
		GetMovieDetailResponse response = new GetMovieDetailResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetMoviesResponse> getUpComingFilms() {
		List<MovieResponseData> data = filmService.getAllUpComingFilms();
		GetMoviesResponse response = new GetMoviesResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		response.total(data.size());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetCinemasResponse> getCinemas(String city) {
		List<CinemaResponseData> data = cinemaService.listAllCinemas(city);
		GetCinemasResponse response = new GetCinemasResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		response.total(data.size());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CinemaDetailResponse> getCinemaDetail(Integer cinemaId) {
		CinemaScheduleResponseData data = cinemaService.getCinemaDetail(cinemaId);
		CinemaDetailResponse response = new CinemaDetailResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetCinemasResponse> getCinemasForOneFilm(String city, Integer filmId) {
		List<CinemaResponseData> data = cinemaService.listAllCinemasForOneFilm(city, filmId);
		GetCinemasResponse response = new GetCinemasResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		response.total(data.size());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BuyTicketsResponse> buyTickets(Integer scheduleId, Integer count) throws MvpMsTicketException {
		BuyTicketsResponseData data = filmScheduleService.buy(scheduleId, count);
		BuyTicketsResponse response = new BuyTicketsResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> loadInitData() {
		filmService.loadInitData();
		cinemaService.loadInitData();
		filmScheduleService.loadInitData();
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CityListResponse> findCityList() {
		CityListResponseListData data = citySerialNumberService.findAll();
		CityListResponse response = new CityListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.data(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> addCity(String city) throws MvpMsTicketException {
		citySerialNumberService.addCity(city);
		BaseResponse response = new CityListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}


}
