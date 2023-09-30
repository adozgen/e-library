package com.adozgen.elibrary.repository;

import com.adozgen.elibrary.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {


}
