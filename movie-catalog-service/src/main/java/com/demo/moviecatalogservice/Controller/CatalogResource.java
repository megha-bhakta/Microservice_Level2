package com.demo.moviecatalogservice.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.moviecatalogservice.Model.CatalogItem;
import com.demo.moviecatalogservice.Model.Movie;
import com.demo.moviecatalogservice.Model.Rating;
import com.demo.moviecatalogservice.Model.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	
	
	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping(value="/{userId}",produces = "application/json")
	public List<CatalogItem> getcatalog(@PathVariable("userId") String userId){
		
		UserRatings ratings= getUserRating(userId);
		
		return ratings.getRating().stream()
				.map(rating -> getCatalogItem(rating))
			    .collect(Collectors.toList());
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRatings getUserRating(@PathVariable("userId") String userId){
		return restTemplate.getForObject("http://ratings-data-service/ratings/user/" + userId, UserRatings.class);
	}
	
	public UserRatings getFallbackUserRating(@PathVariable("userId") String userId){
		UserRatings userRating =  new UserRatings();
		userRating.setUserid(userId);
		userRating.setRating(Arrays.asList(
			new Rating("0",0)
			));
		return userRating;
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
	    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "",rating.getRating());
	}

}
