package com.hsb.mvpmsticket.serv.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsticket.exception.MvpMsTicketException;
import com.hsb.mvpmsticket.exception.MvpMsTicketExceptionCode;
import com.hsb.mvpmsticket.mapper.CinemaMapper;
import com.hsb.mvpmsticket.mapper.FilmMapper;
import com.hsb.mvpmsticket.mapper.FilmScheduleMapper;
import com.hsb.mvpmsticket.model.entity.Cinema;
import com.hsb.mvpmsticket.model.entity.Film;
import com.hsb.mvpmsticket.model.entity.FilmSchedule;
import com.hsb.mvpmsticket.model.payload.BuyTicketsResponseData;
import com.hsb.mvpmsticket.serv.FilmScheduleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FilmScheduleServiceImpl extends ServiceImpl<FilmScheduleMapper, FilmSchedule> implements FilmScheduleService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FilmScheduleServiceImpl.class);
	
	@Autowired
	private CinemaMapper cinemaMapper;
	
	@Autowired
	private FilmMapper filmMapper;
	
	@Override
	public BuyTicketsResponseData buy(Integer scheduleId, Integer count) throws MvpMsTicketException {
		FilmSchedule schedule = getById(scheduleId);
		if(schedule.ticketsLeft() - count < 0) {
			throw new MvpMsTicketException(MvpMsTicketExceptionCode.ERROR_001_01);
		}
		schedule.ticketsLeft(schedule.ticketsLeft() - count);
		updateById(schedule);
		BuyTicketsResponseData data = new BuyTicketsResponseData();
		Cinema cinema = cinemaMapper.selectById(schedule.cinemaId());
		Film film = filmMapper.selectById(schedule.filmId());
		data.cinemaLoc(cinema.address())
			.cinemaName(cinema.name())
			.count(count)
			.id(scheduleId)
			.movieChiName(film.chineseName())
			.price(schedule.price())
			.startTime(schedule.startTime())
			.currency(schedule.currency());
		return data;
	}

	@Override
	public void loadInitData() {
		list().forEach(e -> removeById(e.entityId()));
		QueryWrapper<Film> queryWrapperFilm = new QueryWrapper<>();
		List<Film> filmList = filmMapper.selectList(queryWrapperFilm);
		QueryWrapper<Cinema> queryWrapperCinema = new QueryWrapper<>();
		List<Cinema> cinemaList = cinemaMapper.selectList(queryWrapperCinema);
		FilmSchedule filmSchedule = new FilmSchedule().cinemaId(cinemaList.get(0).entityId()).date(LocalDate.now())
				.endTime(/*
							 * DateUtil.offset(DateUtil.date(), DateField.MINUTE, 15 +
							 * filmList.get(0).duration())
							 */LocalDate.now())
				.filmId(filmList.get(0).entityId()).hall("5号激光厅").price(BigDecimal.valueOf(38))
				.startTime(/* DateUtil.offset(DateUtil.date(), DateField.MINUTE, 15) */LocalDate.now()).ticketsLeft(50).currency("CNY");
		save(filmSchedule);
		filmSchedule.price(new BigDecimal(35)).hall("4号杜比全景厅");
		save(filmSchedule);
		filmSchedule.filmId(filmList.get(1).entityId()).hall("3号激光厅");
		save(filmSchedule);
		filmSchedule.cinemaId(cinemaList.get(1).entityId()).date(LocalDate.now())
				.endTime(/*
							 * DateUtil.offset(DateUtil.date(), DateField.MINUTE, 15 +
							 * filmList.get(1).duration())
							 */LocalDate.now())
				.filmId(filmList.get(1).entityId()).hall("5号激光厅").price(BigDecimal.valueOf(38))
				.startTime(/* DateUtil.offset(DateUtil.date(), DateField.MINUTE, 15) */LocalDate.now()).ticketsLeft(50);
		save(filmSchedule);
	}

}
