package com.hsb.mvpmsticket.serv.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsticket.mapper.CinemaMapper;
import com.hsb.mvpmsticket.mapper.FilmMapper;
import com.hsb.mvpmsticket.mapper.FilmScheduleMapper;
import com.hsb.mvpmsticket.model.entity.Cinema;
import com.hsb.mvpmsticket.model.entity.Film;
import com.hsb.mvpmsticket.model.entity.FilmSchedule;
import com.hsb.mvpmsticket.model.payload.CinemaResponseData;
import com.hsb.mvpmsticket.model.payload.CinemaScheduleResponseData;
import com.hsb.mvpmsticket.model.payload.FilmSchedulePayload;
import com.hsb.mvpmsticket.model.payload.Schedule;
import com.hsb.mvpmsticket.serv.CinemaService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements CinemaService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CinemaServiceImpl.class);
	
	@Autowired
	private FilmScheduleMapper filmScheduleMapper;
	
	@Autowired
	private FilmMapper filmMapper;
	
	@Override
	public List<CinemaResponseData> listAllCinemas(String city) {
		QueryWrapper<Cinema> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("location_city", city);
		List<Cinema> list = list(queryWrapper);
		List<CinemaResponseData> datas = list.stream().map(e -> toCinemaResponseData(e)).collect(Collectors.toList());
		return datas;
	}
	
	@Override
	public CinemaScheduleResponseData getCinemaDetail(Integer cinemaId) {
		Cinema cinema = getById(cinemaId);
		CinemaScheduleResponseData data = new CinemaScheduleResponseData();
		data.id(cinema.entityId()).address(cinema.address()).cgsEnable(cinema.cgsEnable()).dolbyEnable(cinema.dolbyEnable())
			.endorseEnable(cinema.endorseEnable()).free3dGlassesEnable(cinema.free3dEnable()).imaxEnable(cinema.imaxEnable())
			.refundEnable(cinema.refundEnable()).name(cinema.name());
		List<Integer> filmIdList =  filmScheduleMapper.findOnPlayingFilms(cinemaId);
		data.filmTotal(filmIdList.size());
		List<FilmSchedulePayload> filmSchedulePayloadList = new ArrayList<>();
		for(Integer id : filmIdList) {
			Film film = filmMapper.selectById(id);
			FilmSchedulePayload filmSchedulePayload = new FilmSchedulePayload();
			filmSchedulePayload.chineseName(film.chineseName())
				.duration(film.duration())
				.filmId(film.entityId())
				.score(film.score().toString())
				.iamge(film.image())
				.type(Arrays.asList(film.type().split(",")))
				.mainActors(Arrays.asList(film.mainActor().split(",")));
			QueryWrapper<FilmSchedule> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("film_id", id);
			List<FilmSchedule> sceduList = filmScheduleMapper.selectList(queryWrapper);
			filmSchedulePayload.total(sceduList.size());
			
			List<Schedule> scheduleList = new ArrayList<>();
			for(FilmSchedule schedule : sceduList) {
				Schedule schedule2 = new Schedule();
				schedule2.endTime(schedule.endTime())
					.filmScheduleId(schedule.entityId())
					.hall(schedule.hall())
					.price(schedule.price())
					.startTime(schedule.startTime())
					.ticketsLeft(schedule.ticketsLeft())
					.currency(schedule.currency());
				scheduleList.add(schedule2);
			}
			filmSchedulePayload.scheduleList(scheduleList);
			filmSchedulePayloadList.add(filmSchedulePayload);
		}
		data.playingFilmList(filmSchedulePayloadList);
		return data;
	}
	
	@Override
	public List<CinemaResponseData> listAllCinemasForOneFilm(String city, Integer filmId) {
		List<CinemaResponseData> data = listAllCinemas(city);
		return data.stream().filter(e -> withFilm(e, filmId)).collect(Collectors.toList());
	}

	private boolean withFilm(CinemaResponseData cinemaResponseData, Integer filmId) {
		Integer cinemaId = cinemaResponseData.id();
		QueryWrapper<FilmSchedule> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("cinema_id", cinemaId).eq("film_id", filmId);
		int count = filmScheduleMapper.selectCount(queryWrapper);
		return count > 0 ;
	}

	private CinemaResponseData toCinemaResponseData(Cinema cinema) {
		CinemaResponseData data = new CinemaResponseData();
		data.address(cinema.address()).cgsEnable(cinema.cgsEnable()).dolbyEnable(cinema.dolbyEnable())
			.endorseEnable(cinema.endorseEnable()).free3dGlassesEnable(cinema.free3dEnable())
			.id(cinema.entityId()).imaxEnable(cinema.imaxEnable()).locationBusiness(cinema.locationBusinessDistrict())
			.locationCity(cinema.locationCity()).locationRegion(cinema.locationRegion()).name(cinema.name())
			.refundEnable(cinema.refundEnable());
		BigDecimal lowestPrice = filmScheduleMapper.selLowestPrice(cinema.entityId());
		data.lowestFare(lowestPrice);
		return data;
	}

	@Override
	public void loadInitData() {
		list().forEach(e -> removeById(e.entityId()));
		Cinema cinema = new Cinema().address("南山区西丽街留仙大道1369号众冠西郡园二楼（西丽地铁站C口）")
				.cgsEnable(true).dolbyEnable(true).endorseEnable(true).free3dEnable(true).imaxEnable(true).refundEnable(true)
				.locationBusinessDistrict("西丽").locationCity("深圳").locationRegion("南山").name("中影荟星激光影城（西丽店）");
		save(cinema);
		cinema.address("宝安区沙井街道大埔路惠尔乐百货3楼")
				.cgsEnable(true).dolbyEnable(true).endorseEnable(true).free3dEnable(true).imaxEnable(true).refundEnable(true)
				.locationBusinessDistrict("沙井").locationCity("深圳").locationRegion("宝安").name("集鸿发国际影城");
		save(cinema);
	}

}
