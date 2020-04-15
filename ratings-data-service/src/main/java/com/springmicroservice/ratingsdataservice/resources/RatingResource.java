package com.springmicroservice.ratingsdataservice.resources;

import com.springmicroservice.ratingsdataservice.models.Rating;
import com.springmicroservice.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, "4");
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("123569", "4"),
                new Rating("5678", "3")
        );

        UserRating userRating = new UserRating();
        userRating.setRatingList(ratings);
        return userRating;
    }
}
