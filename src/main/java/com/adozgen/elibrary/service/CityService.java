package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.CityRequest;
import com.adozgen.elibrary.model.City;
import com.adozgen.elibrary.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CityService implements ICityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequest cityRequest) {
        City city = new City();
        city.setName(cityRequest.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(()->
                new IllegalArgumentException("şehir id bulunamadı: " + cityId));
    }

    @Override
    public City deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(()->
                new IllegalArgumentException("şehir id bulunamadı: " + cityId));
        cityRepository.delete(city);
        return city;
    }

    @Transactional
    @Override
    public City updateCity(Long cityId, CityRequest cityRequest) {
        City city = cityRepository.findById(cityId).orElseThrow(()->
                new IllegalArgumentException("şehir id bulunamadı: " + cityId));
        city.setName(cityRequest.getName());
        return cityRepository.save(city);
    }
}
