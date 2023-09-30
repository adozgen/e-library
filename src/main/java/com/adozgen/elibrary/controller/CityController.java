package com.adozgen.elibrary.controller;

import com.adozgen.elibrary.dto.request.CityRequest;
import com.adozgen.elibrary.model.City;
import com.adozgen.elibrary.model.Zipcode;
import com.adozgen.elibrary.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final ICityService cityService;

    @Autowired
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody final CityRequest cityRequest){
        City city = cityService.addCity(cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable final Long id){
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.getCities();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable final Long id) {
        City city = cityService.deleteCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<City> updateCity(@RequestBody final CityRequest cityRequest, @PathVariable final Long id) {
        City city = cityService.updateCity(id, cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
