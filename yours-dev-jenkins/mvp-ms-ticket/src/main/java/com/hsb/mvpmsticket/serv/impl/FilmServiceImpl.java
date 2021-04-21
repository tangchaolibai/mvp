package com.hsb.mvpmsticket.serv.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsticket.mapper.FilmMapper;
import com.hsb.mvpmsticket.mapper.FilmScheduleMapper;
import com.hsb.mvpmsticket.model.entity.Film;
import com.hsb.mvpmsticket.model.payload.MovieDetailResponseData;
import com.hsb.mvpmsticket.model.payload.MovieResponseData;
import com.hsb.mvpmsticket.serv.FilmService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
	
	@Autowired
	private FilmMapper mapper;
	
	@Autowired
	private FilmScheduleMapper filmScheduleMapper;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public List<MovieResponseData> getAllFilms() {
		return filmScheduleMapper.findAllOnplayingFilms(DateUtil.format(DateUtil.date(), DATE_FORMAT))
				.stream().map(id -> getById(id)).map(film -> toMovieResponseData(film))
				.collect(Collectors.toList());
	}

	@Override
	public MovieDetailResponseData getFilmDetailById(Integer id) {
		Film film = getById(id);
		return toMovieDetailResponseData(film);
	}

	@Override
	public List<MovieResponseData> getAllUpComingFilms() {
		return mapper.getAllUpComingFilms(DateUtil.format(DateUtil.date(), DATE_FORMAT))
				.stream().map(e -> toMovieResponseData(e)).collect(Collectors.toList());
	}

	private MovieResponseData toMovieResponseData(Film film) {
		MovieResponseData data = new MovieResponseData();
		data.id(film.entityId())		
			.director(film.directorName())
			.image(film.image())
			.likeAmount(film.likeAmount())
			.chineseName(film.chineseName())
			.releaseDate(film.releaseDate())
			.score(film.score())
			.mainActors(StrUtil.split(film.mainActor(), ','))
			.demention(film.demention());
		return data;
	}
	
	private MovieDetailResponseData toMovieDetailResponseData(Film film) {
		MovieDetailResponseData data = new MovieDetailResponseData();
		data.introduction(film.introduction())
			.region(film.region())
			.type(film.type())
			.duration(film.duration())
			.id(film.entityId())
			.director(film.directorName())
			.image(film.image())
			.likeAmount(film.likeAmount())
			.chineseName(film.chineseName())
			.releaseDate(film.releaseDate())
			.score(film.score())
			.mainActors(StrUtil.split(film.mainActor(), ','))
			.demention(film.demention());
		return data;
	}

	@Override
	public void loadInitData() {
		List<Film> list = list();
		list.forEach(e -> removeById(e.entityId()));
		Film film = new Film()
				.chineseName("误杀")
				.demention("2D")
				.directorName("柯文利")
				.duration(112)
				.englishName("Sheep Without a Shepherd")
				.image("null")
				.introduction("李维杰（肖央 饰）与妻子阿玉（谭卓 饰）打拼多年，膝下育有两个女儿。一个雨夜，一场意外，打破了这个家庭的宁静。而李维杰作为一个父亲，为了保全自己的家人，他不顾一切地决定瞒天过海……")
				.language("国语")
				.likeAmount(BigDecimal.valueOf(77.3))
				.mainActor("肖央,谭卓,陈冲")
				.region("中国大陆")
				.releaseDate(/* DateUtil.offset(DateUtil.date(), DateField.DAY_OF_MONTH, -5) */LocalDate.now())
				.score(BigDecimal.valueOf(7.3))
				.type("剧情,犯罪");
		save(film);
		film.chineseName("唐人街探案3")
				.demention("2D")
				.directorName("陈思诚")
				.duration(136)
				.englishName("Detective Chinatown 3")
				.image("null")
				.introduction("继曼谷、纽约之后，东京再出大案。唐人街神探唐仁（王宝强 饰）、秦风（刘昊然 饰）受侦探野田昊（妻夫木聪 饰）的邀请前往破案。“CRIMASTER世界侦探排行榜”中的侦探们闻讯后也齐聚东京，加入挑战，而排名第一Q的现身，让这个大案更加扑朔迷离，一场亚洲最强神探之间的较量即将爆笑展开……")
				.language("国语")
				.likeAmount(BigDecimal.valueOf(250.95))
				.mainActor("王宝强,刘昊然")
				.region("中国大陆")
				.releaseDate(/* DateUtil.offset(DateUtil.date(), DateField.DAY_OF_MONTH, -3) */LocalDate.now())
				.score(BigDecimal.valueOf(9.3))
				.type("喜剧,悬疑");
		save(film);
		film.chineseName("姜子牙")
				.demention("3D")
				.directorName("程腾")
				.duration(110)
				.englishName("JIANG ZIYA：Legend Of Deification")
				.image("null")
				.introduction("昆仑弟子姜子牙（郑希 配音）率领众神伐纣，赢得封神大战胜利。即将受封成为众神之长的他，却因一时过失引得昆仑大乱，从此被贬北海，为世人所唾弃。十年后，因一个契机，姜子牙踏上重回昆仑的旅途。在战后的废墟之上，他重新找到自我，也洞悉了十年前的一切真相。")
				.language("国语")
				.likeAmount(BigDecimal.valueOf(104.79))
				.mainActor("郑希,杨宁")
				.region("中国大陆")
				.releaseDate(/* DateUtil.offset(DateUtil.date(), DateField.DAY_OF_MONTH, 3) */LocalDate.now())
				.score(null)
				.type("动画,冒险");
		save(film);
	}

}
