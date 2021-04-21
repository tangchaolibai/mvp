package com.hsb.mvpmsticket.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get movie detail response data.
 */
@ApiModel(description = "This entity is used to get movie detail response data.")
@Validated
public class GetMovieDetailResponseData {
	@JsonProperty("id")
	private Integer id = null;
	
	@JsonProperty("movie_chi_name")
	private String movieChiName = null;
	
	@JsonProperty("movie_en_name")
	private String movieEnName = null;
	
	@JsonProperty("movie_title")
	private List<String> movieTitle = null;
	
	@JsonProperty("movie_des")
	private String movieDes = null;
	
	@JsonProperty("movie_time")
	private Integer movieTime = null;
	
	@JsonProperty("movie_img_path")
	private String movieImgPath = null;
	
	@JsonProperty("movie_score")
	private Double movieScore = null;
	
	/**
	 * get movie id
	 * @return movieId
	 **/
	@ApiModelProperty(value = "get movie id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get movie Chinese name
	 * @return movieChiName
	 **/
	@ApiModelProperty(value = "get movie Chinese name")
	public String getMovieChiName() {
		return movieChiName;
	}

	public void setMovieChiName(String movieChiName) {
		this.movieChiName = movieChiName;
	}

	/**
	 * get movie English name
	 * @return movieEnName
	 **/
	@ApiModelProperty(value = "get movie English name")
	public String getMovieEnName() {
		return movieEnName;
	}

	public void setMovieEnName(String movieEnName) {
		this.movieEnName = movieEnName;
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
	 * get movie description
	 * @return movieDes
	 **/
	@ApiModelProperty(value = "get movie description")
	public String getMovieDes() {
		return movieDes;
	}

	public void setMovieDes(String movieDes) {
		this.movieDes = movieDes;
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
	 * get movie image path
	 * @return movieImgPath
	 **/
	@ApiModelProperty(value = "get movie image path")
	public String getMovieImgPath() {
		return movieImgPath;
	}

	public void setMovieImgPath(String movieImgPath) {
		this.movieImgPath = movieImgPath;
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
	
}
