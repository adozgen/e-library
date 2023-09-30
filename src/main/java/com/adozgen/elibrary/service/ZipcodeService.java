package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.ZipcodeRequest;
import com.adozgen.elibrary.model.City;
import com.adozgen.elibrary.model.Zipcode;
import com.adozgen.elibrary.repository.ZipcodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ZipcodeService implements IZipcodeService {

    private final ZipcodeRepository zipcodeRepository;
    private final ICityService cityService;

    @Autowired
    public ZipcodeService(ZipcodeRepository zipcodeRepository, ICityService cityService) {
        this.zipcodeRepository = zipcodeRepository;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequest zipcodeRequest) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequest.getName());
        if (zipcodeRequest.getCityId() == null) {
            zipcodeRepository.save(zipcode);
        }
        City city = cityService.getCity(zipcodeRequest.getCityId());
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }

    @Override
    public List<Zipcode> getZipcodes() {
        return StreamSupport.stream(zipcodeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        return zipcodeRepository.findById(zipcodeId).orElseThrow(()->
                new IllegalArgumentException(("zipcode bulunamadı: " + zipcodeId)));
    }

    @Override
    public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode updateZipcode(Long zipcodeId, ZipcodeRequest zipcodeRequest) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipcode.setName(zipcodeRequest.getName());
        if(zipcodeRequest.getCityId() == null){
            return zipcodeRepository.save(zipcode);
        }
        City city = cityService.getCity(zipcodeRequest.getCityId());
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        City city = cityService.getCity(cityId);
        if (Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("Bu şehir zaten var");
        }
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }


    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        if (Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("Bu şehir zaten yok");
        }
        zipcode.setCity(null);
        return zipcodeRepository.save(zipcode);
    }
}
