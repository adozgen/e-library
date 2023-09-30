package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.CityRequest;
import com.adozgen.elibrary.model.City;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ICityService {
    public City addCity(CityRequest cityRequest);
    public List<City> getCities();
    public City getCity(Long cityId);
    public City deleteCity(Long cityId);
    public City updateCity(Long cityId, CityRequest cityRequest);
}
