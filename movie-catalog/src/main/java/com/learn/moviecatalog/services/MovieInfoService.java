package com.learn.moviecatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.moviecatalog.models.Catalog;
import com.learn.moviecatalog.models.Movie;
import com.learn.moviecatalog.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogDetails")
	public Catalog getCatalogDetails(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-service/movies/" + rating.getMovieId(), Movie.class);	
		Catalog catalog = new Catalog(movie.getName(), movie.getDescription(), rating.getRating());
		return catalog;
	}
	
	
	private Catalog getFallbackCatalogDetails(Rating rating) {
		Catalog catalog = new Catalog("No Movie Found","NaDa",0);
		return catalog;
	}
	
}
