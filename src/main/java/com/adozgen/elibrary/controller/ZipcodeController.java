package com.adozgen.elibrary.controller;

import com.adozgen.elibrary.dto.request.ZipcodeRequest;
import com.adozgen.elibrary.model.Zipcode;
import com.adozgen.elibrary.service.IZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {
    private final IZipcodeService zipcodeService;

    @Autowired
    public ZipcodeController(IZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequest zipcodeRequest){
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequest);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipcodeById(@PathVariable final Long id){
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/zipcodes")
    public ResponseEntity<List<Zipcode>> getAllZipcodes(){
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<List<Zipcode>>(zipcodes, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Zipcode> updateZipcode(@RequestBody final ZipcodeRequest zipcodeRequest, @PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.updateZipcode(id, zipcodeRequest);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/add-city/{cityId}/zipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addCityToZipcode(@PathVariable final Long cityId, @PathVariable final Long zipcodeId){
        Zipcode zipcode = zipcodeService.addCityToZipcode(cityId, zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("delete-city/{zipcodeId}")
    public ResponseEntity<Zipcode> deleteCityFromZipcode(@PathVariable final Long zipcodeId){
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
}
