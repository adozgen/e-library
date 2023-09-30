package com.adozgen.elibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
    private Zipcode zipcode;


    public City(String name){
        this.name = name;
    }
}
