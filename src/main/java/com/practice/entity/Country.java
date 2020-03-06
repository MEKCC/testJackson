package com.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JoinColumn(name = "president_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private President president;

    @JsonProperty("countryName")
    private String countryName;

}
