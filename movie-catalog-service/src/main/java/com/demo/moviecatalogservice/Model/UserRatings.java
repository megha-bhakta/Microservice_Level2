package com.demo.moviecatalogservice.Model;

import java.util.List;

public class UserRatings {
	
	private String userid;
	private List<Rating> rating;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Rating> getRating() {
		return rating;
	}
	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}
	public UserRatings(String userid, List<Rating> rating) {
		super();
		this.userid = userid;
		this.rating = rating;
	}
	public UserRatings() {
		super();
	}
	

}
