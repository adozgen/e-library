package com.adozgen.elibrary.repository;

import com.adozgen.elibrary.model.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode, Long> {
}
