package com.hsb.mvpmsticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.mvpmsticket.model.entity.Film;

public interface FilmMapper extends BaseMapper<Film> {

	@Select({"select * from FILM where release_date > #{dateNow}"})
	List<Film> getAllUpComingFilms(String dateNow);
	
}
