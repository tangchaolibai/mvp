package com.hsb.mvpmsticket.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get movie sessions response data.
 */
@ApiModel(description = "This entity is used to get movie sessions response data.")
@Validated
public class GetMovieSessionsResponseData {
	@JsonProperty("id")
	private Integer id = null;
	
	@JsonProperty("cinema_name")
	private String cinemaName = null;
	
	@JsonProperty("chinma_loc")
	private String cinemaLoc = null;
	
	@JsonProperty("movie_chi_name")
	private String movieChiName = null;
	
	@JsonProperty("movie_score")
	private Double movieScore = null;
	
	@JsonProperty("movie_time")
	private Integer movieTime = null;
	
	@JsonProperty("movie_title")
	private List<String> movieTitle = null;
	
	@JsonProperty("movie_main_performer")
	private String movieMainPerformer = null;
	
	@JsonProperty("watch_price")
	private Integer watchPrice = null;
	
	@JsonProperty("watch_time")
	private List<Time> watchTime = null;
	
	@JsonProperty("rest_tickets")
	private Integer restTickets = null;

	/**
	 * get sessions id
	 * @return sessionsId
	 **/
	@ApiModelProperty(value = "get sessions id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get cinema name
	 * @return cinemaName
	 **/
	@ApiModelProperty(value = "get cinema name")
	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	/**
	 * get cinema location
	 * @return cinemaLoc
	 **/
	@ApiModelProperty(value = "get cinema location")
	public String getCinemaLoc() {
		return cinemaLoc;
	}

	public void setCinemaLoc(String cinemaLoc) {
		this.cinemaLoc = cinemaLoc;
	}

	/**
	 * get movie chinese name
	 * @return movieChiName
	 **/
	@ApiModelProperty(value = "get movie chinese name")
	public String getMovieChiName() {
		return movieChiName;
	}

	public void setMovieChiName(String movieChiName) {
		this.movieChiName = movieChiName;
	}

	/**
	 * get movie score
	 * @return movieScore
	 **/
	@ApiModelProperty(value = "get movie score")
	public Double getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(Double movieScore) {
		this.movieScore = movieScore;
	}

	/**
	 * get movie time length
	 * @return movieTime
	 **/
	@ApiModelProperty(value = "get movie time length")
	public Integer getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(Integer movieTime) {
		this.movieTime = movieTime;
	}

	/**
	 * get movie title
	 * @return movieTitle
	 **/
	@ApiModelProperty(value = "get movie title")
	public List<String> getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(List<String> movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * get movie main performer
	 * @return movieMainPerformer
	 **/
	@ApiModelProperty(value = "get movie main performer")
	public String getMovieMainPerformer() {
		return movieMainPerformer;
	}

	public void setMovieMainPerformer(String movieMainPerformer) {
		this.movieMainPerformer = movieMainPerformer;
	}

	/**
	 * get watch price
	 * @return watchPrice
	 **/
	@ApiModelProperty(value = "get watch price")
	public Integer getWatchPrice() {
		return watchPrice;
	}

	public void setWatchPrice(Integer watchPrice) {
		this.watchPrice = watchPrice;
	}

	/**
	 * get watch movie time
	 * @return watchTime
	 **/
	@ApiModelProperty(value = "get watch movie time")
	public List<Time> getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(List<Time> watchTime) {
		this.watchTime = watchTime;
	}

	/**
	 * get rest of tickets
	 * @return restTickets
	 **/
	@ApiModelProperty(value = "get rest of tickets")
	public Integer getRestTickets() {
		return restTickets;
	}

	public void setRestTickets(Integer restTickets) {
		this.restTickets = restTickets;
	}

}
