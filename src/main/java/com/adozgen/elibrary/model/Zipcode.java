package com.adozgen.elibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "zipcodes")
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "zipcode", cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    public Zipcode(String name, City city) {
        this.name = name;
        this.city = city;
    }

}
