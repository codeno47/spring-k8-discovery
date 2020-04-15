package com.springmicroservice.moviewcatalogservice.resources;

import com.springmicroservice.moviewcatalogservice.models.CatalogItem;
import com.springmicroservice.moviewcatalogservice.models.Movie;
import com.springmicroservice.moviewcatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all related movie Ids
        UserRating ratings = restTemplate.getForObject("http://ratingdataservice:8085/rating/users/" + userId, UserRating.class);

        return ratings.getRatingList().stream().map(rating -> {
            //For each movie id, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://movieinfoservice:8082/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "test", rating.getRating());
        }).collect(Collectors.toList());

    }
}
