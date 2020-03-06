package com.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
public class President {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private String age;

    @JsonIgnore
    @JsonProperty
    @OneToMany(mappedBy = "president", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Country> country = new ArrayList<>();

}
