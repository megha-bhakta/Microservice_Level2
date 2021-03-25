package com.demo.moviecatalogservice.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.moviecatalogservice.Model.CatalogItem;
import com.demo.moviecatalogservice.Model.UserRatings;
import com.demo.moviecatalogservice.Services.MovieInfo;
import com.demo.moviecatalogservice.Services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	
	
	@Autowired
	public RestTemplate restTemplate;

	@Autowired
	public MovieInfo movieInfo;
	
	@Autowired
	public UserRatingInfo userRatingInfo;
	
	@RequestMapping(value="/{userId}",produces = "application/json")
	public List<CatalogItem> getcatalog(@PathVariable("userId") String userId){
		
		UserRatings ratings= userRatingInfo.getUserRating(userId);
		
		return ratings.getRating().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
			    .collect(Collectors.toList());
	}
	

}
