package com.learn.moviecatalog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.moviecatalog.models.Catalog;
import com.learn.moviecatalog.models.CatalogItem;
import com.learn.moviecatalog.models.Movie;
import com.learn.moviecatalog.models.Rating;
import com.learn.moviecatalog.models.UserRating;
import com.learn.moviecatalog.services.MovieInfoService;
import com.learn.moviecatalog.services.RatingInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MovieInfoService movieInfoService;
	
	@Autowired
	RatingInfoService ratingInfoService;

	@RequestMapping("/{userId}")
	public CatalogItem getCatalog(@PathVariable("userId") String userId){

		UserRating userRating = ratingInfoService.getUserRating(userId);
		
		CatalogItem ci = new CatalogItem();
		ArrayList<Catalog> temp = new ArrayList<Catalog>();
		
		for(Rating rating : userRating.getRatings()) {
			temp.add(movieInfoService.getCatalogDetails(rating));
		}
		ci.setCatalogList(temp);
		return ci;
	}

		
}
