package com.demo.movieinfoservice.Model;

public class MovieSummary {
	
	private String id;
	private String original_title;
	private String overview;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return original_title;
	}
	public void setTitle(String title) {
		this.original_title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public MovieSummary(String id, String title, String overview) {
		super();
		this.id = id;
		this.original_title = title;
		this.overview = overview;
	}
	public MovieSummary() {
		super();
	}
	
	

}
