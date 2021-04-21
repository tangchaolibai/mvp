package com.hsb.mvpmsticket.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.mvpmsticket.model.entity.FilmSchedule;

public interface FilmScheduleMapper extends BaseMapper<FilmSchedule> {

	@Select({"select price from FILM_SCHEDULE where cinema_id = #{entityId} order by price ASC limit 1"})
	BigDecimal selLowestPrice(Integer entityId);

	@Select({"select film_id from FILM_SCHEDULE where cinema_id = #{cinemaId} GROUP BY film_id;"})
	List<Integer> findOnPlayingFilms(Integer cinemaId);
	
	@Select({"select film_id from FILM_SCHEDULE where date = #{dateNow} GROUP BY film_id;"})
	List<Integer> findAllOnplayingFilms(String dateNow);

}
