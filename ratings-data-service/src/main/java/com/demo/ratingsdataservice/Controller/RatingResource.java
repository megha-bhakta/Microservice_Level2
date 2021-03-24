package com.demo.ratingsdataservice.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ratingsdataservice.Model.Rating;
import com.demo.ratingsdataservice.Model.UserRatings;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
	
	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId){
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/user/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId) {       
        UserRatings userRating = new UserRatings();
        userRating.initData(userId);
        return userRating;

}
}
