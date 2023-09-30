package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.ZipcodeRequest;
import com.adozgen.elibrary.model.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IZipcodeService {
    public Zipcode addZipcode(ZipcodeRequest zipcodeRequest);
    public List<Zipcode> getZipcodes();
    public Zipcode getZipcode(Long zipcodeId);
    public Zipcode deleteZipcode(Long zipcodeId);
    public Zipcode updateZipcode(Long zipcodeId, ZipcodeRequest zipcodeRequest);
    Zipcode addCityToZipcode(Long zipcodeId, Long cityId);
    Zipcode removeCityFromZipcode(Long zipcodeId);
}
