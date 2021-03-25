package com.demo.moviecatalogservice.Services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.demo.moviecatalogservice.Model.Rating;
import com.demo.moviecatalogservice.Model.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {

	@Autowired
	public RestTemplate restTemplate;
	
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
}
